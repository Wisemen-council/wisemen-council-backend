package com.wisemencouncil.app.authentication.business

sealed class UserCredentials

data class EmailPasswordCredentials(val email: String, val password: String): UserCredentials()
