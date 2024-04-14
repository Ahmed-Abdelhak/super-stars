package org.example.error_handler

import org.example.common.SuperModelInterface

interface ErrorHandlerInterface<TError: ErrorInterface> {
    fun handle(error: TError)
}