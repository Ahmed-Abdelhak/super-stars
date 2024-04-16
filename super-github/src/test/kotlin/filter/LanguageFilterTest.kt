package filter

import com.redcare.pharmacy.common.model.GithubOwnerModel
import com.redcare.pharmacy.common.model.GithubRepositoryModel
import com.redcare.pharmacy.filter.FilterTypeLanguage
import com.redcare.pharmacy.filter.LanguageFilter
import org.hibernate.validator.internal.util.Contracts.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LanguageFilterTest {


    @Test
    fun `Should filter the repositories based on the language`() {
        assertTrue(
            LanguageFilter(FilterTypeLanguage.Go).filter(createGithubRepositoryModel("Go")),
            "Should return true"
        )
    }

    @Test
    fun `Should NOT filter the repositories if filter option disabled`() {
        assertTrue(LanguageFilter(null).filter(createGithubRepositoryModel("Go")), "Should return true")
    }

    private fun createGithubRepositoryModel(language: String): GithubRepositoryModel {
        return GithubRepositoryModel(
            1,
            "test",
            "test",
            "test",
            language,
            GithubOwnerModel("test", 1),
            "test",
        )
    }
}