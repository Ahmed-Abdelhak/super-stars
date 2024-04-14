package com.redcare.pharmacy.error_handler

import com.redcare.pharmacy.integration.client.http.HttpClientError
import org.springframework.stereotype.Component

@Component
class ErrorHandler: ErrorHandlerInterface<HttpClientError>{
    override fun handle(error: HttpClientError) {}
}