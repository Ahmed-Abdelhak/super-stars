package com.redcare.pharmacy.integration.client.http.utils

import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.Response

const val LINK_HEADERS = "Link"
const val RATE_LIMIT_REMAINING = "X-RateLimit-Remaining"

fun Headers.hasNextPage(): Boolean {
    return this[LINK_HEADERS].first().contains("rel=\"next\"")
}

fun Headers.hasRemainingRateLimit(): Boolean {
    return this[RATE_LIMIT_REMAINING].first().toInt() > 0
}

class UrlUtils {
    companion object UrlUtils {
        fun buildNextUrl(response: Response): String {
            response.headers[LINK_HEADERS].first().split(",").forEach {
                if (it.contains("rel=\"next\"")) {
                    return it.substring(it.indexOf("<") + 1, it.indexOf(">"))
                }
            }
            return ""
        }
    }
}

