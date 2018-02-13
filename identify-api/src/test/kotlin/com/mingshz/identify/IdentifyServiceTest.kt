package com.mingshz.identify

import me.jiangcai.lib.test.SpringWebTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

/**
 * @author CJ
 */
@ContextConfiguration(classes = [TestConfig::class,IdentifySpringConfig::class])
class IdentifyServiceTest: SpringWebTest() {

    @Autowired
    private lateinit var identifyService:IdentifyService

    @Test
    fun `this is a long desc for a test`(){
        println(identifyService)
    }
}