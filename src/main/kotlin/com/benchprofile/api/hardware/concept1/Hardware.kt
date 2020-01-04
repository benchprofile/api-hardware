package com.benchprofile.api.hardware.concept1

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
class Hardware(
        @Column(nullable = false, columnDefinition = "text")
        val name: String,

        @OneToMany(mappedBy = "hardware", cascade = [CascadeType.PERSIST])
        val properties: MutableSet<Property> = mutableSetOf(),

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        var id: UUID? = null
)
