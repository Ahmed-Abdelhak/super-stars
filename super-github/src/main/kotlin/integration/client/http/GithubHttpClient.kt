package com.redcare.pharmacy.integration.client.http

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.redcare.pharmacy.common.model.GithubRepositoryModel
import integration.client.http.GithubClientInterface
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}
private val NEXT_PAGE_PATTERN = Regex("<(.*?)>; rel=\"next\"")

@Component
class GithubHttpClient(
    private val githubHttpApiProperties: GithubHttpApiProperties
): GithubClientInterface {
    override fun getRepositories(): List<HttpClientResponse> {
        //TODO:  launch a coroutine to handle Asynchronous requests for pagination
       return doHttpCall()
    }

    private fun doHttpCall(): MutableList<HttpClientResponse> {
        var url = buildApiUrl()
        val pagesRemaining = true
        val httpClientResponses = mutableListOf<HttpClientResponse>()

        while (pagesRemaining) {
            val (_, response, result) = url.httpGet().response()

            httpClientResponses.add(
                when (result) {
                is Result.Failure -> buildHttpClientFailureResponse(result)
                is Result.Success -> buildHttpClientSuccessResponse(result)
            })

            val hasNextPage = response.headers["Link"].contains("rel=\"next\"")
            if (hasNextPage) {
                url = NEXT_PAGE_PATTERN.find(response.headers["Link"].first())?.groupValues?.get(1)!!
            }
        }
        return httpClientResponses
    }

    private fun buildHttpClientSuccessResponse(result: Result.Success<ByteArray>): HttpClientResponse {
        return HttpClientResponse(
            modelList = Gson().fromJson(result.get().decodeToString(), Array<GithubRepositoryModel>::class.java)
                .toList(),
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
        githubHttpApiProperties.endpointUrl +
                "?q=created:${githubHttpApiProperties.sinceDate}" +
                "&sort=${githubHttpApiProperties.sortType}" +
                "&order=${githubHttpApiProperties.orderType}" +
                "&per_page=${githubHttpApiProperties.listLimit}"
}