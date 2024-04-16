package integration.client.http.utils

import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.Response
import com.redcare.pharmacy.integration.client.http.utils.*
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UrlExtensionsTest {

    @Test
    fun `hasNextPage returns true when Link header contains rel="next"`() {
        val headers = Headers().apply {
            append(LINK_HEADERS, "<https://api.github.com/user/repos?page=3&per_page=100>; rel=\"next\"")
        }

        assertTrue(headers.hasNextPage())
    }

    @Test
    fun `hasNextPage returns false when Link header does not contain rel="next"`() {
        val headers = Headers().apply {
            append(LINK_HEADERS, "<https://api.github.com/user/repos?page=3&per_page=100>; rel=\"last\"")
        }

        assertFalse(headers.hasNextPage())
    }

    @Test
    fun `hasRemainingRateLimit returns true when X-RateLimit-Remaining is greater than 0`() {
        val headers = Headers().apply {
            append(RATE_LIMIT_REMAINING, "1")
        }

        assertTrue(headers.hasRemainingRateLimit())
    }

    @Test
    fun `hasRemainingRateLimit returns false when X-RateLimit-Remaining is 0`() {
        val headers = Headers().apply {
            append(RATE_LIMIT_REMAINING, "0")
        }

        assertFalse(headers.hasRemainingRateLimit())
    }

    @Test
    fun `buildNextUrl returns next url when Link header contains rel="next"`() {
        val response: Response = mockk(relaxed = true)
        every { response.headers[LINK_HEADERS] } returns listOf("<https://api.github.com/user/repos?page=3&per_page=100>; rel=\"next\"")

        val actual = UrlUtils.buildNextUrl(response)

        assertEquals("https://api.github.com/user/repos?page=3&per_page=100", actual)
    }

    @Test
    fun `buildNextUrl returns empty string when Link header does not contain rel="next"`() {
        val response: Response = mockk(relaxed = true)
        every { response.headers[LINK_HEADERS] } returns listOf("<https://api.github.com/user/repos?page=3&per_page=100>; rel=\"last\"")

        val actual = UrlUtils.buildNextUrl(response)

        assertEquals("", actual)
    }
}