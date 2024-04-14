package com.redcare.pharmacy.sorter

import com.redcare.pharmacy.common.SuperModelInterface

interface SorterInterface<TModel: SuperModelInterface> {
    fun sort(models: List<TModel>, sorterType: SortType): List<TModel>
}