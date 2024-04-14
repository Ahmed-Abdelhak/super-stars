package org.example.validation

import org.example.common.SuperModelInterface

interface ValidatorInterface<TModel: SuperModelInterface> {
    fun isValid(model: TModel): Boolean
}