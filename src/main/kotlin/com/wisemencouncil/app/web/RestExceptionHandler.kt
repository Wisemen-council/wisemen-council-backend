package com.wisemencouncil.app.web

import com.wisemencouncil.app.security.web.AuthorizationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(RuntimeException::class)
    @ResponseBody
    fun handleRuntimeException(exception: RuntimeException): ResponseEntity<Unit> {
        val statusCode = getHttpStatusCode(exception)
        return ResponseEntity(statusCode)
    }

    private fun getHttpStatusCode(exception: RuntimeException): HttpStatus = when (exception) {
        is AuthorizationException -> HttpStatus.FORBIDDEN
        else -> HttpStatus.INTERNAL_SERVER_ERROR
    }
}
