package com.redcare.pharmacy.fetcher

import com.redcare.pharmacy.integration.client.http.GithubHttpClient
import com.redcare.pharmacy.model.GithubRepositoryModel
import org.springframework.stereotype.Service

@Service
class FetchService(
    private val githubHttpClient: GithubHttpClient
): FetcherInterface<GithubRepositoryModel> {
    override fun fetch(): List<GithubRepositoryModel> {
        TODO("Not yet implemented")
    }
}