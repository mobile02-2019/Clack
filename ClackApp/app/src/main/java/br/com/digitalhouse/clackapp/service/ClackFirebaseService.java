package br.com.digitalhouse.clackapp.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class ClackFirebaseService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token){
        Log.d("Token", "Refreshed token");
    }

}