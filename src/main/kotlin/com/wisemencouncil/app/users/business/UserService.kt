package com.wisemencouncil.app.users.business

import com.wisemencouncil.app.security.isSignUpDataValid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService @Autowired constructor(private val userRepository: UserRepository) {

    @Transactional
    fun findByEmail(email: String): User? {
        val entity = userRepository.findByEmail(email)

        if (entity == null) {
            return entity
        }

        return mapEntityToUser(entity)
    }

    @Transactional
    fun createUser(signUpData: SignUpData): User? {
        if (!isSignUpDataValid(signUpData)) {
            throw SignUpException()
        }

        val entity = userRepository.save(mapSignUpDataToUserEntity(signUpData))
        return mapEntityToUser(entity)
    }
}
