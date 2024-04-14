package com.redcare.pharmacy

import com.redcare.pharmacy.integration.client.http.GithubHttpApiProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(GithubHttpApiProperties::class)
open class SuperGithubApplication
fun main(args: Array<String>) {
    runApplication<SuperGithubApplication>(*args)
}
