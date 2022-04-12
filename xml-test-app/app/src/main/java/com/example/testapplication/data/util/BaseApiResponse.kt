package com.example.testapplication.data.util

import android.content.Context
import com.example.testapplication.data.dto.CharacterDetailDto
import com.example.testapplication.domain.model.Character
import com.example.testapplication.util.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseApiResponse {

    suspend fun <T, U> safeApiCall(
        apiCall: suspend () -> Response<T>,
        toSomething: T.() -> U,
    ): Resource<U> {
        return try {
            val response = apiCall()

            if (response.isSuccessful) {
                val body = (response.body() as T).toSomething()
                body?.let {
                    return Resource.Success(data = it)
                }
            }
            return Resource.Error(errorMessage = response.errorBody().toString())
        } catch (e: HttpException) {
            // Returning HttpException's message
            // wrapped in Resource.Error
            Resource.Error(errorMessage = e.message ?: "Something went wrong")
        } catch (e: IOException) {
            // Returning no internet message
            // wrapped in Resource.Error
            Resource.Error("Please check your network connection")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Something went wrong")
        }
    }
}