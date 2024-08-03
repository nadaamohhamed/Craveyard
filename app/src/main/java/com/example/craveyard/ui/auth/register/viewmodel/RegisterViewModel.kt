package com.example.craveyard.ui.auth.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.craveyard.ui.auth.register.events.RegisterViewEvents
import com.example.craveyard.data.db.MyDataBase
import com.example.craveyard.data.model.auth.User
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.example.craveyard.data.model.auth.ViewMessage


class RegisterViewModel : ViewModel() {


    val userNameLiveData = MutableLiveData<String?>()
    val userNameError = MutableLiveData<String?>()

    val emailLiveData = MutableLiveData<String?>()
    val emailError = MutableLiveData<String?>()

    val passwordLiveData = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()

    val passwordConfirmationLiveData = MutableLiveData<String?>()
    val passwordConfirmationError = MutableLiveData<String?>()

    val isRegisteringLiveData = MutableLiveData<Boolean>(false)

    val auth: FirebaseAuth =Firebase.auth
    val events = MutableLiveData<RegisterViewEvents>()
    val viewMessageLiveData = MutableLiveData<ViewMessage>()


    fun register() {
        if (isRegisteringLiveData.value == true) return
        if (!isValidInputs()) return
        isRegisteringLiveData.value = true

        auth.createUserWithEmailAndPassword(emailLiveData.value!!, passwordLiveData.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result.user
                    createUserInDB(user!!.uid)
                    viewMessageLiveData.value= ViewMessage(
                        message=task.exception?.localizedMessage?:"Registering is successfully")

                } else {
                    isRegisteringLiveData.value=false
                    viewMessageLiveData.value= ViewMessage(
                        message=task.exception?.localizedMessage?:"Something went wrong"
                    )
                }

            }
    }

    fun isValidInputs():Boolean{
        var isvalid = true
        if(userNameLiveData.value.isNullOrBlank()){
            userNameError.value="Please Enter User Name"
            isvalid=false
        }
        else{
            userNameError.value=null
        }
        if(emailLiveData.value.isNullOrBlank()){
            emailError.value="Please Enter Email"
            isvalid=false
        }
        else{
            emailError.value=null
        }
        if(passwordLiveData.value.isNullOrBlank()){
            passwordError.value="Please Enter Password"
            isvalid=false
        }
        else if(passwordLiveData.value!!.length<6){
            passwordError.value="Password too Short"
            isvalid=false
        }
        else{
            passwordError.value=null
        }
        if (passwordConfirmationLiveData.value.isNullOrBlank()) {
            passwordConfirmationError.value = "please Enter password confirmation"
            isvalid = false
        } else if (passwordLiveData.value!! != passwordConfirmationLiveData.value!!) {
            passwordConfirmationError.value = "password doesn't match"
            isvalid = false
        } else {
            passwordConfirmationError.value = null
        }
        return isvalid
    }

    private fun createUserInDB(uid:String){

        val user= User(uid,userNameLiveData.value!!,emailLiveData.value!!)
        MyDataBase.saveUserInDB(user){ task->
            if(task.isSuccessful){
                isRegisteringLiveData.value=false
                events.postValue(RegisterViewEvents.navigateToHome(user))
            }
            else{
                viewMessageLiveData.value= ViewMessage(message=task.exception!!.localizedMessage,
                    posActionName = "Ok")
            }
        }

    }


}