package org.gotson.transitivebeandependencies

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

class MyServices

@ConditionalOnProperty("service.a")
@Service
class ServiceA {
    init {
        logger.info { "A SERVICE" }
    }
}

@ConditionalOnBean(ServiceA::class)
@ConditionalOnProperty("service.b")
@Service
class ServiceB(
        private val serviceA: ServiceA
) {
    init {
        logger.info { "B SERVICE depends on $serviceA" }
    }
}

@ConditionalOnBean(ServiceB::class)
@ConditionalOnProperty("service.c")
@Service
class ServiceC(
        private val serviceB: ServiceB
) {
    init {
        logger.info { "C Service depends on $serviceB" }
    }
}