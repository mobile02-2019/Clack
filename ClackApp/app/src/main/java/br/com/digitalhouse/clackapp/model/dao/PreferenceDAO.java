package br.com.digitalhouse.clackapp.model.dao;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.digitalhouse.clackapp.interfaces.RealtimeDatabasePreferenciaCall;
import br.com.digitalhouse.clackapp.model.dto.PreferenceDTO;

public class PreferenceDAO {

    private FirebaseDatabase database;
    private DatabaseReference reference;
    volatile PreferenceDTO preferenceDTO;
    //private PreferenceDBHelper dbHelper;

    public void save (PreferenceDTO users){
        DatabaseReference ref = reference.child(users.getDatabaseKey());
        ref.setValue(users);
    }

    public void load (String idUser, final RealtimeDatabasePreferenciaCall listener){
        database.getReference("Preferencias/" +idUser).limitToFirst(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                preferenceDTO = dataSnapshot.getValue(PreferenceDTO.class);
                listener.onDataChange(preferenceDTO);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onDataCanceled();
            }
        });
    }

}
