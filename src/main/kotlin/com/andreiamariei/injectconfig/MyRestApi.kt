package com.andreiamariei.injectconfig

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MyRestApi {

    @Autowired
    lateinit var myService: MyServiceInterface

    @GetMapping("/api")
    fun get() = myService.value()

}