package com.release.manager.domain.model

import com.release.manager.domain.dto.ServiceDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "service_data")
data class ServiceModel(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0,
    val name: String = "",
    val version: Int = 0
)

fun ServiceModel.toDto() : ServiceDto = ServiceDto(name = this.name, version = this.version)
