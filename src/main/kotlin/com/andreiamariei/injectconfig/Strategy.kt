package com.andreiamariei.injectconfig

interface Strategy {
    fun value(): String
}

enum class StrategyImpl : Strategy {

    ONE,
    TWO;

    override fun value() = name
}
