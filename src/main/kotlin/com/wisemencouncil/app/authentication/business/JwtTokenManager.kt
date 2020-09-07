package com.wisemencouncil.app.authentication.business

import com.wisemencouncil.app.security.createAndSignTokenFromClaims
import com.wisemencouncil.app.users.business.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.security.Key
import java.util.Date
import kotlin.collections.HashMap

@Service
class JwtTokenManager @Autowired constructor(private val appSecret: Key) {
    fun createTokenFromUser(user: User): String {
        if (user.id == null) throw AuthenticationException()

        val claims = HashMap<String, Any>()
        claims["iss"] = "Authentication API"
        claims["sub"] = "AccessToken"
        claims["issuedAt"] = Date().time
        claims["userId"] = user.id
        claims["userEmail"] = user.email

        return createAndSignTokenFromClaims(claims, appSecret)
    }
}
