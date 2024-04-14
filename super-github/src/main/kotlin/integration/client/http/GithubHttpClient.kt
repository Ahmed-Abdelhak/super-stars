package com.redcare.pharmacy.integration.client.http

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.redcare.pharmacy.model.GithubRepositoryModel
import integration.client.http.GithubClientInterface
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class GithubHttpClient(
    private val githubHttpApiProperties: GithubHttpApiProperties
): GithubClientInterface {

    override fun getRepositories():HttpClientResponse {
        val (_, _, result) =
            buildApiUrl()
                .httpGet()
                .responseString()

        return when (result) {
            is Result.Failure -> buildHttpClientFailureResponse(result)

            is Result.Success -> buildHttpClientSuccessResponse(result)
        }
    }

    private fun buildHttpClientSuccessResponse(result: Result<String, FuelError>): HttpClientResponse {
        val data = result.get()
        return HttpClientResponse(
            modelList = Gson().fromJson(data, Array<GithubRepositoryModel>::class.java)
                .toList()
                .take(githubHttpApiProperties.listLimit), // could be handled by the API not in app memory
            isSuccess = true,
            error = null
        )
    }

    private fun buildHttpClientFailureResponse(result: Result.Failure<FuelError>): HttpClientResponse {
        logger.error {
            "Error while getting repositories using GithubHttpClient" +
                    "ErrorMessage: ${result.getException().message}"
        }
        return HttpClientResponse(
            modelList = listOf(),
            isSuccess = false,
            error = HttpClientError(
                errorMessage = result.getException().message
                    ?: "Unknown error while fetching from Github Api, Stacktrace: ${
                        result.getException().stackTraceToString()
                    }",
                errorCode = result.getException().response.statusCode
            )
        )
    }

    private fun buildApiUrl() =
        "${githubHttpApiProperties.endpointUrl}?q=created:${githubHttpApiProperties.sinceDate}&sort=${githubHttpApiProperties.sortType}&order=${githubHttpApiProperties.orderType}"

}