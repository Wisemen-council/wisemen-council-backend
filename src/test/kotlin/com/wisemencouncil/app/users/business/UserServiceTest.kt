package com.wisemencouncil.app.users.business

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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

    private val A_NAME = "Jon Doe"
    private val A_PASSWORD = "123456789"

    private val A_USER_NO_ID = User(null, AN_EMAIL, A_PASSWORD, A_NAME)
    private val A_USER_ENTITY_NO_ID = UserEntity(null, AN_EMAIL, A_PASSWORD, A_NAME)

    private val A_USER_ENTITY = UserEntity(1L, AN_EMAIL, A_PASSWORD, A_NAME)

    @Test
    fun createUser_callsRepository() {
        `when`(userRepository.save(A_USER_ENTITY_NO_ID)).thenReturn(A_USER_ENTITY)
        userService.createUser(A_USER_NO_ID)

        Mockito.verify(userRepository).save(A_USER_ENTITY_NO_ID)
    }

    @Test
    fun createUser_whenCreationSucceeds_returnsUser() {
        `when`(userRepository.save(A_USER_ENTITY_NO_ID)).thenReturn(A_USER_ENTITY)
        val user = userService.createUser(A_USER_NO_ID)

        assertThat(user).extracting("email", "password", "full_name")
                .containsExactly(A_USER_ENTITY.email, A_USER_ENTITY.password, A_USER_ENTITY.full_name)
    }

    @Test
    fun createUser_whenEmailIsInvalid_returnsSignUpException() {
        assertThrows<SignUpException> {
            val INVALID_SIGN_UP_DATA = User(null, "invalid_email", A_PASSWORD, A_NAME)
            userService.createUser(INVALID_SIGN_UP_DATA)
        }
    }

    @Test
    fun createUser_whenPasswordIsNotLongEnough_returnsSignUpException() {
        assertThrows<SignUpException> {
            val INVALID_SIGN_UP_DATA = User(null, AN_EMAIL, "", A_NAME)
            userService.createUser(INVALID_SIGN_UP_DATA)
        }
    }
}
