package com.example.chat.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(val uid:String?=null,
    val userName:String?=null,
    val email:String?=null,
    ):Parcelable
