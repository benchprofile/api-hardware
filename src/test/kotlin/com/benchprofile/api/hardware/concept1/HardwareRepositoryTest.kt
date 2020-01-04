package com.benchprofile.api.hardware.concept1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class HardwareRepositoryTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val hardwareRepository: HardwareRepository
) {
    @Test
    fun `When findByIdOrNull then return Hardware`() {
        val hardware = Hardware("Test")
        entityManager.persist(hardware)
        entityManager.flush()
        val found = hardwareRepository.findByIdOrNull(hardware.id!!)
        assertThat(found).isEqualTo(hardware)
    }
}
