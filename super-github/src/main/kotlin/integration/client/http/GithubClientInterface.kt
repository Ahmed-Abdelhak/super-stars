package integration.client.http

import com.redcare.pharmacy.error_handler.ResponseInterface
import com.redcare.pharmacy.model.GithubRepositoryModel

interface GithubClientInterface {
    fun getRepositories(): ResponseInterface<GithubRepositoryModel>
}