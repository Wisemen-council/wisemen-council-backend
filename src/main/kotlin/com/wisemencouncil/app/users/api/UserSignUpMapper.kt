package com.wisemencouncil.app.users.api

import com.wisemencouncil.app.users.business.SignUpData

fun mapVMToSignUpData(signUpDataVM: SignUpDataVM) =
        SignUpData(signUpDataVM.email, signUpDataVM.password, signUpDataVM.full_name)
