package com.washing_blanket_prd.washing_blanket.viewmodel;

import androidx.lifecycle.MutableLiveData;

public class TSLiveData<T> extends MutableLiveData<T> {

    public TSLiveData() {

    }

    public TSLiveData(T value) {
        setValue(value);
    }
}