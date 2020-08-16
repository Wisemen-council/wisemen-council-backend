package com.wisemencouncil.app.security

import io.jsonwebtoken.Jwts
import java.security.Key

fun createAndSignTokenFromClaims(claims: Map<String, Any?>, secret: Key): String = Jwts.builder()
        .setClaims(claims)
        .signWith(secret)
        .compact()
