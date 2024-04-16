package com.redcare.pharmacy.integration.client.http

import com.redcare.pharmacy.common.OrderType
import com.redcare.pharmacy.common.SortType
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "github.api")
data class GithubHttpApiProperties(
    val endpointUrl: String,
    val sinceDate: String,
    val orderType: String,
    val sortType: String,
    val listLimit: String
)
