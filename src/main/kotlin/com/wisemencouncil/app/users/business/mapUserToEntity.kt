package com.wisemencouncil.app.users.business

fun mapUserToEntity(user: User): UserEntity = UserEntity(null, user.email, user.password, user.full_name)
