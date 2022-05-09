package com.panicape.wellnesscoin.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.panicape.wellnesscoin.persistence.PersistenceTools;

/**
 * @author panicape
 * @version 1.01 May 2022
 */
public class LoginViewModel extends ViewModel {

    private PersistenceTools persistenceTools;

    private MutableLiveData<Boolean> itemVisible;


    // Constructor

    public LoginViewModel() {
        persistenceTools = new PersistenceTools();
        if (itemVisible == null) {
            itemVisible = new MutableLiveData<Boolean>();
            itemVisible.setValue(false);
        }
    }


    // Getters & Setters

    private LiveData<Boolean> getItemVisible() {
        return itemVisible;
    }

    public void setItemVisible(MutableLiveData<Boolean> itemVisible) {
        this.itemVisible = itemVisible;
    }

    // Methods

}