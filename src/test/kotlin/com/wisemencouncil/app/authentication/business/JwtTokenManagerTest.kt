package com.wisemencouncil.app.authentication.business

import com.wisemencouncil.app.security.createAndSignTokenFromClaims
import com.wisemencouncil.app.users.business.User
import io.mockk.mockkStatic
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.security.Key

internal class JwtTokenManagerTest {
    private val A_TOKEN = "MY_TOKEN"

    @Mock
    private lateinit var appSecret: Key

    @InjectMocks
    private lateinit var tokenManager: JwtTokenManager

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockkStatic("com.wisemencouncil.app.security.JwtUtilsKt")
    }

    @Test
    fun createTokenFromUser_returnsASignedToken() {
        val user = User(1L, "jon@example.com", "")

        every {
            createAndSignTokenFromClaims(any(), eq(appSecret))
        } returns A_TOKEN


        val actual = tokenManager.createTokenFromUser(user)

        assertThat(actual).isEqualTo(A_TOKEN)
    }

    @Test
    fun createTokenFromUser_callsJwtUtilsWithRightClaims() {
        val user = User(1L, "jon@example.com", "")

        every {
            createAndSignTokenFromClaims(
                    claims = any(),
                    secret = eq(appSecret)
            )
        } returns A_TOKEN


        tokenManager.createTokenFromUser(user)

        val claims = slot<HashMap<String, Any>>()
        verify {
            createAndSignTokenFromClaims(
                    claims = capture(claims),
                    secret = appSecret
            )
        }

        assertThat(claims.captured).contains(
                entry("iss", "Authentication API"),
                entry("sub", "AccessToken"),
                entry("userId", user.id),
                entry("userEmail", user.email)
        )
    }
}
