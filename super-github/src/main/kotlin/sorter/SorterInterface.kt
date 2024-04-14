package org.example.sorter

import org.example.common.SuperModelInterface

interface SorterInterface<TModel: SuperModelInterface, TSorterType: SorterTypeInterface> {
    fun sort(models: List<TModel>, sorterType: SorterTypeInterface): List<TModel>
}