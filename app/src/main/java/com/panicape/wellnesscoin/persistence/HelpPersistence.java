package com.panicape.wellnesscoin.persistence;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.panicape.wellnesscoin.persistence.model.HelpDto;

/**
 * Help Persistence class
 *
 * @author panicape
 * @version  1.01 November 2020
 */
public class HelpPersistence {

    /** databaseReference component */
    private DatabaseReference mDatabase;

    private FirebaseStorage firebaseStorage;



    // Methods

    /**
     *
     * @param help
     */
    public void createHelp(HelpDto help) {
        if (help != null) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("Help").child(help.getShortLabel())
                    .child("name").setValue(help.getName());
            mDatabase.child("Help").child(help.getShortLabel())
                    .child("dateCreation").setValue(help.getDateCreation());
            mDatabase.child("Help").child(help.getShortLabel())
                    .child("contentPath").setValue(help.getContentPath());
            mDatabase.child("Help").child(help.getShortLabel())
                    .child("shortLabel").setValue(help.getUserCreation());
            mDatabase.child("Help").child(help.getShortLabel())
                    .child("description").setValue(help.getDescription());
        }
    }

    public void replicateToFirestore() {
    }


    /**
     * TODO: getHelpList
     *
     */
    public void getHelp(String shortLabel) {
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}