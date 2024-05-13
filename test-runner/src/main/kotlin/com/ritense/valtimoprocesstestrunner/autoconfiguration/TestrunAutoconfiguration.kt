package com.ritense.valtimoprocesstestrunner.autoconfiguration

import com.ritense.valtimo.contract.config.LiquibaseMasterChangeLogLocation
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order

@AutoConfiguration
class TestrunAutoconfiguration {


    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    fun testrunLiquibaseMasterChangeLogLocation(): LiquibaseMasterChangeLogLocation {
        return LiquibaseMasterChangeLogLocation("config/liquibase/testrun-master.xml")
    }
}