package com.tset.release.manager.service

import com.tset.release.manager.domain.dto.ServiceDto

interface ReleaseVersionService {
    fun deploy(serviceDto: ServiceDto):Int
    fun getServices(version: Int): List<ServiceDto>
}