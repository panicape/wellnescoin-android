package com.panicape.wellnesscoin.persistence;

import com.google.firebase.database.DatabaseReference;
import com.panicape.wellnesscoin.persistence.model.TransactionsDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * PaymentPersistence Class
 *
 * @author panicape
 * @version 1.01 November 2020
 */
public class TransactionPersistence {

    /** Resource mDatabase */
    private DatabaseReference mDatabase;



    // Methods

    public List<TransactionsDto> getTransactionsFromUsername() {
        return new ArrayList<TransactionsDto>();
    }

    public List<TransactionsDto> getAllTransactionsOfUsername() {
        return new ArrayList<TransactionsDto>();
    }

    public List<TransactionsDto> getAllTransactions() {
        return new ArrayList<TransactionsDto>();
    }

    public HashMap<String, String> populateTransactionToHashMap (TransactionsDto transactionsDto) {
        if (transactionsDto != null) {
            HashMap<String, String> result = new HashMap<String, String>();
            result.put("date", transactionsDto.getDateCreation().toString());
            result.put("txType", transactionsDto.getTxType().toString());
            result.put("from", transactionsDto.getFrom());

            return result;
        }
        return null;
    }

    public TransactionsDto populateHashMapToTransaction (HashMap<String, String> input) throws ParseException {
        TransactionsDto result = new TransactionsDto();

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy-HHmmss");
        result.setDateCreation (sdf.parse(input.get("date")));
        result.setTxType (Integer.getInteger(input.get("txType")));
        result.setFrom (input.get("from"));

        return result;
    }




//    /**
//     *
//     * Method saveAskPayment.
//     * from--> person who has to pay
//     * to --> person who receives the payment
//     *
//     * @param transactionsDto
//     */
//    public void saveAskPayment(TransactionsDto transactionsDto) {
//        if (transactionsDto != null) {
//            HashMap<String, String> result = populateTransactionToHashMap(transactionsDto);
//
//            mDatabase = FirebaseDatabase.getInstance().getReference();
//            mDatabase.child("Transactions").child (result.get("from") + "_"
//                    + result.get("to")+"_"+result.get("date")).setValue(result);
//
//        }
//    }

//    /**
//     *
//     * @param transactionRef Path de la transaccion a actualizar a pagado
//     */
//    public void doPayment(String transactionRef) {
//        if (transactionRef != null) {
//            mDatabase = FirebaseDatabase.getInstance().getReference();
//            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot snapshot) {
//                    if (snapshot.child("Transactions").hasChild(transactionRef)) {
//                        // Realizar pago
//                        String from = snapshot.child("Transactions").child(transactionRef).child("from").getValue().toString();
//                        String to = snapshot.child("Transactions").child(transactionRef).child("to").getValue().toString();
//                        Integer amountTemp = Integer.valueOf(snapshot.child("Transactions").child(transactionRef).child("amount").getValue().toString());
//
//                        Integer amountFrom = Integer.valueOf(snapshot.child("Wallet").child(from).child("amount").getValue().toString());
//                        Integer amountTo = Integer.valueOf(snapshot.child("Wallet").child(to).child("amount").getValue().toString());
//
//                        if (amountFrom > amountTemp) {
//                            Integer newBalanceFrom = amountFrom - amountTemp;
//                            mDatabase.child("Wallet").child(from).child("amount").setValue(newBalanceFrom.toString());
//
//                            Integer newBalanceTo = amountTo + amountTemp;
//                            mDatabase.child("Wallet").child(to).child("amount").setValue(newBalanceTo.toString());
//
//                            // Cambiar estado de la transacción a pagado
//                            mDatabase.child("Transactions").child(transactionRef).child("status").setValue(ETransactionStatus.PAYED.getCode());
//                        }
//
//                    } else{
//                        System.out.println("TransactionPersistence.saveAskPayment: NoUserFound-> No se puede pedir el pago");
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    System.out.println("TransactionPersistence.saveAskPayment: Cancelado");
//                }
//            });
//        }
//    }

//    public void createWallet(WalletDto walletDto) {
//        Map<String, String> result = EntityFactory.populateWalletToHashMap(walletDto);
//
//        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                if (!snapshot.child("Wallet").hasChild(result.get("username"))) {
//                    mDatabase = FirebaseDatabase.getInstance().getReference();
//                    mDatabase.child("Wallet").child(result.get("username")).setValue(result.get("date"));
//                    mDatabase.child("Wallet").child(result.get("username")).setValue(result.get("balance"));
//                } else{
//                    System.out.println("createWallet: Ya existe el usuario");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                System.out.println("createWallet: Cancelado");
//            }
//        });
//    }

//    public void addBalance(TransactionsDto transactionsDto) {
//        if (transactionsDto.getTxType() == ETransactionType.BALANCE_UPDDATE.getCode()) {
//            HashMap<String, String> result = populateTransactionToHashMap(transactionsDto);
//
//            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot snapshot) {
//                    if (snapshot.child("Wallet").hasChild(result.get("from"))) {
//                        Integer balance = Integer.valueOf (snapshot.child ("Wallet").child(
//                                        result.get("from")).child("balance").getValue().toString());
//
//                        Integer newBalance = Integer.getInteger(result.get("balance"));
//                        Integer total = balance + newBalance;
//
//                        mDatabase = FirebaseDatabase.getInstance().getReference();
//                        mDatabase.child("Wallet").child(result.get("from")).child("balance").setValue(total);
//                    }else{
//                        // does not exist
//                        mDatabase = FirebaseDatabase.getInstance().getReference();
//                        mDatabase.child("Wallet").child(result.get("from")).child("balance").setValue(result.get("balance"));
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                }
//            });
//        }
//    }

}
