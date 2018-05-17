package org.gotson.transitivebeandependencies

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(properties = ["service.a=true"])
class MyServicesTest {

    @Autowired
    private lateinit var ctx: ApplicationContext

    @Test
    fun `all services loaded when serviceA is enabled`() {
        assertThat(ctx.beanDefinitionNames.asList())
                .containsAll(listOf("serviceA", "serviceB", "serviceC"))
    }
}

@RunWith(SpringRunner::class)
@SpringBootTest(properties = ["service.a=false"])
class MyServicesTest2 {

    @Autowired
    private lateinit var ctx: ApplicationContext

    @Test
    fun `no services loaded when serviceA is not enabled`() {
        assertThat(ctx.beanDefinitionNames.asList())
                .doesNotContainAnyElementsOf(listOf("serviceA", "serviceB", "serviceC"))
    }
}

@RunWith(SpringRunner::class)
@SpringBootTest(properties = ["service.b=false"])
class MyServicesTest3 {

    @Autowired
    private lateinit var ctx: ApplicationContext

    @Test
    fun `only serviceA is loaded when serviceB is not enabled`() {
        assertThat(ctx.beanDefinitionNames.asList())
                .contains("serviceA")
                .doesNotContainAnyElementsOf(listOf("serviceB", "serviceC"))
    }
}