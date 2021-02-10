package com.washing_blanket_prd.washing_blanket.viewmodel;

import androidx.lifecycle.ViewModel;



public class CalendarHeaderViewModel extends ViewModel {
    public TSLiveData<Long> mHeaderDate = new TSLiveData<>();

    public void setHeaderDate(long headerDate) {
        this.mHeaderDate.setValue(headerDate);
    }
}