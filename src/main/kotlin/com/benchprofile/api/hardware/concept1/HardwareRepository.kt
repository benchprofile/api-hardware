package com.benchprofile.api.hardware.concept1

import org.springframework.data.repository.CrudRepository
import java.util.*

interface HardwareRepository : CrudRepository<Hardware, UUID>
