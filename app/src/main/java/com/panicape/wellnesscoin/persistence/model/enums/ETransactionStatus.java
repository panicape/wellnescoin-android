package com.panicape.wellnesscoin.persistence.model.enums;

/**
 *
 */
public enum ETransactionStatus {

    ASKED(1, "ASKED"),
    PAYED (2, "PAYED"),
    CANCELLED (3, "CANCELLED");

    private Integer code;

    private String name;

    /**
     * Constructor
     */
    ETransactionStatus() {
    }

    ETransactionStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
