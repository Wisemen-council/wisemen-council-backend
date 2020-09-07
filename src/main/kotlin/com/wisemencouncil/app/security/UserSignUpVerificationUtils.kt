package com.wisemencouncil.app.security

import com.wisemencouncil.app.users.business.User
import java.util.regex.Pattern

fun isEmailValid(email: String): Boolean = Pattern.compile(
        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
).matcher(email).matches()

fun isPasswordLongEnough(password: String): Boolean = password.length >= 8

fun isUserSignUpDataValid(user: User): Boolean =
        isPasswordLongEnough(user.password) && isEmailValid(user.email)
