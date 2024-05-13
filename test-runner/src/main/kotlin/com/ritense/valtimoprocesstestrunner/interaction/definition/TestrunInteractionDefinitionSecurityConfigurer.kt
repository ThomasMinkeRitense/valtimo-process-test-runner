package com.ritense.valtimoprocesstestrunner.interaction.definition

import com.ritense.valtimo.contract.authentication.AuthoritiesConstants.ADMIN
import com.ritense.valtimo.contract.security.config.HttpConfigurerConfigurationException
import com.ritense.valtimo.contract.security.config.HttpSecurityConfigurer
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher


class TestrunInteractionDefinitionSecurityConfigurer : HttpSecurityConfigurer {
    override fun configure(http: HttpSecurity) {
        try {
            http.authorizeHttpRequests { requests ->
                requests
                    .requestMatchers(antMatcher(HttpMethod.POST, "/api/management/v1/test-run/definition/{definitionId}/interaction-definition")).hasAuthority(ADMIN)
                    .requestMatchers(antMatcher(HttpMethod.GET, "/api/management/v1/test-run/definition/{definitionId}/interaction-definition")).hasAuthority(ADMIN)
            }
        } catch (e: Exception) {
            throw HttpConfigurerConfigurationException(e)
        }
    }
}