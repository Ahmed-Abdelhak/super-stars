package com.redcare.pharmacy.integration.client.http

import com.redcare.pharmacy.common.OrderType
import com.redcare.pharmacy.common.SortType
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "github.api")
data class GithubHttpApiProperties(
    val endpointUrl: String,
    val sinceDate: String = "2019-01-10",
    val orderType: OrderType = OrderType.DESC,
    val sortType: SortType = SortType.STARS,
    val listLimit: Int = 10
)
