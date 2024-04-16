package com.redcare.pharmacy.integration.client.http.models

import com.redcare.pharmacy.error_handler.ResponseInterface
import com.redcare.pharmacy.common.model.GithubRepositoryModel

data class HttpClientResponse(
    override val modelList: List<GithubRepositoryModel>,
    override val isSuccess: Boolean,
    override val error: HttpClientError?
) : ResponseInterface<GithubRepositoryModel>