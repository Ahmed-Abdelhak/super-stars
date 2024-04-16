package com.redcare.pharmacy.main

import com.redcare.pharmacy.common.model.GithubRepositoryModel
import com.redcare.pharmacy.error_handler.ErrorHandlerInterface
import com.redcare.pharmacy.filter.FilterInterface
import com.redcare.pharmacy.integration.client.http.GithubHttpClient
import com.redcare.pharmacy.integration.client.http.models.HttpClientError
import org.springframework.stereotype.Service

@Service
class GithubService(
    private val githubClient: GithubHttpClient,
    private val filter: FilterInterface<GithubRepositoryModel>,
    private val errorHandler: ErrorHandlerInterface<HttpClientError>
) {
    fun getPopularRepositories(): List<GithubRepositoryModel> =
        githubClient.getRepositories()
            .flatMap {
                response ->
                if (response.isSuccess) {
                    response.modelList.filter {
                        filter.filter(it)
                    }
                } else {
                    errorHandler.handle(response.modelList, response.error!!)
                }
            }
}

