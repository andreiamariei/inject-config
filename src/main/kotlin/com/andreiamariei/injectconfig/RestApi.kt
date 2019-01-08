package com.andreiamariei.injectconfig

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RestApi {

    @Autowired
    lateinit var strategy: Strategy

    @GetMapping("/api")
    fun get() = strategy.value()

}