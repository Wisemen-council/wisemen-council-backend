package com.wisemencouncil.app.users.business

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="users")
data class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var email: String,

        @Column(nullable = false)
        var password: String
) {
        @CreationTimestamp
        @Column(nullable = false, updatable = false)
        val createdAt: LocalDateTime? = null

        @UpdateTimestamp
        @Column(nullable = false)
        val updatedAt: LocalDateTime? = null

}
