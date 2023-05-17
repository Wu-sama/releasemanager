package com.release.manager.data.access.repository

import com.release.manager.domain.model.SystemModel
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SystemRepository : CrudRepository<SystemModel, Long> {
    fun findByVersion(version: Int): SystemModel
    @Query("SELECT s FROM SystemModel s WHERE s.version = (select max(sm.version) FROM SystemModel sm)")
    fun findLatestSystemModel(): SystemModel?
}