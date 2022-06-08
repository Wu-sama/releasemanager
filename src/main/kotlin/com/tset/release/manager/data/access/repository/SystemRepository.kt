package com.tset.release.manager.data.access.repository

import com.tset.release.manager.domain.model.SystemModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SystemRepository : CrudRepository<SystemModel, Long> {
    fun findByVersion(version: Int): SystemModel
}