package com.example.craveyard.ui.auth.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.craveyard.data.db.MyDataBase
import com.example.craveyard.data.model.auth.User
import com.example.craveyard.ui.auth.login.events.LoginViewEvents
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.example.craveyard.data.model.auth.ViewMessage

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
                    Log.d("TAG", "signInWithEmail:success")
                    val user = task.result.user
                    getUserFromDataBase(user!!.uid)

                } else {
                    isLogin.value=false
                    viewMessageLiveData.value= ViewMessage(
                        message=task.exception?.localizedMessage?:"Somthing went wrong"
                    )
                }

            }
    }

    private fun getUserFromDataBase(uid:String) {
        Log.d("TAG", "getUserFromDataBase: $uid")
        MyDataBase.getUserFromDB(uid){
            if(it.isSuccessful){
                Log.d("TAG", "getUserFromDataBaseSuccess: $it")
                isLogin.value=false
                val user = it.result.toObject(User::class.java)
                Log.d("TAG", "getUserFromDataBase: $user")
                events.postValue(LoginViewEvents.navigateToHome(user!!))
            }
            else{
                Log.e("TAG", "Database fetch failed: ${it.exception?.localizedMessage}")
                viewMessageLiveData.postValue(
                    ViewMessage(
                        message = it.exception!!.localizedMessage,posActionName = "Ok")
                )
            }
        }
    }
    fun gotoRegisterClick(){
        events.postValue(LoginViewEvents.navigatToRegister())
    }

    fun isValidInputs():Boolean{
        var isvalid = true
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
        return isvalid
    }



}