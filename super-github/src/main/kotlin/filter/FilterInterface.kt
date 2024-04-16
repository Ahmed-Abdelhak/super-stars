package com.redcare.pharmacy.filter

import com.redcare.pharmacy.common.model.SuperModelInterface

interface FilterInterface<TModel: SuperModelInterface> {
    fun filter(model: TModel): Boolean
}