package com.tset.release.manager

import com.tset.release.manager.domain.dto.ServiceDto
import org.springframework.stereotype.Service

@Service
class ReleaseVersionServiceImpl : ReleaseVersionService {
    override fun deploy(serviceDto: ServiceDto):Int{
        return 1
    }

    override fun getServices(version: Int): List<ServiceDto>{
        return emptyList<ServiceDto>()
    }
}