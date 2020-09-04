package com.wisemencouncil.app.users.business

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
@SecondaryTable(name = "users_information", pkJoinColumns = [PrimaryKeyJoinColumn(name = "user_id")])
data class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var email: String,

        @Column(nullable = false)
        var password: String,

        @Column(nullable = false, table = "users_information")
        var full_name: String? = null
) {
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime? = null

    @UpdateTimestamp
    @Column(nullable = false)
    val updatedAt: LocalDateTime? = null
}
