package com.benchprofile.api.hardware.concept1

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
class Property(
        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        val hardware: Hardware,

        @Column(nullable = false, columnDefinition = "text")
        val value: String,

        @ManyToOne(optional = false)
        val unit: Unit,

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        var id: UUID? = null
) {
    fun computedValue() = this.unit.type.cast(value)
}
