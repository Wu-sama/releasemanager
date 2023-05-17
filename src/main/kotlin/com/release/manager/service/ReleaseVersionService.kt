package com.release.manager.service

import com.release.manager.domain.dto.ServiceDto

interface ReleaseVersionService {
    fun deploy(serviceDto: ServiceDto):Int
    fun getServices(version: Int): List<ServiceDto>
}