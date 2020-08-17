package com.wisemencouncil.app.users.business

fun mapEntityToUser(entity: UserEntity): User =
        if (entity.id != null) User(entity.id, entity.email, entity.password) else throw RuntimeException("Missing ID in user")
