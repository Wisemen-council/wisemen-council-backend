package com.wisemencouncil.app.authentication.api

import com.wisemencouncil.app.authentication.business.EmailPasswordCredentials
import com.wisemencouncil.app.authentication.business.UserCredentials

fun mapVMToCredentials(credentialsVM: CredentialsVM): UserCredentials =
        EmailPasswordCredentials(credentialsVM.email, credentialsVM.password)
