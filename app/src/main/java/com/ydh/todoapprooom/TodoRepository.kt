package com.ydh.todoapprooom

interface TodoRepository {
    fun getAllTodo(): List<TodoModel>
    fun insertTodo(todoModel: TodoModel): TodoModel
    fun deleteTodo(todoModel: TodoModel): Long
    fun updateTodo(todoModel: TodoModel): TodoModel
}