package com.mingshz.identify

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * @author CJ
 */
@Configuration
@ComponentScan("com.mingshz.identify.service")
@EnableJpaRepositories("com.mingshz.identify.repository")
open class IdentifySpringConfig {

}