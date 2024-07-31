package com.example.craveyard.data.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class User(val uid:String?=null,
    val userName:String?=null,
    val email:String?=null,
    ):Serializable
