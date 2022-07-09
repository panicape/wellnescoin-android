package com.panicape.wellnesscoin.persistence.model.enums;

/**
 *
 * @author panicape
 * @version 1.01
 */
public enum ETransactionStatus {

    ASKED (1, "ASKED"),
    PAYED (2, "PAYED"),
    CANCELLED (3, "CANCELLED");


    // Variables

    private Integer code;

    private String name;


    // Constructor

    ETransactionStatus() {
    }

    ETransactionStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }


    // Methods

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
