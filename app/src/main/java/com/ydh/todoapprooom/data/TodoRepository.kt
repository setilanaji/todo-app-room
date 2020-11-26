package com.ydh.todoapprooom.data

import com.ydh.todoapprooom.data.remote.TodoResponse
import com.ydh.todoapprooom.model.TodoModel
import retrofit2.Call

interface TodoRepository {
    fun getAllTodo(): List<TodoModel>
    fun insertTodo(todoModel: TodoModel): TodoModel
    fun deleteTodo(todoModel: TodoModel): Long
    fun updateTodo(todoModel: TodoModel): TodoModel
    fun getAllTodoOnline(): Call<TodoResponse>
}