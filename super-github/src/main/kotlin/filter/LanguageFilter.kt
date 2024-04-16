package com.redcare.pharmacy.filter

import com.redcare.pharmacy.common.model.GithubRepositoryModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class LanguageFilter(
    @Value("\${github.repositories.filter-type}")
    private val filterType: FilterTypeLanguage
): FilterInterface<GithubRepositoryModel> {
    override fun filter(model: GithubRepositoryModel) =
        model.language == filterType.name

}