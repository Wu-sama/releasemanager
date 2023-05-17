package com.release.manager.domain.dto

import com.release.manager.domain.model.ServiceModel
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class ServiceDto(
    @field:NotBlank(message = "Name is mandatory")
    val name:String,
    @field:NotNull
    @field:Positive(message = "Version should be positive")
    val version: Int
)

fun ServiceDto.toModel() : ServiceModel = ServiceModel(name = this.name, version = this.version)