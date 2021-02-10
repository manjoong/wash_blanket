package com.washing_blanket_prd.washing_blanket.viewmodel;

import com.washing_blanket_prd.washing_blanket.viewmodel.TSLiveData;

import java.util.Calendar;

import androidx.lifecycle.ViewModel;

public class CalendarViewModel extends ViewModel {
    public TSLiveData<Calendar> mCalendar = new TSLiveData<>();

    public void setCalendar(Calendar calendar) {
        this.mCalendar.setValue(calendar);
    }


}
