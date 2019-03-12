package com.github.dddpaul.spring;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * Restrict access to leaky /decrypt endpoint by default
     */
    @Configuration
    @Order(1)
    @ConditionalOnProperty(value = "security.endpoint.decrypt.enabled", matchIfMissing = true)
    public static class DenyDecryptEndpointAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/decrypt").denyAll();
        }
    }

    @Configuration
    public static class PermitAllAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().permitAll();
        }
    }
}
