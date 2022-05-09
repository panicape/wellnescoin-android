package com.panicape.wellnesscoin.persistence;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.panicape.wellnesscoin.persistence.model.PausaRegistryDto;
import com.panicape.wellnesscoin.persistence.model.PausaTypeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * PausaPersistence class
 *
 * @author panicape
 * @version 1.01 november 2020
 */
public class PausaPersistence {

    /** Resource mDatabase */
    private FirebaseFirestore db;


    public PausaPersistence() {
        db = FirebaseFirestore.getInstance();
    }

// Methods

    public PausaTypeDTO getPausaTypeByName(String name) {
        PausaTypeDTO result;
        this.db.collection("PausaType").document("name")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        task.getResult().getData();
                    }
                });
        return null;
    }

    public PausaRegistryDto getPausaById() {
        return null;
    }

    public List<PausaRegistryDto> getPausaList() {
        return new ArrayList<PausaRegistryDto>();
    }

    public List<PausaTypeDTO> getPausaTypeList() {
        return new ArrayList<PausaTypeDTO>();
    }


//    public boolean createPausa(PausaRegistryDto pausaRegistry) {
//        HashMap<String, String> result = EntityFactory.populatePausaRegistryToHashmap(pausaRegistry);
//
//        if (pausaRegistry != null) {
//            mDatabase = FirebaseDatabase.getInstance().getReference();
//
//            mDatabase.child("PausaActivaRegistry").child("").setValue(result);
//        }
//        return false;
//    }

}
