package org.gotson.transitivebeandependencies

import mu.KotlinLogging
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@SpringBootApplication
class TransitiveBeanDependenciesApplication

fun main(args: Array<String>) {
    SpringApplication.run(TransitiveBeanDependenciesApplication::class.java, *args)
}


@Component
class BeansLister(
        private val ctx: ApplicationContext
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        ctx.beanDefinitionNames.sorted()
                .forEach {
                    logger.info { "Bean loaded: $it" }
                }
    }
}