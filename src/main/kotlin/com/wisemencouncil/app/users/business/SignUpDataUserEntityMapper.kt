package com.wisemencouncil.app.users.business

fun mapSignUpDataToUserEntity(signUpData: UserSignUpData): UserEntity = when(signUpData) {
    is SignUpData -> UserEntity(null, signUpData.email, signUpData.password, signUpData.full_name)
}
