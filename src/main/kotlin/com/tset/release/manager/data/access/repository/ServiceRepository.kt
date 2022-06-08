package com.tset.release.manager.data.access.repository

import com.tset.release.manager.domain.model.ServiceModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceRepository : CrudRepository<ServiceModel, Long>