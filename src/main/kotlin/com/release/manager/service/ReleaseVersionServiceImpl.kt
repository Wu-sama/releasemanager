package com.release.manager.service

import com.release.manager.data.access.repository.SystemRepository
import com.release.manager.domain.dto.ServiceDto
import com.release.manager.domain.dto.toModel
import com.release.manager.domain.model.ServiceModel
import com.release.manager.domain.model.SystemModel
import com.release.manager.domain.model.toDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReleaseVersionServiceImpl @Autowired constructor(private val systemRepository: SystemRepository) :
    ReleaseVersionService {
    @Transactional
    override fun deploy(serviceDto: ServiceDto): Int {
        return getSystemModel(serviceDto).version
    }

    // todo refactoring
    private fun getSystemModel(serviceDto: ServiceDto): SystemModel {
        val latestSystemModel = systemRepository.findLatestSystemModel()
        val (version, serviceModel) = getSystemData(latestSystemModel, serviceDto)

        if(version == latestSystemModel?.version){
            return latestSystemModel
        }

        return systemRepository.save(
            SystemModel(
                id = latestSystemModel?.id ?: 0,
                version = version,
                services = getNewServiceList(latestSystemModel?.services, serviceModel)
            )
        )
    }

    private fun getSystemData(latestSystemModel: SystemModel?, serviceDto: ServiceDto): Pair<Int, ServiceModel>{
        var version = 1
        var serviceModel = serviceDto.toModel()
        latestSystemModel?.let {
            val newService = latestSystemModel.services.singleOrNull { it.name == serviceDto.name }
            if (newService != null && newService.version == serviceDto.version) {
                return latestSystemModel.version to serviceModel
            }
            serviceModel = serviceModel.copy(id = newService?.id ?: 0)
            version += latestSystemModel.version
        }
        return version to serviceModel
    }

    private fun getNewServiceList(
        services: List<ServiceModel>?,
        model: ServiceModel
    ): List<ServiceModel> {
        val mutableServices = services?.toMutableList() ?: mutableListOf()
        mutableServices.removeIf{it.id == model.id}
        mutableServices.add(model)
        return mutableServices
    }

    override fun getServices(version: Int): List<ServiceDto> {
        return systemRepository.findByVersion(version).services.map { it.toDto() }
    }
}