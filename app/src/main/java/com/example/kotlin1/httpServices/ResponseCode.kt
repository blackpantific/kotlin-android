package com.example.kotlin1.httpServices

enum class ResponseCode {
    Ok,
    NoContent,
    Unknown,
    Exception,
    NoDataFound,
    NoConnection,
    JsonFail,
    ServerError,
    BadRequest,
    Forbidden,
    InvalidCredentials,
    OperationFailed,
    NotSuccessfulRequest,
    ErrorHandlingResponse,
    MissingInternetConnection,
    NotAuthenticated,
}