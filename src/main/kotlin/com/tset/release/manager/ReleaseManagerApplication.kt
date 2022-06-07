package com.tset.release.manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
 class ReleaseManagerApplication

fun main(args: Array<String>) {
    runApplication<ReleaseManagerApplication>(*args)
}