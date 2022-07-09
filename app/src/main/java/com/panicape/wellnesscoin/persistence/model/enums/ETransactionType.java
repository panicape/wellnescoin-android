package com.panicape.wellnesscoin.persistence.model.enums;

/**
 *
 * @author panicape
 * @version 1.01
 */
public enum ETransactionType {

    PAUSA_REGISTRATION(1),
    PAUSA_VALIDATION(2),
    VALIDATION_REWARD_OBTAINED (3);

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
