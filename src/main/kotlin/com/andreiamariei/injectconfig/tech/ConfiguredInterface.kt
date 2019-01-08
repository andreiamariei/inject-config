package com.andreiamariei.injectconfig.tech

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import org.springframework.stereotype.Indexed

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Indexed
@Component
annotation class ConfiguredInterface {
}