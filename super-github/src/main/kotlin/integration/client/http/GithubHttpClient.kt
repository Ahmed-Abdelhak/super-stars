package com.redcare.pharmacy.integration.client.http

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.redcare.pharmacy.integration.client.http.models.HttpApiResponse
import com.redcare.pharmacy.integration.client.http.models.HttpClientError
import com.redcare.pharmacy.integration.client.http.models.HttpClientResponse
import com.redcare.pharmacy.integration.client.http.utils.UrlUtils
import com.redcare.pharmacy.integration.client.http.utils.hasNextPage
import com.redcare.pharmacy.integration.client.http.utils.hasRemainingRateLimit
import integration.client.http.GithubClientInterface
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class GithubHttpClient(
    private val githubHttpApiProperties: GithubHttpApiProperties
) : GithubClientInterface {
    override fun getRepositories(): List<HttpClientResponse> {
        //TODO:  launch a coroutine to handle Asynchronous requests for pagination
        return doHttpCall()
    }

    private fun doHttpCall(): List<HttpClientResponse> {
        var url = buildApiUrl()
        var hasNextPage = true
        var hasRemainingRateLimit = true
        var repositoriesCounter = 0
        val httpClientResponses = mutableListOf<HttpClientResponse>()

        while (hasNextPage &&
            hasRemainingRateLimit &&
            repositoriesCounter <= githubHttpApiProperties.listLimit.toInt()
        ) {
            val (_, response, result) = url.httpGet().response()

            httpClientResponses.add(
                when (result) {
                    is Result.Failure -> buildHttpClientFailureResponse(result)
                    is Result.Success -> buildHttpClientSuccessResponse(result)
                }
            )

            repositoriesCounter = httpClientResponses.sumOf { it.modelList.count()}
            hasNextPage = response.headers.hasNextPage()
            // a dirty way to handle rateLimiting, ideally we should have a rateLimiting component
            hasRemainingRateLimit = response.headers.hasRemainingRateLimit()

            if (hasNextPage) {
                url = UrlUtils.buildNextUrl(response)
            }
        }
        return httpClientResponses
    }

    private fun buildHttpClientSuccessResponse(result: Result.Success<ByteArray>): HttpClientResponse {
        return HttpClientResponse(
            modelList = Gson().fromJson(result.get().decodeToString(), HttpApiResponse::class.java)
                .items,
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
                "&order=${githubHttpApiProperties.orderType}"
}