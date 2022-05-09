package com.panicape.wellnesscoin.persistence.model.enums;

public enum ETransactionType {

    WALLET_CREATE(1),
    BALANCE_UPDDATE(2),
    DO_PAYMENT (3);

    private int code;

    ETransactionType() {
    }

    public int getCode() {
        return code;
    }

    ETransactionType(int code) {
        this.code = code;
    }
}
