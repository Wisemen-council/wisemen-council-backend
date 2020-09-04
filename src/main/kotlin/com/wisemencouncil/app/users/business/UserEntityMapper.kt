package com.wisemencouncil.app.users.business

fun mapEntityToUser(entity: UserEntity): User =
        if (entity.id != null) User(entity.id, entity.email, entity.password, entity.full_name)
        else throw RuntimeException("Missing ID in user")
