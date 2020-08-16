package com.wisemencouncil.app.authentication.business

interface AuthenticationService {
    fun authenticate(credentials: UserCredentials): Session
}
