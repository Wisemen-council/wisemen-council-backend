package com.wisemencouncil.app.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthorizationFilter(
        private val tokenManager: JwtTokenManager,
        authenticationManager: AuthenticationManager
): BasicAuthenticationFilter(authenticationManager) {
    companion object {
        val HEADER_STRING = "Authorization"
        val TOKEN_PREFIX = "Bearer "
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader(HEADER_STRING)

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response)
            return
        }

        val token = header.replace(TOKEN_PREFIX, "")
        val user = tokenManager.createUserFromToken(token)

        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(user, null, emptyList())

        chain.doFilter(request, response)
    }
}
