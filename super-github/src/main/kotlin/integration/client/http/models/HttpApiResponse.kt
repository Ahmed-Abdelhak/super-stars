package com.redcare.pharmacy.integration.client.http.models

import com.redcare.pharmacy.common.model.GithubRepositoryModel

data class HttpApiResponse (
    val totalCount: Int,
    val items: List<GithubRepositoryModel>
)
