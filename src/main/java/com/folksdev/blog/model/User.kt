package com.folksdev.blog.model

import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import javax.persistence.*


@Entity
@Table(name = "blog_user")
data class User @JvmOverloads constructor(
    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val name: String,
    val surname: String,
    @Column(unique = true)
    val username: String,
    @Column(unique = true)
    val email: String,
    val dateOfBirth: LocalDate,
    val gender: Gender,

    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(
        name = "users_groups",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "group_id", referencedColumnName = "group_id")]
    )
    val groups: Set<Group> = emptySet(),

    @OneToOne(mappedBy = "user", cascade = [CascadeType.REMOVE])
    val blog: Blog? = null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val comments: Set<Comment> = emptySet()

) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (username != other.username) return false
        if (email != other.email) return false
        if (dateOfBirth != other.dateOfBirth) return false
        if (gender != other.gender) return false
        if (groups != other.groups) return false
        if (blog != other.blog) return false
        if (comments != other.comments) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + surname.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + dateOfBirth.hashCode()
        result = 31 * result + gender.hashCode()
        return result
    }


}

enum class Gender {
    MALE, FEMALE, UNKNOWN
}