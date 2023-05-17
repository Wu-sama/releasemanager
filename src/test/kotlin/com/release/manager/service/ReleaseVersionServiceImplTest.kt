package com.release.manager.service

import com.ninjasquad.springmockk.MockkBean
import com.release.manager.data.access.repository.SystemRepository
import com.release.manager.domain.dto.ServiceDto
import com.release.manager.domain.dto.toModel
import com.release.manager.domain.model.SystemModel
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [ReleaseVersionServiceImpl::class])
internal class ReleaseVersionServiceImplTest @Autowired constructor(private val releaseVersionService: ReleaseVersionService) {

    @MockkBean
    lateinit var repository: SystemRepository

    @Test
    fun deploy() {
        val serviceDto = ServiceDto(name = "Service A", version = 1)
        val system = SystemModel(id = 1, version = 1, services = listOf(serviceDto.toModel()))
        every { repository.findLatestSystemModel() } returns null
        every { repository.save(any()) } returns system

        val version = releaseVersionService.deploy(serviceDto)

        Assertions.assertEquals(1, version)
        verify(exactly = 1) { repository.save(system.copy(id = 0)) }
    }

    @Test
    fun getServices() {
    }
}