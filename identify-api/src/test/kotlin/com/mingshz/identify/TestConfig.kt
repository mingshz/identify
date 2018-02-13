package com.mingshz.identify

import me.jiangcai.lib.test.config.H2DataSourceConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import javax.sql.DataSource

/**
 * @author CJ
 */
@Configuration
@ImportResource("classpath:/datasource_test.xml")
open class TestConfig: H2DataSourceConfig() {
    @Bean
    open fun dataSource(): DataSource {
        return memDataSource("identify")
    }
}