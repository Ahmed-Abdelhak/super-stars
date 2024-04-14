package com.redcare.pharmacy.error_handler

interface ErrorHandlerInterface<TError: ErrorInterface> {
    fun handle(error: TError)
}