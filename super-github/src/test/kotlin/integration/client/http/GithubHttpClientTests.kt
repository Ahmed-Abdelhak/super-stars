package integration.client.http

import com.github.kittinunf.fuel.core.Response
import com.redcare.pharmacy.integration.client.http.GithubHttpApiProperties
import com.redcare.pharmacy.integration.client.http.GithubHttpClient
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.jupiter.api.BeforeEach

class GithubHttpClientTest {

    private val githubHttpApiProperties: GithubHttpApiProperties = mockk()
    private lateinit var githubHttpClient: GithubHttpClient

    @BeforeEach
    fun setup() {
        githubHttpClient = GithubHttpClient(githubHttpApiProperties)
    }


    // not completed
    fun `getRepositories returns successful response`() {
        mockkStatic("com.github.kittinunf.fuel.Fuel")
        val url = "https://api.github.com/search/repositories?q=created:>2022-01-01&sort=stars&order=desc"
        val response: Response = mockk(relaxed = true)
    }

    // not completed
    fun `getRepositories returns failure response`() {
        mockkStatic("com.github.kittinunf.fuel.Fuel")
        val url = "https://api.github.com/search/repositories?q=created:>2022-01-01&sort=stars&order=desc"
        val response: Response = mockk(relaxed = true)
    }
}