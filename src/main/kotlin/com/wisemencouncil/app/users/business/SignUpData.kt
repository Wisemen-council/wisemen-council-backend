package com.wisemencouncil.app.users.business

sealed class UserSignUpData

data class SignUpData(val email: String, val password: String, val full_name: String): UserSignUpData()

