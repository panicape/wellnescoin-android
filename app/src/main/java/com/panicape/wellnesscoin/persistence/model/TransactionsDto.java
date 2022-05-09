package com.panicape.wellnesscoin.persistence.model;

import java.util.Date;

import lombok.AllArgsConstructor;

/**
 * @author panicape
 */
@AllArgsConstructor
public class TransactionsDto {

    private Date dateCreation;

    // Transaction Type
    private Integer txType;

    private Float amount;

    private String from;

    private String to;

    // 1- Asked, 2-payed, 3-cancelled
    private Integer status;

    // this field shouldn't be saved in Transactions,
    // It is only used to be compared with user-password from wallet
    private String password;

    public TransactionsDto() {
    }

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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
