package com.tset.release.manager.domain.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class ServiceDto(
    @field:NotBlank(message = "Name is mandatory")
    private val name:String,
    @field:NotNull
    @field:Positive(message = "Version should be positive")
    private val version: Int
)
