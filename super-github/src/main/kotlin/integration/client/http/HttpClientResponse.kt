package com.redcare.pharmacy.integration.client.http

import com.redcare.pharmacy.error_handler.ErrorInterface
import com.redcare.pharmacy.error_handler.ResponseInterface
import com.redcare.pharmacy.common.model.GithubRepositoryModel

class HttpClientResponse(
    override val modelList: List<GithubRepositoryModel>,
    override val isSuccess: Boolean,
    override val error: ErrorInterface?
) : ResponseInterface<GithubRepositoryModel>