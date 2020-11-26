package com.ydh.todoapprooom.view

import com.ydh.todoapprooom.model.TodoModel

interface TodoContract {
    interface View {
        fun onSuccessGetAllTodo(todo: List<TodoModel>)
        fun onSuccessGetAllFavTodo(todo: List<TodoModel>)
        fun onSuccessInsertTodo(todoModel: TodoModel)
        fun onSuccessInsertFavTodo(todoModel: TodoModel)
        fun onSuccessDeleteTodo(id: Long)
        fun onSuccessDeleteFavTodo(id: Long)
        fun onSuccessUpdateTodo(todoModel: TodoModel)
    }

    interface Presenter {
        fun getAllTodo(list: List<TodoModel>)
        fun getAllFavTodo(): List<TodoModel>
        fun insertFavTodo(todoModel: TodoModel)
        fun deleteFavTodo(todoModel: TodoModel)
        fun updateFavTodo(todoModel: TodoModel)
        fun insertTodo(task: String)
        fun deleteTodo(todoModel: TodoModel)
        fun updateTodo(todoModel: TodoModel)
    }
}