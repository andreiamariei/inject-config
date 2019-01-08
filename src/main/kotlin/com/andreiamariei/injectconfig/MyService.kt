package com.andreiamariei.injectconfig

import com.andreiamariei.injectconfig.tech.ConfiguredImpl
import com.andreiamariei.injectconfig.tech.ConfiguredInterface

@ConfiguredInterface
interface MyServiceInterface {
    fun value(): String
}


@ConfiguredImpl
class MyServiceImplOne : MyServiceInterface {
    override fun value() = "ONE"
}

@ConfiguredImpl
class MyServiceImplTwo : MyServiceInterface {
    override fun value() = "TWO"
}