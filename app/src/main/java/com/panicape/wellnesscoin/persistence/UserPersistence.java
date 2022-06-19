package com.panicape.wellnesscoin.persistence;

import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.core.FirestoreClient;
import com.panicape.wellnesscoin.persistence.model.UserDto;

import java.util.ArrayList;
import java.util.List;

/**
 * UserPersistence class
 *
 * @author panicape
 * @version 1.01 November 2020
 */
public class UserPersistence {

    List<UserDto> result;

    UserDto resultSingle;

    private static FirestoreClient firestoreClient;
    private static FirebaseFirestore firestoreDb;




    // Constructor

    /**
     * UserPersistence Constructor
     */
    public UserPersistence() {
        firestoreDb = FirebaseFirestore.getInstance();
    }



    // Methods

    public List<UserDto> getUserList() {
        return new ArrayList<UserDto>();
    }

    public UserDto getUserByUsername(String username) {
        return null;
    }

    public UserDto getUserByEmail(String email) throws IllegalStateException, RuntimeExecutionException {
        // Create a reference to the cities collection

//        DocumentReference docRef = firestoreDb.collection("Users").document(email.split("@")[0]);
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//
//                        Log.d("getUserByEmail", "DocumentSnapshot data: " + document.getData());
//                    } else {
//                        Log.d("getUserByEmail", "No such document");
//                    }
//                } else {
//                    Log.d("getUserByEmail", "get failed with ", task.getException());
//                }
//            }
//        });

        return resultSingle;
    }

//    public boolean updateUser(Map<String, String> userAtrList) throws NoSuchAlgorithmException {
//        if (userAtrList != null) {
//            if (userAtrList.containsKey("mail") && userAtrList.containsKey("username")) {
//                mDatabase.child("Users").child (userAtrList.get("username")).setValue(userAtrList);
//
//                return true;
//            } else {
//                return false;
//            }
//
//        }
//
//        return false;
//    }

//    public static boolean testCreateUser() {
//        HashMap<String, String> userAtrList = new HashMap<String, String>();
//        userAtrList.put("username", "panicape");
//        userAtrList.put("mail", "panicape@hotmail.com");
//
//        if (userAtrList != null) {
//            if (userAtrList.containsKey("mail") && userAtrList.containsKey("username")) {
//                mDatabase.child("Users").setValue(userAtrList);
//
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

//    public static boolean testUpdateUser() throws NoSuchAlgorithmException {
//        Map<String, String> userAtrList = new HashMap<String, String>();
//
//        userAtrList.put("username","panicape");
//        userAtrList.put("mail","panicape");
//
//        if (userAtrList != null) {
//            if (userAtrList.containsKey("mail") && userAtrList.containsKey("username")) {
//                mDatabase.child ("Users").child (userAtrList.get("username")).setValue(userAtrList);
//
//                return true;
//            } else {
//                return false;
//            }
//        }
//
//        return false;
//    }
}
