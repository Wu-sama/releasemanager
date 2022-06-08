package com.tset.release.manager

import com.tset.release.manager.data.access.repository.SystemRepository
import com.tset.release.manager.domain.model.SystemModel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@DataJpaTest
class SpringBootJPAIntegrationTest @Autowired constructor(private val systemRepository: SystemRepository) {
    @Test
    fun create_system_model() {
        val systemModelEntity = systemRepository.save(SystemModel())

        val foundEntity = systemRepository.findById(systemModelEntity.id)

        assertNotNull(foundEntity)
        assertNotEquals(0, systemModelEntity.id)
    }

    @Test
    fun find_system_model_by_version() {
        val systemModelEntity = systemRepository.save(SystemModel(version = 1))

        val foundEntity = systemRepository.findByVersion(systemModelEntity.version)

        assertNotNull(foundEntity)
        assertEquals(foundEntity, systemModelEntity)
        assertNotEquals(0, systemModelEntity.id)
    }
}