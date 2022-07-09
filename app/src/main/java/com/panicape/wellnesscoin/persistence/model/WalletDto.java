package com.panicape.wellnesscoin.persistence.model;

import java.util.Date;

/**
 *
 * @author panicape
 * @version 1.01
 */
public class WalletDto {

    private String username;

    private String password;

    private Float balance;

    private Date lastUpdate;


    // Constructor

    public WalletDto() {
    }


    // Methods

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
