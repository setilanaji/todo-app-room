package com.ydh.todoapprooom

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

}