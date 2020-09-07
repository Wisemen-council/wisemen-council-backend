package com.wisemencouncil.app.users.business

data class User(val id: Long? = null, val email: String, val password: String, val full_name: String? = null)
