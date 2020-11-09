package com.example.linj.myapplication.utils;

import android.annotation.SuppressLint;
import android.text.format.DateUtils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 时间工具类
 * LocalDateTime 格式为 ： 2018-11-15T16:44:08.458
 * DateTime      格式为 ： 2018-11-15T16:45:08.458+08:00
 * LocalDate     格式为 ： 2018-11-15
 * LocalTime     格式为 ： 16:47:16.242
 *
 * @author JLin
 * @date 2019/1/1
 */
public final class TimeUtils extends DateUtils {

    /**
     * 从周几开始，1为周一，0为周日
     */
    private static final int DAY_OF_WEEK_MONDAY = 1;
    private static final String[] WEEK_FULL_NAME = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"};
    private static final String[] WEEK_FULL_NAME_SUN = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private static final String[] WEEK_SIMPLE_NAME = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private static final String[] WEEK_SIMPLE_NAME_SUN = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static final String[] ZODIAC = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};
    private static final int[] ZODIAC_FLAGS = {20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};
    private static final String[] CHINESE_ZODIAC = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat simpleHHmm = new SimpleDateFormat("HH:mm");

    public static List<String> getRandomTime(String startTime, String endTime, int num) {
        Date start = null;
        Date end = null;
        try {
            start = simpleHHmm.parse(startTime);
            end = simpleHHmm.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert start != null;
        assert end != null;
        return getMiddleTime(start, end, num);
    }

    public static List<String> getMiddleTime(Date start, Date end, int num) {
        long startL = start.getTime();
        long endL = end.getTime();
        List<String> times = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            long middleL = (long) (startL + (endL - startL) * Math.random());
            times.add(simpleHHmm.format(new Date(middleL)));
        }
        Collections.sort(times);
        for (int i = 1; i < num; i++) {
            if (times.get(i).equals(times.get(i - 1))) {
                Date newTime = null;
                try {
                    newTime = simpleHHmm.parse(times.get(i));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                assert newTime != null;
                times.set(i, simpleHHmm.format(new Date(newTime.getTime() + 60000)));
            }
            try {
                Date oldTime = simpleHHmm.parse(times.get(i - 1));
                Date newTime = simpleHHmm.parse(times.get(i));
                assert newTime != null;
                assert oldTime != null;
                if (newTime.getTime() < oldTime.getTime()) {
                    times.set(i, simpleHHmm.format(new Date(oldTime.getTime() + 60000)));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return times;
    }

    public static String getNowTimeHour() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(new Date());
    }

    public static String getAfterNowTimeHour() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(System.currentTimeMillis() + 60000);
    }

    public static String getStandardTime() {
        return TimeUtils.format(System.currentTimeMillis(), Formatter.YYYY_MM_DD_HH_MM_SS_24HOUR);
    }

    /**
     * 获得两个日期之间隔了几周
     *
     * @param type 0 周日 1 周一 默认0
     * @return 相隔周数
     */
    public static int getIntervalWeek(LocalDate date1, LocalDate date2, int type) {
        if (type == 0) {
            date1 = getSunFirstDayOfWeek(date1);
            date2 = getSunFirstDayOfWeek(date2);
        } else {
            date1 = getMonFirstDayOfWeek(date1);
            date2 = getMonFirstDayOfWeek(date2);
        }
        return Weeks.weeksBetween(date1, date2).getWeeks();
    }

    private static LocalDate getSunFirstDayOfWeek(LocalDate date) {
        return date.dayOfWeek().get() == 7 ? date : date.minusWeeks(1).withDayOfWeek(7);
    }

    private static LocalDate getMonFirstDayOfWeek(LocalDate date) {
        return date.dayOfWeek().withMinimumValue();
    }

    /**
     * 获得两个日期距离几个月
     *
     * @return 相隔约数
     */
    public static int getIntervalMonth(LocalDate date1, LocalDate date2) {
        date1 = date1.withDayOfMonth(1);
        date2 = date2.withDayOfMonth(1);
        return Months.monthsBetween(date1, date2).getMonths();
    }

    /**
     * 判断两个日期是否同月
     */
    public static boolean isEqualsMonth(LocalDate date1, LocalDate date2) {
        return date1.getYear() == date2.getYear() && date1.getMonthOfYear() == date2.getMonthOfYear();
    }

    public static List<LocalDate> getWeekCalendar2(LocalDate date, int type) {
        List<LocalDate> dateList = new ArrayList<>();
        if (type == 0) {
            date = getSunFirstDayOfWeek(date);
        } else {
            date = getMonFirstDayOfWeek(date);
        }
        for (int i = 0; i < 7; i++) {
            LocalDate date1 = date.plusDays(i);
            dateList.add(date1);
        }
        return dateList;
    }

    /**
     * 填充月历 将上一个月日期和当月日期和下一个月日期拼合
     */
    public static List<LocalDate> getMonthCalendar2(LocalDate date, int type) {
        List<LocalDate> dates = new ArrayList<>();
        // 上一个月
        LocalDate lastMonthDate = date.minusMonths(1);
        // 下一个月
        LocalDate nextMonthDate = date.plusMonths(1);
        // 当月天数
        int days = date.dayOfMonth().getMaximumValue();
        // 上一个月的天数
        int lastMonthDays = lastMonthDate.dayOfMonth().getMaximumValue();
        // 当月第一天周几
        int firstDayOfWeek = new LocalDate(date.getYear(), date.getMonthOfYear(), 1).getDayOfWeek();
        // 当月最后一天周几
        int endDayOfWeek = new LocalDate(date.getYear(), date.getMonthOfYear(), days).getDayOfWeek();
        if (type == 0) {
            // 上一个月
            if (firstDayOfWeek != 7) {
                for (int i = 0; i < firstDayOfWeek; i++) {
                    LocalDate date1 = new LocalDate(lastMonthDate.getYear(), lastMonthDate.getMonthOfYear(),
                            lastMonthDays - (firstDayOfWeek - i - 1));
                    dates.add(date1);
                }
            }
            // 当月
            for (int i = 0; i < days; i++) {
                LocalDate date1 = new LocalDate(date.getYear(), date.getMonthOfYear(), i + 1);
                dates.add(date1);
            }
            // 下一个月
            if (endDayOfWeek == 7) {
                endDayOfWeek = 0;
            }
            for (int i = 0; i < 6 - endDayOfWeek; i++) {
                LocalDate date1 = new LocalDate(nextMonthDate.getYear(), nextMonthDate.getMonthOfYear(), i + 1);
                dates.add(date1);
            }
        } else {
            for (int i = 0; i < firstDayOfWeek - 1; i++) {
                LocalDate date1 = new LocalDate(lastMonthDate.getYear(), lastMonthDate.getMonthOfYear(),
                        lastMonthDays - (firstDayOfWeek - i - 2));
                dates.add(date1);
            }
            for (int i = 0; i < days; i++) {
                LocalDate date1 = new LocalDate(date.getYear(), date.getMonthOfYear(), i + 1);
                dates.add(date1);
            }
            int size = dates.size();
            for (int i = 0; i < 42 - size; i++) {
                LocalDate date1 = new LocalDate(nextMonthDate.getYear(), nextMonthDate.getMonthOfYear(), i + 1);
                dates.add(date1);
            }
        }
        return dates;
    }

    /**
     * 某月第一天是周几
     */
    public static int getFirstDayOfWeekOfMonth(int year, int month) {
        int dayOfWeek = new LocalDate(year, month, 1).getDayOfWeek();
        return dayOfWeek == 7 ? 0 : dayOfWeek;
    }

    /**
     * date1是否date2的上一个月（只在此处有效）
     */
    public static boolean isLastMonth(LocalDate date1, LocalDate date2) {
        LocalDate date = date2.minusMonths(1);
        return date1.getMonthOfYear() == date.getMonthOfYear();
    }

    /**
     * date1是否date2的下一个月（只在此处有效）
     */
    public static boolean isNextMonth(LocalDate date1, LocalDate date2) {
        LocalDate date = date2.plusMonths(1);
        return date1.getMonthOfYear() == date.getMonthOfYear();
    }

    /**
     * 是否是今天
     */
    public static boolean isToday(LocalDate date) {
        return new LocalDate().equals(date);
    }

    /**
     * 是否是今天
     *
     * @param origin 标注时间 2018-11-12 10:30:08
     */
    public static boolean isToday(String origin) {
        try {
            long time = new SimpleDateFormat(Formatter.YYYY_MM_DD_HH_MM_SS_24HOUR.value(), Locale.getDefault()).parse(origin).getTime();
            return isToday(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断两天是否同一天
     *
     * @param origin  标准时间 2018-11-12 10:30:08
     * @param compare 标准时间 2018-11-12 10:30:08
     * @return true则为同一天
     */
    public static boolean isSameDay(String origin, String compare) {
        try {
            long ori = new SimpleDateFormat(Formatter.YYYY_MM_DD_HH_MM_SS_24HOUR.value(), Locale.getDefault()).parse(origin).getTime();
            long com = new SimpleDateFormat(Formatter.YYYY_MM_DD_HH_MM_SS_24HOUR.value(), Locale.getDefault()).parse(compare).getTime();
            LocalDate oriDate = new LocalDate(ori);
            LocalDate comDate = new LocalDate(com);
            return oriDate.equals(comDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取星期几
     *
     * @param week week
     * @return 星期几
     */
    public static String weekDay(int week) {
        return weekDay(week, DAY_OF_WEEK_MONDAY, true);
    }

    /**
     * 获取周几
     *
     * @param week week
     * @return 周几
     * @deprecated Use {@link TimeUtils#weekDay(int)} instead, which includes both use Full Name.
     */
    @Deprecated
    public static String weekDay(Integer week) {
        return weekDay(week, DAY_OF_WEEK_MONDAY, false);
    }

    /**
     * 获取星期几或周几
     *
     * @param week day of week 一周中的第几天 仅支持参数[1,2,3,4,5,6,7]
     * @param type 第一天为周日开始还是周一开始 1为周一，0为周日
     * @return 星期或周
     */
    public static String weekDay(int week, int type, boolean full) {
        try {
            String result;
            if (full) {
                result = type == 0 ? WEEK_FULL_NAME_SUN[week - 1] : WEEK_FULL_NAME[week - 1];
            } else {
                result = type == 0 ? WEEK_SIMPLE_NAME_SUN[week - 1] : WEEK_SIMPLE_NAME[week - 1];
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "-";
        }
    }

    /**
     * 获取星座
     *
     * @param localDate localDate
     * @return 星座
     */
    public static String getZodiac(LocalDate localDate) {
        int month = localDate.getMonthOfYear();
        int day = localDate.getDayOfMonth();
        return ZODIAC[day >= ZODIAC_FLAGS[month - 1]
                ? month - 1
                : (month + 10) % 12];
    }

    /**
     * 获取生肖
     *
     * @param localDate localDate
     * @return 生肖
     */
    public static String getChineseZodiac(LocalDate localDate) {
        int year = localDate.getYear();
        return CHINESE_ZODIAC[year % 12];
    }

    /**
     * 格式化时间戳到指定格式
     *
     * @param timeMillis timeMillis
     * @param formatter  value
     * @return formatted
     */
    public static String format(long timeMillis, Formatter formatter) {
        return new SimpleDateFormat(formatter.value(), Locale.getDefault()).format(timeMillis);
    }

    /**
     * 格式化标准时间到指定格式
     *
     * @param origin    标准时间 形如：2018-11-12 10:30:08
     * @param formatter value
     * @return formatted
     */
    public static String format(String origin, Formatter formatter) {
        return format(origin, Formatter.YYYY_MM_DD_HH_MM_SS_24HOUR, formatter);
    }

    /**
     * 格式化时间到指定格式 用LocalDate
     *
     * @param origin        时间 任何格式
     * @param oriFormatter  原始格式，对应origin格式
     * @param destFormatter 目标格式
     * @return formatted
     */
    public static String format(String origin, Formatter oriFormatter, Formatter destFormatter) {
        try {
            return DateTime.parse(origin, DateTimeFormat.forPattern(oriFormatter.value())).toString(destFormatter.value());
        } catch (Exception e) {
            return origin;
        }
    }

    /**
     * 格式化时间到指定格式
     *
     * @param origin        时间 任何格式
     * @param oriFormatter  原始格式，对应origin格式
     * @param destFormatter 目标格式
     * @return formatted
     */
//    public static String format(String origin, Formatter oriFormatter, Formatter destFormatter) {
//        try {
//            long timeMillis = new SimpleDateFormat(oriFormatter.value(), Locale.getDefault()).parse(origin).getTime();
//            LocalDateTime localDateTime = new LocalDateTime(timeMillis);
//            return localDateTime.toString(destFormatter.value());
//        } catch (ParseException e) {
//            return origin;
//        }
//    }

    /**
     * 格式化Date格式日期
     *
     * @param date      date
     * @param formatter formatter
     * @return 格式化后的string
     * @deprecated {@link TimeUtils#format(long, Formatter)}
     */
    @Deprecated
    public static String format(Date date, Formatter formatter) {
        try {
            return new LocalDateTime(date).toString(formatter.value(), Locale.getDefault());
        } catch (Exception e) {
            return date.toString();
        }
    }

    /**
     * 将标准格式时间转为 时间戳
     *
     * @param origin 标准格式 2018-11-06 12:34:02
     * @return 时间戳
     */
    public static long toMillis(String origin) {
        try {
            return new SimpleDateFormat(Formatter.YYYY_MM_DD_HH_MM_SS_24HOUR.value(), Locale.getDefault()).parse(origin).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 将格式时间转为 时间戳
     *
     * @param origin 标准格式 2018-11-06 12:34:02
     * @return 时间戳
     */
    public static long toMillis(String origin, Formatter formatter) {
        try {
            return new SimpleDateFormat(formatter.value(), Locale.getDefault()).parse(origin).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 将日期转化为 yyyy-MM-dd HH:mm:ss 标准格式
     *
     * @param localDate localDate
     * @return 标准格式
     */
    public static String toMillisString(LocalDate localDate) {
        return localDate.toString(Formatter.YYYY_MM_DD_HH_MM_SS_24HOUR.value());
    }

    /**
     * 将日期转化为相应格式
     *
     * @param origin    格式 2018-12-20
     * @param formatter formatter 支持 YYYY || MM || dd
     * @return YYYY || MM || dd
     */
    public static String dateString(String origin, Formatter formatter) {
        return new LocalDate(origin).toString(formatter.value());
    }

    /**
     * 将日期转化为相应格式
     *
     * @param date      date
     * @param formatter formatter 支持 YYYY || MM || dd
     * @return YYYY || MM || dd
     * @deprecated 不推荐使用 {@link TimeUtils#dateString(String, Formatter)}
     */
    @Deprecated
    public static String dateString(Date date, Formatter formatter) {
        return new LocalDateTime(date).toString(formatter.value());
    }

    /**
     * 获取两个日期相隔天数
     *
     * @param s1 LocalDate
     * @param s2 LocalDate
     * @return 天数
     */
    public static int separate(LocalDate s1, LocalDate s2) {
        return Days.daysBetween(s1, s2).getDays();
    }

    /**
     * 是否白天
     *
     * @return true 则为白天
     */
    public static boolean isDaytime() {
        int hourOfDay = new LocalTime().getHourOfDay();
        return hourOfDay > 6 && hourOfDay < 18;
    }

    public enum Formatter {

        YYYY("yyyy"),
        YYYY_CH("yyyy年"),

        YYYY_MM_DD("yyyy-MM-dd"),
        YYYY_MM_DD_CH("yyyy年MM月dd日"),

        YYYY_MM("yyyy-MM"),
        YYYY_MM_CH("yyyy年MM月"),

        YYYY_MM_DD_HH_MM_24HOUR("yyyy-MM-dd HH:mm"),
        YYYY_MM_DD_HH_MM_24HOUR_CH("yyyy年MM月dd日 HH:mm"),

        YYYY_MM_DD_HH_MM_SS_24HOUR("yyyy-MM-dd HH:mm:ss"),
        YYYY_MM_DD_HH_MM_SS_24HOUR_DOT("yyyy.MM.dd HH:mm:ss"),
        YYYY_MM_DD_HH_MM_SS_12HOUR("yyyy-MM-dd hh:mm:ss"),
        YYYY_MM_DD_HH_MM_yySS_KNELLING("yyyyMMdd HHmmss"),
        YYMMDDHHMM("yyMMddHHmm"),
        YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
        YYYYMMDDHHMMSSS("yyyyMMddHHmmssS"),
        YYMMDD_HHMMSS("yyyyMMdd'T'HHmmss'Z'"),

        YYYY_MM_DD_HH_MM_SS_24HOUR_CH("yyyy年MM月dd日 HH:mm:ss"),
        YYYY_MM_DD_HH_MM_SS_12HOUR_CH("yyyy年MM月dd日 hh:mm:ss"),

        MM_DD_HH_MM("MM-dd HH:mm"),
        MM_DD_HH_MM_LINE("MM/dd HH:mm"),
        MM_DD_HH_MM_WRAP("MM-dd\nHH:mm"),
        MM_DD_HH_MM_CH("MM月dd HH:mm"),

        MM_DD("MM-dd"),
        MM_DD_CH("MM月dd日"),

        M_D("M-d"),
        M_D_CH("M月d日"),

        HH_MM_SS_24HOUR("HH:mm:ss"),
        HH_MM_SS_24HOUR_CH("HH时mm分ss秒"),
        HH_MM_SS_12HOUR("hh:mm:ss"),
        HH_MM_SS_12HOUR_CH("hh时mm分ss秒"),

        HH_MM_24HOUR("HH:mm"),
        HH_MM_12HOUR("hh:mm"),

        MM("MM"),
        M("M"),

        DD("dd"),
        D("d"),

        DD_MM_YYYY("dd-MM-yyyy"),
        YYYY_MM_DD_HH_MM_SS_LINE("yyyy_MM_dd_HH_mm_ss"),
        YYYY_MM_DD_HH_MM_SS_MID("yyyyMMdd-HHmmss"),
        DD_MM_YY_HH_MM_SS_12HOUR("dd.MM.yyhh.mm.ss");

        private final String value;

        Formatter(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    public static String longToTime(long millis) {
        String time;
        int h = (int) (millis / 3600);
        int m = (int) ((millis % 3600) / 60);
        if (m == 0) {
            time = h + "点";
        } else {
            time = h + "点" + m + "分";
        }
        return time;
    }

    public static boolean isTimeOpen() {
//        if (Constants.MORNING_OPEN == 0 && Constants.MORNING_CLOSE == 0
//                && Constants.EVENING_CLOSE == 0 && Constants.EVENING_OPEN == 0) {
//            return true;
//        } else {
//            String[] nowTime = getNowTimeHour().split(":");
//            long nowTimeMill = Integer.parseInt(nowTime[0]) * 3600 + Integer.parseInt(nowTime[1]) * 60;
//            System.out.println("isTimeOpen" + "==== " + nowTimeMill);
//            return (nowTimeMill > Constants.MORNING_OPEN && nowTimeMill < Constants.MORNING_CLOSE) ||
//                    nowTimeMill > Constants.EVENING_OPEN && nowTimeMill < Constants.EVENING_CLOSE;
//        }
        return true;
    }

}

