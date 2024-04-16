package com.redcare.pharmacy.error_handler

import com.redcare.pharmacy.common.model.GithubRepositoryModel

interface ErrorHandlerInterface<TError : ErrorInterface> {
    fun handle(modelList: List<GithubRepositoryModel>, error: TError): List<GithubRepositoryModel>
}