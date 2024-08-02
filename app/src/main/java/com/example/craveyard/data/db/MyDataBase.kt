package com.example.craveyard.data.db

import com.example.craveyard.data.model.auth.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore

object MyDataBase {

    const val nameOfCollection="users"

    fun saveUserInDB(user: User, onComplete:OnCompleteListener<Void>){
        Firebase.firestore
            .collection(nameOfCollection)
            .document(user.uid?:"")
            .set(user).addOnCompleteListener(onComplete)
    }

    fun getUserFromDB(uid:String,onComplete:OnCompleteListener<DocumentSnapshot>){
        Firebase.firestore
            .collection(nameOfCollection)
            .document(uid)
            .get()
            .addOnCompleteListener(onComplete)
    }

}