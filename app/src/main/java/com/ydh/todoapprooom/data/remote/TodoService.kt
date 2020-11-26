package com.ydh.todoapprooom.data.remote

import com.ydh.todoapprooom.model.TodoBodyInsert
import com.ydh.todoapprooom.model.TodoBodyUpdate
import retrofit2.Call
import retrofit2.http.*

interface TodoService {
    @GET("api/v1/todos")
    fun getAllTodo(): Call<TodoResponse>

    @POST("api/v1/todos")
     fun insertTodo(
        @Body
        body: TodoBodyInsert
    ): Call<InsertResponse>

    @DELETE("api/v1/todos/{id}")
    fun deleteTodo(@Path("id") id: Long): Call<DeleteTodoResponse>

    @PUT("api/v1/todos/{id}")
    fun updateTodo(@Path("id") id: Long, @Body body: TodoBodyUpdate): Call<UpdateResponse>

}