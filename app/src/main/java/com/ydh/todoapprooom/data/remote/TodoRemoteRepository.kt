package com.ydh.todoapprooom.data.remote

import com.ydh.todoapprooom.data.TodoRepository
import com.ydh.todoapprooom.model.TodoBodyInsert
import com.ydh.todoapprooom.model.TodoBodyUpdate
import com.ydh.todoapprooom.model.TodoModel
import retrofit2.Call

class TodoRemoteRepository(private val service: TodoService): TodoRepository {
    override fun getAllTodo(): List<TodoModel> {
        TODO("Not yet implemented")
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

    override fun createTodoOnline(task: String): Call<InsertResponse> {
        return service.insertTodo(TodoBodyInsert(task))
    }

    override fun updateTodoById(todo: TodoModel): Call<UpdateResponse> {
        return service.updateTodo(todo.id, TodoBodyUpdate(todo.task, todo.status))
    }

    override fun deleteTodoById(id: Long): Call<DeleteTodoResponse> {
        return service.deleteTodo(id)
    }

}