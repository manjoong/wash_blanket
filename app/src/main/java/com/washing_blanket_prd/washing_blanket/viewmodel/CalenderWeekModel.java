package com.washing_blanket_prd.washing_blanket.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.Calendar;

public class CalenderWeekModel extends ViewModel {
    public TSLiveData<String> mWeek = new TSLiveData<>();

    public void setWeekCalendar(String week) {
        this.mWeek.setValue(week);
    }


}

