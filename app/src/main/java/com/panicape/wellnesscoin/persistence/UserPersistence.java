package com.panicape.wellnesscoin.persistence;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    /** databaseReference component */
    private static  DatabaseReference mDatabase;




    // Constructor

    /**
     * UserPersistence Constructor
     */
    public UserPersistence() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }



    // Methods

    public List<UserDto> getUserList() {
        return new ArrayList<UserDto>();
    }

    public UserDto getUserByUsername(String username) {
        return null;
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
