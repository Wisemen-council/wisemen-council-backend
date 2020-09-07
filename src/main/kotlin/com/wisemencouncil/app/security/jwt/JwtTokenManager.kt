package com.wisemencouncil.app.security.jwt

import com.wisemencouncil.app.security.web.AuthorizationException
import com.wisemencouncil.app.users.business.User
import io.jsonwebtoken.MalformedJwtException
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

    fun createUserFromToken(token: String): User = try {
        val claims = getClaimsFromToken(token, appSecret)
        val userId = claims.get("userId", Long::class.javaObjectType)
        val userEmail = claims.get("userEmail", String::class.java)
        User(userId, userEmail, "")
    } catch (e: MalformedJwtException) {
        throw AuthorizationException()
    }
}
