package com.example.linj.myapplication;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.TextUtils;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author JLin
 * @date 2019/12/2
 * @describe 日历
 */
public class CalendarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_calendar_event)
    public void onViewClicked() {
        CalendarRemindEvent.addCalendarEvent(getApplicationContext(), "宝柚健康", "提醒您吃药啦", 7);
    }

    @OnClick(R.id.delete_calendar_event)
    public void delete() {
        CalendarRemindEvent.deleteCalendarEvent(getApplicationContext(), "监测提醒");
    }

    @OnClick(R.id.query_calendar_event)
    public void query() {
        CalendarRemindEvent.queryCalendarEvent(getApplicationContext());
    }

    @OnClick(R.id.add_account)
    public void addAccount() {
        CalendarRemindEvent.addCalendarAccount(getApplicationContext());
    }

    @OnClick(R.id.get_account_id)
    public void getAccountId() {
        int id = CalendarRemindEvent.getAccountId(getApplicationContext());
    }

    public static class CalendarRemindEvent {
        private static String CALENDER_URL = "content://com.android.calendar/calendars";
        private static String CALENDER_EVENT_URL = "content://com.android.calendar/events";
        private static String CALENDER_REMINDER_URL = "content://com.android.calendar/reminders";

        private static String CALENDARS_NAME = "baou";
        private static String CALENDARS_ACCOUNT_NAME = "baou@neurotech.cn";
        private static String CALENDARS_ACCOUNT_TYPE = "com.neurotech.baou";
        private static String CALENDARS_DISPLAY_NAME = "宝柚健康";

        /**
         * 检查是否已经添加了日历账户，如果没有添加先添加一个日历账户再查询
         * 获取账户成功返回账户id，否则返回-1
         */
        private static int checkAndAddCalendarAccount(Context context) {
            int oldId = getAccountId(context);
            if (oldId >= 0) {
                return oldId;
            } else {
                long addId = addCalendarAccount(context);
                if (addId >= 0) {
                    return getAccountId(context);
                } else {
                    return -1;
                }
            }
        }

        /**
         * 检查是否存在现有账户，存在则返回账户id，否则返回-1
         */
        private static int getAccountId(Context context) {
            int id = -1;
            Cursor userCursor = context.getContentResolver().query(Uri.parse(CALENDER_URL), null, null, null, null);
            while (userCursor.moveToNext()) {
                String name = userCursor.getString(userCursor.getColumnIndex(CalendarContract.Calendars.ACCOUNT_NAME));
                if (CALENDARS_ACCOUNT_NAME.equals(name)) {
                    id = userCursor.getInt((userCursor.getColumnIndex(CalendarContract.Calendars._ID)));
                }
            }
            return id;
        }

        /**
         * 添加日历账户，账户创建成功则返回账户id，否则返回-1
         */
        private static long addCalendarAccount(Context context) {
            TimeZone timeZone = TimeZone.getDefault();
            ContentValues value = new ContentValues();
            value.put(CalendarContract.Calendars.NAME, CALENDARS_NAME);
            value.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME);
            value.put(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE);
            value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDARS_DISPLAY_NAME);
            value.put(CalendarContract.Calendars.VISIBLE, 1);
            value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);
            value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
            value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
            value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
            value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME);
            value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);

            Uri calendarUri = Uri.parse(CALENDER_URL);
            calendarUri = calendarUri.buildUpon()
                    .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                    .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
                    .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
                    .build();

            Uri result = context.getContentResolver().insert(calendarUri, value);
            long id = result == null ? -1 : ContentUris.parseId(result);
            return id;
        }

        /**
         * 添加日历事件
         */
        static void addCalendarEvent(Context context, String title, String description, int previousDate) {
            if (context == null) {
                return;
            }
            int calId = checkAndAddCalendarAccount(context); //获取日历账户的id
            if (calId < 0) { //获取账户id失败直接返回，添加日历事件失败
                return;
            }

            //添加日历事件
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(System.currentTimeMillis() + (1000 * 60 * 10));//设置开始时间
            long start = mCalendar.getTime().getTime();
            mCalendar.setTimeInMillis(start + 10 * 60 * 1000);//设置终止时间，开始时间加10分钟
            long end = mCalendar.getTime().getTime();

            ContentValues event = new ContentValues();
            event.put("title", title);
            event.put("description", description);
            event.put("calendar_id", calId); //插入账户的id
            event.put(CalendarContract.Events._ID, 1000);
            event.put(CalendarContract.Events.DTSTART, start);
            event.put(CalendarContract.Events.DTEND, end);
            event.put(CalendarContract.Events.HAS_ALARM, 1);//设置有闹钟提醒
            event.put(CalendarContract.Events.RRULE, "FREQ=WEEKLY;WKST=SU;BYDAY=TU");
            event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");//这个是时区，必须有
            Uri newEvent = context.getContentResolver().insert(Uri.parse(CALENDER_EVENT_URL), event); //添加事件
            if (newEvent == null) { //添加日历事件失败直接返回
                return;
            }

            //事件提醒的设定
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
            values.put(CalendarContract.Reminders.MINUTES, previousDate);// 提前previousDate天有提醒
            values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
            Uri uri = context.getContentResolver().insert(Uri.parse(CALENDER_REMINDER_URL), values);
            if (uri == null) { //添加事件提醒失败直接返回
                return;
            }
        }

        /**
         * 查询日历时间
         */
        public static void queryCalendarEvent(Context context) {
            Uri uri = Uri.parse(CALENDER_EVENT_URL);
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            while (cursor.moveToNext()) {
                if (getAccountId(context) == cursor.getInt(cursor.getColumnIndex(CalendarContract.Events.CALENDAR_ID))) {
                    String title = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE));

                    String startTime = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DTSTART));
                }
            }
        }

        /**
         * 删除日历事件
         */
        public static void deleteCalendarEvent(Context context, String title) {
            if (context == null) {
                return;
            }
            Cursor eventCursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), null, null, null, null);
            try {
                if (eventCursor == null) { //查询返回空值
                    return;
                }
                if (eventCursor.getCount() > 0) {
                    //遍历所有事件，找到title跟需要查询的title一样的项
                    for (eventCursor.moveToFirst(); !eventCursor.isAfterLast(); eventCursor.moveToNext()) {
                        String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
                        if (!TextUtils.isEmpty(title) && title.equals(eventTitle)) {
                            int id = eventCursor.getInt(eventCursor.getColumnIndex(CalendarContract.Calendars._ID));//取得id
                            Uri deleteUri = ContentUris.withAppendedId(Uri.parse(CALENDER_EVENT_URL), id);
                            int rows = context.getContentResolver().delete(deleteUri, null, null);
                            if (rows == -1) { //事件删除失败
                                return;
                            }
                        }
                    }
                }
            } finally {
                if (eventCursor != null) {
                    eventCursor.close();
                }
            }
        }
    }
}
