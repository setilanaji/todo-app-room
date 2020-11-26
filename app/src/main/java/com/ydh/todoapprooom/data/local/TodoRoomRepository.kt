package com.ydh.todoapprooom.data.local

import com.ydh.todoapprooom.data.TodoRepository
import com.ydh.todoapprooom.data.remote.DeleteTodoResponse
import com.ydh.todoapprooom.data.remote.InsertResponse
import com.ydh.todoapprooom.data.remote.TodoResponse
import com.ydh.todoapprooom.data.remote.UpdateResponse
import com.ydh.todoapprooom.model.TodoModel
import com.ydh.todoapprooom.model.toEntity
import retrofit2.Call

class TodoRoomRepository(private val dao: TodoDAO): TodoRepository {

    override fun getAllTodo(): List<TodoModel> {
        return dao.getAllTodo().asSequence().map { it.toModel() }.toList()
    }

    override fun insertTodo(todoModel: TodoModel): TodoModel {
        val todo = todoModel.toEntity()
        val id = dao.createTodo(todo)
        return todoModel.copy(id = id)
    }

    override fun deleteTodo(todoModel: TodoModel): Long {
        dao.deleteTodo(todoModel.toEntity())
        return todoModel.id
    }

    override fun updateTodo(todoModel: TodoModel): TodoModel {
        dao.updateTodo(todoModel.toEntity())
        return todoModel
    }

    override fun getAllTodoOnline(): Call<TodoResponse> {
        TODO("Not yet implemented")
    }

    override fun createTodoOnline(task: String): Call<InsertResponse> {
        TODO("Not yet implemented")
    }

    override fun updateTodoById(todo: TodoModel): Call<UpdateResponse> {
        TODO("Not yet implemented")
    }

    override fun deleteTodoById(id: Long): Call<DeleteTodoResponse> {
        TODO("Not yet implemented")
    }

}