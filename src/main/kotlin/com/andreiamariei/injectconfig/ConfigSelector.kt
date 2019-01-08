package com.andreiamariei.injectconfig

import org.springframework.beans.factory.FactoryBean
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
class ConfigSelector : FactoryBean<Strategy> {
    override fun getObject(): Strategy? {
        return if (Math.random() < 0.5) {
            StrategyImpl.ONE
        } else {
            StrategyImpl.TWO
        }
    }

    override fun getObjectType(): Class<*>? {
        return Strategy::class.java
    }

    override fun isSingleton() = false
}