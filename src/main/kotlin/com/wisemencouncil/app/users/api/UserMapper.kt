package com.wisemencouncil.app.users.api

import com.wisemencouncil.app.users.business.User

fun mapVMToUser(userVM: UserVM) = User(null, userVM.email, userVM.password, userVM.full_name)
