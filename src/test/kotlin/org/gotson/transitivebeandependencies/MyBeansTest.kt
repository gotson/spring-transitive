package org.gotson.transitivebeandependencies

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(properties = ["bean.a=true"])
class MyBeansTest {

    @Autowired
    private lateinit var ctx: ApplicationContext

    @Test
    fun `all beans loaded when beanA is enabled`() {
        assertThat(ctx.beanDefinitionNames.asList()
        ).containsAll(listOf("beanA", "beanB", "beanC"))
    }
}

@RunWith(SpringRunner::class)
@SpringBootTest(properties = ["bean.a=false"])
class MyBeansTest2 {

    @Autowired
    private lateinit var ctx: ApplicationContext

    @Test
    fun `no beans loaded when beanA is not enabled`() {
        assertThat(ctx.beanDefinitionNames.asList())
                .doesNotContainAnyElementsOf(listOf("beanA", "beanB", "beanC"))
    }
}

@RunWith(SpringRunner::class)
@SpringBootTest(properties = ["bean.b=false"])
class MyBeansTest3 {

    @Autowired
    private lateinit var ctx: ApplicationContext

    @Test
    fun `only beanA is loaded when beanB is not enabled`() {
        assertThat(ctx.beanDefinitionNames.asList())
                .contains("beanA")
                .doesNotContainAnyElementsOf(listOf("beanB", "beanC"))
    }
}