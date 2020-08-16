package com.wisemencouncil.app.security

import com.wisemencouncil.app.security.jwt.JwtAuthorizationFilter
import com.wisemencouncil.app.security.jwt.JwtTokenManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@EnableWebSecurity
class WebSecurity @Autowired constructor(
        val tokenManager: JwtTokenManager
): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.cors()?.and()?.csrf()?.disable()
                ?.addFilter(JwtAuthorizationFilter(tokenManager, authenticationManager()))
                ?.sessionManagement()
                ?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

}
