package com.wisemencouncil.app.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import java.security.Key
import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64

@Configuration
@EnableAspectJAutoProxy
class ApplicationConfig {
    @Bean
    fun jwtPrivateKey(@Value("\${jwt.private_key}") appSecret: String): Key {
        val parsedPrivateKey = Base64.getDecoder().decode(appSecret)
        val keySpecPKCS8 = PKCS8EncodedKeySpec(parsedPrivateKey)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePrivate(keySpecPKCS8)
    }
}
