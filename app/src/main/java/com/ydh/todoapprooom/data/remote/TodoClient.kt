package com.ydh.todoapprooom.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TodoClient {
    private const val BASE_URL = "https://online-course-todo.herokuapp.com/"

    private val gson: Gson by lazy{
        GsonBuilder().setLenient().create()
    }

    private val interceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
    }

    val todoApiService: TodoService by lazy{
        retrofit.create(TodoService::class.java)
    }
}