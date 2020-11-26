package com.ydh.todoapprooom.data

import androidx.room.Update
import com.ydh.todoapprooom.data.remote.DeleteTodoResponse
import com.ydh.todoapprooom.data.remote.InsertResponse
import com.ydh.todoapprooom.data.remote.TodoResponse
import com.ydh.todoapprooom.data.remote.UpdateResponse
import com.ydh.todoapprooom.model.TodoModel
import retrofit2.Call

interface TodoRepository {
    fun getAllTodo(): List<TodoModel>
    fun insertTodo(todoModel: TodoModel): TodoModel
    fun deleteTodo(todoModel: TodoModel): Long
    fun updateTodo(todoModel: TodoModel): TodoModel
    fun getAllTodoOnline(): Call<TodoResponse>
    fun createTodoOnline(task: String): Call<InsertResponse>
    fun updateTodoById(todo: TodoModel): Call<UpdateResponse>
    fun deleteTodoById(id: Long): Call<DeleteTodoResponse>
}