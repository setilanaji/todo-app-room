package com.ydh.todoapprooom

import com.ydh.todoapprooom.TodoRepository
import com.ydh.todoapprooom.TodoBodyInsert
import com.ydh.todoapprooom.TodoModel
import retrofit2.Call
import retrofit2.http.*

interface TodoService {
    @GET("api/v1/todos")
    fun getAllTodo(): Call<TodoResponse>

    @POST("api/v1/todos")
     fun insertTodo(
        @Body
        body: TodoBodyInsert
    ): Call<TodoResponse>

}