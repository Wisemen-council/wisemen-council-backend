package com.wisemencouncil.app.security.web

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Aspect
@Component
class AuthorizedInterceptor @Autowired constructor(private val sessionContext: SessionContext) {

    @Before("@within(authorized) && !@annotation(com.wisemencouncil.app.security.web.Authorized)")
    fun beforeClass(authorized: Authorized) {
        verifyAuthentication()
    }

    @Before("@annotation(authorized)")
    fun beforeMethod(authorized: Authorized) {
        verifyAuthentication()
    }

    private fun verifyAuthentication() {
        if (sessionContext.getCurrentUser() == null) {
            throw AuthorizationException()
        }
    }
}
