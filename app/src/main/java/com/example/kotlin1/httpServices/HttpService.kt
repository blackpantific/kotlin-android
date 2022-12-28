package com.example.kotlin1.httpServices

import com.example.kotlin1.models.Task
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.lang.reflect.Type


class HttpService() {

    private var httpClient: OkHttpClient = OkHttpClient()
    private val gsonSerializer = Gson()
    private var request: Request? = null
    private var response: Response? = null
    private var requestBody: RequestBody? = null
    private var mediaType: MediaType = "application/json; charset=utf-8".toMediaType()

    fun <T> makeRequest(
        method: HttpMethodEnum,
        url: String,
        task: Task? = null,
        type: Type? = null
    ): HttpResponseData<T> {

        val httpResponse: HttpResponseData<T>

        return try {
            when (method) {
                HttpMethodEnum.Get -> request = Request.Builder()
                    .get()
                    .url(url)
                    .addHeader("Content-Type", "application/json")
                    .build()
                HttpMethodEnum.Post -> {
                    val serializedTask = gsonSerializer.toJson(task)
                    requestBody = serializedTask.toRequestBody(mediaType)
                    request = Request.Builder()
                        .post(requestBody!!)
                        .url(url)
                        .addHeader("Content-Type", "application/json")
                        .build()
                }
                HttpMethodEnum.Put -> {
                    val serializedTask = gsonSerializer.toJson(task)
                    requestBody = serializedTask.toRequestBody(mediaType)
                    request = Request.Builder()
                        .put(requestBody!!)
                        .url(url)
                        .addHeader("Content-Type", "application/json")
                        .build()
                }
                HttpMethodEnum.Delete -> request = Request.Builder()
                    .delete()
                    .url(url)
                    .build()
                else -> {}
            }
            val call: Call = httpClient.newCall(request!!)
            response = call.execute()

            if (response!!.isSuccessful) {
                val deserializedData: T? = if (type != null) {
                    gsonSerializer.fromJson(response!!.body!!.string(), type)
                } else {
                    null
                }

                httpResponse = HttpResponseData()
                httpResponse.isSuccessful = true
                httpResponse.statusCode = response!!.code
                httpResponse.data = deserializedData
            } else {
                httpResponse = HttpResponseData()
                httpResponse.isSuccessful = false
                httpResponse.statusCode = response!!.code
            }

            httpResponse

        } catch (ex: Exception) {

            val response = HttpResponseData<T>()
            response.isSuccessful = false
            response.responseCode = ResponseCode.ErrorHandlingResponse
            response.errorMessage = ex.message

            response
        }
    }
}