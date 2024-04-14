package com.redcare.pharmacy.validation

import com.redcare.pharmacy.common.SuperModelInterface

interface ValidatorInterface<TModel: SuperModelInterface> {
    fun isValid(model: TModel): Boolean
}