package com.redcare.pharmacy.integration.client.http.models

import com.redcare.pharmacy.error_handler.ErrorInterface

class HttpClientError(override val errorCode: Int, override val errorMessage: String) : ErrorInterface