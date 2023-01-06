package com.rudione.tranquilhaven.utils

import android.util.Patterns


fun validEmail(email: String): RegisterValidation {
    if(email.isEmpty()) {
        return RegisterValidation.Failed("Email field is empty")
    }

    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return RegisterValidation.Failed("Wrong email type")
    }

    return RegisterValidation.Success
}

fun validPassword(password: String): RegisterValidation {

    if (password.isEmpty()) {
        return RegisterValidation.Failed("Password is empty")
    }

    if (password.length < 6) {
        return RegisterValidation.Failed("Password should contains 6 symbols")
    }

    return RegisterValidation.Success
}