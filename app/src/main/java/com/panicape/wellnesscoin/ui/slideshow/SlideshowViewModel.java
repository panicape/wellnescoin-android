package com.panicape.wellnesscoin.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mWalletValue;

    private MutableLiveData<List<String>> mList;



    public SlideshowViewModel() {
//        mText = new MutableLiveData<String>();
//        mText.setValue("This is slideshow fragment");
        mWalletValue = new MutableLiveData<String>("0");

        loadList();
    }

    private void loadList () {
        List<String> pausasCheckedList = new ArrayList<String>();
        // Formato fecha: ddMMaaaaHHmmss
        pausasCheckedList.add("pcarrillo_01012022101022");
        pausasCheckedList.add("pcarrillo_01012022120010");
        pausasCheckedList.add("pcarrillo_01012022151509");

        mList = new MutableLiveData<List<String>>();
        mList.setValue(pausasCheckedList);
    }

    public LiveData<String> getWalletValue() {
        return mWalletValue;
    }

    public void setWalletValue(String value) {
        this.mWalletValue.setValue(value);
    }

    public LiveData<List<String>> getList() {
        return mList;
    }
}