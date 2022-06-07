package com.tset.release.manager.controller

import com.tset.release.manager.model.dto.ServiceDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/release/version")
class ReleaseVersionController {
    @PostMapping(value= ["/deploy"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun deploy(@RequestBody serviceDto: ServiceDto): Int { // it is better to wrap response in another class. This is better for front
        return 1
    }

    @GetMapping("/services")
    fun services(@RequestParam systemVersion: Int): List<ServiceDto> {
        return listOf<ServiceDto>()
    }
}