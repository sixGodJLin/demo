package com.example.linj.myapplication.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

/**
 * @author JLin
 * @date 2018/11/7
 */
public class CalendarEventUtil {
    private static String CALENDER_URL = "content://com.android.calendar/calendars";
    private static String CALENDER_EVENT_URL = "content://com.android.calendar/events";
    private static String CALENDER_REMINDER_URL = "content://com.android.calendar/reminders";

    private static String CALENDARS_NAME = "baou";
    private static String CALENDARS_ACCOUNT_NAME = "baou@neurotech.cn";
    private static String CALENDARS_ACCOUNT_TYPE = "com.neurotech.baou";
    private static String CALENDARS_DISPLAY_NAME = "宝柚健康";


}
