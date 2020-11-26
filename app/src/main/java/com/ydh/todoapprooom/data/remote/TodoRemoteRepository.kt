package com.ydh.todoapprooom.data.remote

import android.content.Context
import android.util.Log
import com.ydh.todoapprooom.data.TodoRepository
import com.ydh.todoapprooom.model.TodoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoRemoteRepository(private val service: TodoService): TodoRepository {
    override fun getAllTodo(): List<TodoModel> {
        TODO("Not yet implemented")


//        var todo = mutableListOf<TodoModel>()
//
//            TodoClient.todoApiService.getAllTodo().enqueue(object : Callback<TodoResponse> {
//                override fun onResponse(
//                    call: Call<TodoResponse>,
//                    response: Response<TodoResponse>
//                ) {
//                    if (response.isSuccessful){
//                        todo = response.body()?.data as MutableList<TodoModel>
//                    }
//
//                }
//
//                override fun onFailure(call: Call<TodoResponse>, t: Throwable) {
//                    Log.e("getAllTodo", t.toString()
//                    )
//                }
//
//            })
//
//        return todo
    }

    override fun insertTodo(todoModel: TodoModel): TodoModel {
        TODO("Not yet implemented")
    }

    override fun deleteTodo(todoModel: TodoModel): Long {
        TODO("Not yet implemented")
    }

    override fun updateTodo(todoModel: TodoModel): TodoModel {
        TODO("Not yet implemented")
    }

    override fun getAllTodoOnline(): Call<TodoResponse> {
        return  service.getAllTodo()
    }

}