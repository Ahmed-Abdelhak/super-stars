package com.redcare.pharmacy.error_handler

import com.redcare.pharmacy.common.SuperModelInterface

interface ResponseInterface<TModel: SuperModelInterface> {
    val modelList: List<TModel>
    val isSuccess: Boolean
    val error: ErrorInterface?
}