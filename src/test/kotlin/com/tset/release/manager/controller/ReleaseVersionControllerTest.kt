package com.tset.release.manager.controller

import org.junit.jupiter.api.Assertions.assertNotNull
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

    @Test
    fun deploy_service() {
        val request = "{name: \"Service B\", version: 1 }"
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.post("$URL/deploy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString
        assertNotNull(body)
    }

    @Test
    fun get_list_of_services() {
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/services")
                .contentType(MediaType.APPLICATION_JSON)
                .param("systemVersion", "1")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString
        assertNotNull(body)
    }
}