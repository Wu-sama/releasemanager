package com.tset.release.manager.service

import com.tset.release.manager.data.access.repository.SystemRepository
import com.tset.release.manager.domain.dto.ServiceDto
import com.tset.release.manager.domain.dto.toModel
import com.tset.release.manager.domain.model.SystemModel
import com.tset.release.manager.domain.model.toDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReleaseVersionServiceImpl @Autowired constructor(private val systemRepository: SystemRepository) :
    ReleaseVersionService {
    override fun deploy(serviceDto: ServiceDto): Int {
        return systemRepository.save(SystemModel(version = 1, services = listOf(serviceDto.toModel()))).version
    }

    override fun getServices(version: Int): List<ServiceDto> {
        return systemRepository.findByVersion(version).services.map { it.toDto() }
    }
}