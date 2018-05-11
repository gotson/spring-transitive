package org.gotson.transitivebeandependencies

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

private val logger = KotlinLogging.logger {}

@Configuration
class MyBeans {

    @ConditionalOnProperty("bean.a")
    @Bean
    fun beanA(): BeanA {
        logger.info { "A BEAN" }
        return BeanA("beanA")
    }

    @ConditionalOnBean(BeanA::class)
    @ConditionalOnProperty("bean.b")
    @Bean
    fun beanB(beanA: BeanA): BeanB {
        logger.info { "B BEAN depends on $beanA" }
        return BeanB("beanB")
    }

    @ConditionalOnBean(BeanB::class)
    @ConditionalOnProperty("bean.c")
    @Bean
    fun beanC(beanB: BeanB): BeanC {
        logger.info { "C BEAN depends on $beanB" }
        return BeanC("beanC")
    }

}

data class BeanA(val name: String)
data class BeanB(val name: String)
data class BeanC(val name: String)
