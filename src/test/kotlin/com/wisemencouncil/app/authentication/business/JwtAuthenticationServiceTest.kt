package com.wisemencouncil.app.authentication.business

import com.wisemencouncil.app.security.jwt.JwtTokenManager
import com.wisemencouncil.app.security.encrypt.isSameTextWhenHashed
import com.wisemencouncil.app.users.business.User
import com.wisemencouncil.app.users.business.UserService
import io.mockk.every
import io.mockk.mockkStatic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

internal class JwtAuthenticationServiceTest {
    private val AN_EMAIL = "jon@example.com"
    private val A_PASSWORD = "1234567890"

    @Mock
    private lateinit var userService: UserService

    @Mock
    private lateinit var tokenManager: JwtTokenManager

    @InjectMocks
    private lateinit var jwtAuthenticationService: JwtAuthenticationService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockkStatic("com.wisemencouncil.app.security.encrypt.TextHashUtilsKt")
    }

    @Test
    fun authenticate_withEmailPasswordAndEmailDoesNotExist_ThrowsAuthenticationException() {
        `when`(userService.findByEmail(AN_EMAIL)).thenReturn(null)

        val credentials = EmailPasswordCredentials(AN_EMAIL, A_PASSWORD)
        assertThrows<AuthenticationException> { jwtAuthenticationService.authenticate(credentials) }
    }

    @Test
    fun authenticate_withEmailPasswordAndPasswordDoesNotMatch_ThrowsAuthenticationException() {
        val user = User(1L, AN_EMAIL, "hashed_password")
        `when`(userService.findByEmail(AN_EMAIL)).thenReturn(user)

        every {
            isSameTextWhenHashed(A_PASSWORD, user.password)
        } returns false

        val credentials = EmailPasswordCredentials(AN_EMAIL, A_PASSWORD)
        assertThrows<AuthenticationException> { jwtAuthenticationService.authenticate(credentials) }
    }

    @Test
    fun authenticate_withEmailPasswordAndCredentialsAreCorrect_ReturnsJwtSession() {
        val user = User(1L, AN_EMAIL, "hashed_password")
        `when`(userService.findByEmail(AN_EMAIL)).thenReturn(user)

        every {
            isSameTextWhenHashed(A_PASSWORD, user.password)
        } returns true

        val token = "MY_TOKEN"
        `when`(tokenManager.createTokenFromUser(user)).thenReturn(token)

        val credentials = EmailPasswordCredentials(AN_EMAIL, A_PASSWORD)
        val actual = jwtAuthenticationService.authenticate(credentials)

        assertThat(actual).extracting("token")
                .containsExactly(token)
    }
}
