package com.wisemencouncil.app.authentication.business

import com.wisemencouncil.app.security.JwtTokenManager
import com.wisemencouncil.app.security.isSameTextWhenHashed
import com.wisemencouncil.app.users.business.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JwtAuthenticationService @Autowired constructor(
        private val userService: UserService,
        private val tokenManager: JwtTokenManager
): AuthenticationService {

    override fun authenticate(credentials: UserCredentials): Session = when (credentials) {
        is EmailPasswordCredentials -> authenticateEmailPassword(credentials)
    }

    private fun authenticateEmailPassword(credentials: EmailPasswordCredentials): JwtSession {
        val user = userService.findByEmail(credentials.email) ?: throw AuthenticationException()

        if (!isSameTextWhenHashed(credentials.password, user.password)) {
            throw AuthenticationException()
        }

        val token = tokenManager.createTokenFromUser(user)
        return JwtSession(token)
    }
}
