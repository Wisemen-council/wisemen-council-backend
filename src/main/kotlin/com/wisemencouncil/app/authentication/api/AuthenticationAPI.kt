package com.wisemencouncil.app.authentication.api

import com.wisemencouncil.app.authentication.business.AuthenticationException
import com.wisemencouncil.app.authentication.business.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthenticationAPI @Autowired constructor(
        private val authenticationService: AuthenticationService
) {

    @PostMapping("/")
    fun authenticate(@RequestBody credentialsVM: CredentialsVM): SessionVM {
        val credentials = mapVMToCredentials(credentialsVM)
        return mapSessionToVM(authenticationService.authenticate(credentials))
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException::class)
    fun handlerUserAuthenticationException() {
    }

}
