package com.andreiamariei.injectconfig.tech

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "request")
annotation class ConfiguredBean {
}