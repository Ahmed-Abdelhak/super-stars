package com.redcare.pharmacy.fetcher

import com.redcare.pharmacy.common.SuperModelInterface

interface FetcherInterface<TModel: SuperModelInterface> {
    fun fetch(): List<TModel>
}