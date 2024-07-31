package com.example.craveyard

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:error")
fun setErrorToTil(
    textInputLayout: TextInputLayout,
    errorText : String?,
){
    textInputLayout.error=errorText
}