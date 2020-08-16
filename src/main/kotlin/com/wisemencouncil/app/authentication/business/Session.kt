package com.wisemencouncil.app.authentication.business

sealed class Session

data class JwtSession(val token: String): Session()
