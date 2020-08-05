package com.wisemencouncil.app.dummy

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DummyController {

    @GetMapping("/")
    fun helloWorld(): String {
        return "Hello World"
    }
}
