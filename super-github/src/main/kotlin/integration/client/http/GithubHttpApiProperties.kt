package com.redcare.pharmacy.integration.client.http

import com.redcare.pharmacy.common.OrderType
import com.redcare.pharmacy.sorter.SortType
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated

@ConfigurationProperties(prefix = "github.api")
data class GithubHttpApiProperties(
    val endpointUrl: String,
    val sinceDate: String = "2019-01-10",
    val orderType: OrderType = OrderType.DESC,
    val sortType: SortType = SortType.STARS,
    val listLimit: Int = 10
)
