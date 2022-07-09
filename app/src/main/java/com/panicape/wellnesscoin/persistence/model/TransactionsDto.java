package com.panicape.wellnesscoin.persistence.model;

import java.util.Date;

import lombok.AllArgsConstructor;

/**
 *
 * @author panicape
 * @version 1.01
 */
@AllArgsConstructor
public class TransactionsDto {

    private Date dateCreation;

    // Transaction Type
    private Integer txType;

    // Participant  that creates the transaction
    private String from;


    // Constructor

    public TransactionsDto() {
    }


    //Methods

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Integer getTxType() {
        return txType;
    }

    public void setTxType(Integer txType) {
        this.txType = txType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}
