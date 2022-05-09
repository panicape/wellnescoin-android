package com.panicape.wellnesscoin.persistence;


/**
 * Persistence Tools Class
 *
 * @author panicape
 * @version 1.01 November 2020
 */
public class PersistenceTools {

    /** helpPersistence */
    private HelpPersistence helpPersistence;

    /** pausaPersistence */
    private  PausaPersistence pausaPersistence;

    /** paymentPersistence */
    private TransactionPersistence transactionPersistence;

    /** userPersistence */
    private UserPersistence userPersistence;



    // Getters

    /**
     *
     * @return
     */
    public UserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistence();
        }
        return userPersistence;
    }

    /**
     *
     * @return
     */
    public HelpPersistence getHelpPersistence() {
        if (helpPersistence == null) {
            helpPersistence = new HelpPersistence();
        }

        return helpPersistence;
    }

    /**
     * Method getPausaPersistence
     *
     * @return PausaPersistence instance
     */
    public PausaPersistence getPausaPersistence() {
        if (pausaPersistence == null) {
            pausaPersistence = new PausaPersistence();
        }

        return pausaPersistence;
    }

    /**
     *
     * @return PaymentPersistence instance
     */
    public TransactionPersistence getPaymentPersistence() {
        if (pausaPersistence == null) {
            transactionPersistence = new TransactionPersistence();
        }

        return transactionPersistence;
    }
}
