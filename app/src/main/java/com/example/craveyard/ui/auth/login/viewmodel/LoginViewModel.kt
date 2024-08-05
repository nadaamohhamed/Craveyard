package com.example.craveyard.ui.auth.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.craveyard.data.db.MyDataBase
import com.example.craveyard.data.model.auth.User
import com.example.craveyard.data.model.auth.ViewMessage
import com.example.craveyard.ui.auth.login.events.LoginViewEvents
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginViewModel : ViewModel(){
    val emailLiveData = MutableLiveData<String?>()
    val emailError = MutableLiveData<String?>()

    val passwordLiveData = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()

    var isLogin = MutableLiveData<Boolean>(false)

    val auth = Firebase.auth
    val viewMessageLiveData = MutableLiveData<ViewMessage>()

    val events = MutableLiveData<LoginViewEvents>()


    fun login() {
        if (isLogin.value == true) return
        if (!isValidInputs()) return
        isLogin.value = true
        auth.signInWithEmailAndPassword(emailLiveData.value!!, passwordLiveData.value!!)
            .addOnCompleteListener { task ->
                isLogin.value=true
                if (task.isSuccessful) {
                    val user = task.result.user
                    getUserFromDataBase(user!!.uid)

                } else {
                    isLogin.value=false

                    viewMessageLiveData.value= ViewMessage(
                        message=task.exception?.localizedMessage?:"Something went wrong."
                    )
                }

            }
    }

    private fun getUserFromDataBase(uid:String) {
        MyDataBase.getUserFromDB(uid){
            if(it.isSuccessful){
                isLogin.value=false
                val user = it.result.toObject(User::class.java)
                events.postValue(LoginViewEvents.navigateToHome(user!!))
            }
            else{
                viewMessageLiveData.postValue(
                    ViewMessage(
                        message = it.exception!!.localizedMessage,posActionName = "Ok")
                )
            }
        }
    }
    fun gotoRegisterClick(){
        events.postValue(LoginViewEvents.navigateToRegister())
    }

    fun isValidInputs():Boolean{
        var isValid = true

        // email validation for empty email
        val email = emailLiveData.value
        if (email.isNullOrBlank()){
            isValid = false
            emailError.value="Please enter a valid email address."
        }
        else{
            emailError.value=null
        }

        // password validation for empty password
        val password = passwordLiveData.value
        if (password.isNullOrBlank()){
            isValid = false
            passwordError.value="Please enter a valid password."
        }
        else{
            passwordError.value=null
        }

        return isValid
    }

    fun checkUserLogin():Boolean= Firebase.auth.currentUser!=null


}