package com.example.linj.myapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.View;
import android.widget.TextView;

import com.example.linj.myapplication.bean.CourseRecordBean2;
import com.example.linj.myapplication.utils.StringAdapter;
import com.example.linj.myapplication.view.calendar.Calendar;
import com.example.linj.myapplication.view.calendar.CalendarView;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JLin
 */
public class MainActivity extends AppCompatActivity implements
        CalendarView.OnMonthChangeListener,
        CalendarView.OnCalendarSelectListener,
        View.OnClickListener {
    private CalendarView calendarView;
    private TextView calendarDate;
    private RecyclerView recyclerView;
    private StringAdapter adapter;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setEnterTransition(new Explode().setDuration(2000));
        getWindow().setExitTransition(new Explode().setDuration(2000));

        initView();
    }

    private void initView() {
        calendarView = findViewById(R.id.calendarView);
        calendarDate = findViewById(R.id.calendar_date);
        recyclerView = findViewById(R.id.recyclerView);

        calendarView.setOnCalendarSelectListener(this);
        calendarView.setOnMonthChangeListener(this);

        TextView preMonth = findViewById(R.id.pre_month);
        TextView nextMonth = findViewById(R.id.next_month);

        preMonth.setOnClickListener(this);
        nextMonth.setOnClickListener(this);
    }

    private void initSchemeData(List<CourseRecordBean2.RowsBean> data) {
        Map<String, Calendar> map = new HashMap<>();

        for (CourseRecordBean2.RowsBean bean : data) {
            map.put(bean.getDate(), setScheme(bean.getEvents().size()));
        }

        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        calendarView.setSchemeDate(map);
    }

    private Calendar setScheme(int num) {
        Calendar calendar = new Calendar();
        calendar.setScheme(String.valueOf(num));
        return calendar;
    }

    @Override
    public void onMonthChange(int year, int month) {
    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        calendarDate.setText(new LocalDate(calendar.getTimeInMillis()).toString("yyyy-MM-dd"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pre_month:
                calendarView.scrollToPre();
                break;
            case R.id.next_month:
//                calendarView.scrollToNext();
                System.out.println("MainActivity " + "onClick " + "----getTranslationY: " + recyclerView.computeVerticalScrollOffset());
                break;
            default:
                break;
        }
    }


}