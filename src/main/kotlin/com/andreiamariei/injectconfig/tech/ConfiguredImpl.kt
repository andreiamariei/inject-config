package com.andreiamariei.injectconfig.tech

import org.springframework.stereotype.Component
import org.springframework.stereotype.Indexed

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Indexed
@Component
annotation class ConfiguredImpl {
}