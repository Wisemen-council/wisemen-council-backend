package com.wisemencouncil.app.security.jwt

import com.wisemencouncil.app.security.web.AuthorizationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import java.security.Key

fun createAndSignTokenFromClaims(claims: Map<String, Any?>, secret: Key): String = Jwts.builder()
        .setClaims(claims)
        .signWith(secret)
        .compact()

fun getClaimsFromToken(token: String, secret: Key): Claims {
    try {
        val parsedClaims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
        return parsedClaims.body
    } catch (e: MalformedJwtException) {
        throw AuthorizationException()
    }
}
