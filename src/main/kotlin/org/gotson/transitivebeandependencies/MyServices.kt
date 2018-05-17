package org.gotson.transitivebeandependencies

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

class MyServices

@ConditionalOnProperty("service.a")
@Component
class ServiceA {
    init {
        logger.info { "A SERVICE" }
    }
}

@ConditionalOnBean(ServiceA::class)
@ConditionalOnProperty("service.b")
@Component
class ServiceB(
        private val serviceA: ServiceA
) {
    init {
        logger.info { "B SERVICE depends on $serviceA" }
    }
}

@ConditionalOnBean(ServiceB::class)
@ConditionalOnProperty("service.c")
@Component
class ServiceC(
        private val serviceB: ServiceB
) {
    init {
        logger.info { "C Service depends on $serviceB" }
    }
}