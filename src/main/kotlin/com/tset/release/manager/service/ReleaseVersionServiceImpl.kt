package com.tset.release.manager.service

import com.tset.release.manager.data.access.repository.SystemRepository
import com.tset.release.manager.domain.dto.ServiceDto
import com.tset.release.manager.domain.dto.toModel
import com.tset.release.manager.domain.model.ServiceModel
import com.tset.release.manager.domain.model.SystemModel
import com.tset.release.manager.domain.model.toDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReleaseVersionServiceImpl @Autowired constructor(private val systemRepository: SystemRepository) :
    ReleaseVersionService {
    override fun deploy(serviceDto: ServiceDto): Int {
        return getSystemModel(serviceDto).version
    }

    private fun getSystemModel(serviceDto: ServiceDto): SystemModel {
        val latestSystemModel = systemRepository.findLatestSystemModel()
            ?: return systemRepository.save(SystemModel(version = 1, services = listOf(serviceDto.toModel())))
        val newService = latestSystemModel.services.singleOrNull { it.name == serviceDto.name }
        if (newService != null && newService.version == serviceDto.version) {
            return latestSystemModel
        }

        return systemRepository.save(
            SystemModel(
                version = 1,
                services = getNewServiceList(latestSystemModel.services, serviceDto, newService)
            )
        )
    }

    private fun getNewServiceList(
        services: List<ServiceModel>,
        serviceDto: ServiceDto,
        oldServiceModel: ServiceModel?
    ): List<ServiceModel> {
        val newServicesList = services.toMutableList()
        newServicesList.remove(oldServiceModel)
        newServicesList.add(serviceDto.toModel())
        return newServicesList
    }

    override fun getServices(version: Int): List<ServiceDto> {
        return systemRepository.findByVersion(version).services.map { it.toDto() }
    }
}