package com.wisemencouncil.app.users.api

import com.wisemencouncil.app.users.business.SignUpException
import com.wisemencouncil.app.users.business.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UsersAPI @Autowired constructor(
        private val userService: UserService
) {
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@RequestBody signUpDataVM: SignUpDataVM) {
        val signUpData = mapVMToSignUpData(signUpDataVM)
        userService.createUser(signUpData)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SignUpException::class)
    fun handleUserSignUpException() {
    }
}
