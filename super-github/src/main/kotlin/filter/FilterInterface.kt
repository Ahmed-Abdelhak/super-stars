package com.redcare.pharmacy.filter

import com.redcare.pharmacy.common.model.SuperModelInterface

interface FilterInterface<TModel: SuperModelInterface, TFilterType: FilterTypeInterface> {
    fun filter(models: List<TModel>, filterType: TFilterType): List<TModel>
}