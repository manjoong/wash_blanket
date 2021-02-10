package com.washing_blanket_prd.washing_blanket.bindingAdapter;

import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.databinding.BindingAdapter;

import com.washing_blanket_prd.washing_blanket.DateFormat;

public class TextBindingAdapter {


    @BindingAdapter({"setCalendarHeaderText"})
    public static void setCalendarHeaderText(TextView view, Long date) {
        try {
            if (date != null) {
                view.setText(DateFormat.getDate(date, DateFormat.CALENDAR_HEADER_FORMAT)+ " 세탁 스케줄");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setWeekText"})
    public static void setWeekText(TextView view, String week) {
        try {
            if (week != null) {
                if (week.equals("일")){
                    view.setText(week);
                    view.setTextColor(Color.RED);
                }else{
                    view.setText(week);
                    view.setTextColor(Color.BLACK);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setDayText"})
    public static void setDayText(TextView view, Calendar calendar) {
        try {
            if (calendar != null) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                view.setText(DateFormat.getDate(gregorianCalendar.getTimeInMillis(), DateFormat.DAY_FORMAT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}