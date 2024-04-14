package org.example.fetcher

import org.example.common.SuperModelInterface

interface FetcherInterface<TModel: SuperModelInterface> {
    fun fetch(): List<TModel>
}