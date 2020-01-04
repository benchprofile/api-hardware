package com.benchprofile.api.hardware.concept1

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

enum class Type(val clazz: Class<*>, val cast: (value: String) -> Any) {
    TEXT(String::class.java, { it }),
    URL(java.net.URL::class.java, { java.net.URL(it) })
}

@Entity
class Unit(
        @Column(nullable = false, columnDefinition = "text", unique = true)
        val unit: String,

        @Column(nullable = false, columnDefinition = "text")
        @Enumerated(EnumType.STRING)
        val type: Type,

        @OneToMany(mappedBy = "unit", fetch = FetchType.LAZY)
        val properties: Set<Property> = setOf(),

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        var id: UUID? = null
)
