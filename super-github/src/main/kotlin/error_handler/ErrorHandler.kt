package com.redcare.pharmacy.error_handler

import com.redcare.pharmacy.common.model.GithubRepositoryModel
import com.redcare.pharmacy.integration.client.http.models.HttpClientError
import org.springframework.stereotype.Component

@Component
class ErrorHandler : ErrorHandlerInterface<HttpClientError> {
    override fun handle(
        modelList: List<GithubRepositoryModel>,
        error: HttpClientError
    ): List<GithubRepositoryModel> = modelList
}