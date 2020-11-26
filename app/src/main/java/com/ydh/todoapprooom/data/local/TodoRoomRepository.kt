package com.ydh.todoapprooom.data.local

import com.ydh.todoapprooom.data.TodoRepository
import com.ydh.todoapprooom.data.remote.TodoResponse
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

}