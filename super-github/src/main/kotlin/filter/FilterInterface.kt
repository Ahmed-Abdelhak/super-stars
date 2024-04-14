package org.example.filter

import org.example.common.SuperModelInterface
import org.example.sorter.SorterTypeInterface

interface FilterInterface<TModel: SuperModelInterface, TFilterType: FilterTypeInterface> {
    fun filter(models: List<TModel>, filterType: TFilterType): List<TModel>
}