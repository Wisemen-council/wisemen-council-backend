package com.wisemencouncil.app.security.web

import com.wisemencouncil.app.users.business.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SessionContext {
    fun getCurrentUser(): User? {
        val authentication = SecurityContextHolder.getContext().authentication

        val principal = authentication.principal

        return if (principal is User) principal else null
    }
}
