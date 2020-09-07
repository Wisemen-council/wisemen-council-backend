package com.wisemencouncil.app.users.business

import com.wisemencouncil.app.security.isUserSignUpDataValid
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
    fun createUser(user: User): User? {
        if (!isUserSignUpDataValid(user)) {
            throw SignUpException()
        }

        val entity = userRepository.save(mapUserToEntity(user))
        return mapEntityToUser(entity)
    }
}
