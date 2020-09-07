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
    fun signUp(@RequestBody userVM: UserVM) {
        val user = mapVMToUser(userVM)
        userService.createUser(user)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SignUpException::class)
    fun handleUserSignUpException() {
    }
}
