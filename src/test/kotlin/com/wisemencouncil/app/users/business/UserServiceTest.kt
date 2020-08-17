package com.wisemencouncil.app.users.business

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

internal class UserServiceTest {
    private val AN_EMAIL = "jon@example.com"

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun findByEmail_callsRepository() {

        userService.findByEmail(AN_EMAIL)

        Mockito.verify(userRepository).findByEmail(AN_EMAIL)
    }

    @Test
    fun findByEmail_whenUserExists_returnsUser() {
        val A_USER = UserEntity(1L, AN_EMAIL, "")
        `when`(userRepository.findByEmail(AN_EMAIL)).thenReturn(A_USER)

        val actual = userService.findByEmail(AN_EMAIL)

        assertThat(actual).extracting("email", "password")
                .containsExactly(A_USER.email, A_USER.password)
    }

    @Test
    fun findByEmail_whenUserDoesNotExist_returnsNull() {
        `when`(userRepository.findByEmail(AN_EMAIL)).thenReturn(null)

        val actual = userService.findByEmail(AN_EMAIL)

        assertThat(actual).isNull()
    }
}
