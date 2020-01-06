package com.benchprofile.api.hardware.concept2

import java.time.OffsetDateTime
import java.util.*

class Node(
        val label: String,
        val id: UUID = UUID.randomUUID(),
        val createdAt: OffsetDateTime = OffsetDateTime.now()
)