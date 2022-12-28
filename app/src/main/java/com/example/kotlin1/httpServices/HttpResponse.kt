package com.example.kotlin1.httpServices

open class HttpResponse {
    var isSuccessful = false
    var errorMessage: String? = null
    var statusCode = 0
    var responseCode: ResponseCode? = null
}