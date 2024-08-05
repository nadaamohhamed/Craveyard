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
                        message=task.exception?.localizedMessage?:"Registering successfully...")

                } else {
                    isRegisteringLiveData.value=false
                    // TODO: fix showing this error
                    viewMessageLiveData.value= ViewMessage(
                        message="Something went wrong."
                    )
                }

            }
    }

    fun isValidInputs():Boolean{
        var isValid = true

//        1.	^                 # Start of the string.
//        2.	[a-zA-Z0-9._-]+   # Matches one or more of the following characters: lowercase letters, uppercase letters, digits, periods, underscores, or hyphens.
//        3.	@                 # Matches the @ symbol.
//        4.	[a-z]+            # Matches one or more lowercase letters.
//        5.	\\.               # Matches a literal period (since . is a special character in regex, we escape it with \\).
//        6.	[a-z]+            # Matches one or more lowercase letters.
//        7.	$                 # End of the string.

//          pattern -> one or more letter or digit or ._- >> @ >> one or more letter >> . >> one or more letter

        // email validation regex
        val email = emailLiveData.value
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (email.isNullOrBlank() || !email.matches(emailPattern.toRegex())){
            isValid = false
            emailError.value="Please enter a valid email address."
        }
        else{
            emailError.value=null
        }


//       1. ^                 # start-of-string
//       2. (?=.*[0-9])       # a digit must occur at least once
//       3. (?=.*[a-z])       # a lower case letter must occur at least once
//       4. (?=.*[A-Z])       # an upper case letter must occur at least once
//       5. (?=.*[!@#$%^&+=])  # a special character must occur at least once you can replace with your special characters
//       6.(?=\\S+$)          # no whitespace allowed in the entire string
//       7. .{6,}             # anything, at least six places though
//       8. $                 # end-of-string

//            pattern -> one or more digit >> one or more lowercase letter >> one or more uppercase letter >> one or more special character
//            >> no whitespace >> at least six places

        // password validation regex
        val password = passwordLiveData.value
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{6,}$";

        var validPassword = true
        if (password.isNullOrBlank() || !password.matches(passwordPattern.toRegex())){
            isValid = false
            validPassword = false
            passwordError.value="Please enter a valid password that contains at least: \n-1 uppercase letter \n-1 lowercase letter \n-1 digit \n-1 special character \nAnd at least 6 characters."
        }
        else{
            passwordError.value=null
        }

        // password confirmation validation
        val passwordConfirmation = passwordConfirmationLiveData.value
       if (validPassword && password != passwordConfirmation) {
            passwordConfirmationError.value = "Passwords doesn't match, please enter a valid password confirmation!"
            isValid = false
        } else {
            passwordConfirmationError.value = null
        }

        return isValid
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