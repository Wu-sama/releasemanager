package com.release.manager.controller

import com.release.manager.service.ReleaseVersionService
import com.release.manager.domain.dto.ServiceDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/release/version")
class ReleaseVersionController @Autowired constructor(private val releaseVersionService: ReleaseVersionService) {
    @PostMapping(value = ["/deploy"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun deploy(@RequestBody serviceDto: ServiceDto): Int {
        // it is better to wrap response in another class. This is better for front
        return releaseVersionService.deploy(serviceDto)
    }

    @GetMapping("/services")
    fun services(@RequestParam systemVersion: Int): List<ServiceDto> {
        return releaseVersionService.getServices(systemVersion)
    }
}