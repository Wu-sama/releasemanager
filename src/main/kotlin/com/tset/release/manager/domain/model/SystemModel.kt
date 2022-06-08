package com.tset.release.manager.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "system_data")
data class SystemModel(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0,
    @Column(unique=true)
    val version:Int = 0,
    @OneToMany(fetch = FetchType.LAZY)
    val services: List<ServiceModel> = emptyList()
)