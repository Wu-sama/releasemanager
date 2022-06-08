package com.tset.release.manager.controller

import com.ninjasquad.springmockk.MockkBean
import com.tset.release.manager.ReleaseVersionService
import com.tset.release.manager.domain.dto.ServiceDto
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(ReleaseVersionController::class)
internal class ReleaseVersionControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {
    private val URL = "/release/version"

    @MockkBean
    lateinit var releaseVersionService: ReleaseVersionService

    @Test
    fun deploy_service() {
        every { releaseVersionService.deploy(any()) } returns 1

        val request = "{\"name\": \"Service B\", \"version\": 1 }"
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("$URL/deploy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString
        assertNotNull(body)
        verify(exactly = 1) { releaseVersionService.deploy(any()) }
    }

    @Test
    fun get_list_of_services() {
        every { releaseVersionService.getServices(any()) } returns listOf<ServiceDto>(
            ServiceDto(
                name = "Service A",
                version = 1
            )
        )

        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/services")
                .contentType(MediaType.APPLICATION_JSON)
                .param("systemVersion", "1")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString

        assertNotNull(body)
        assertTrue(body.contains("Service A"))
        verify(exactly = 1) { releaseVersionService.getServices(any()) }
    }
}