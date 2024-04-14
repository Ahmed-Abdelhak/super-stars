package org.example.error_handler

import org.example.common.SuperModelInterface

interface ResponseInterface<TModel: SuperModelInterface> {
    val model: TModel
    val isSuccess: Boolean
    val error: ErrorInterface?
}