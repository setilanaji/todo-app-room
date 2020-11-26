package com.ydh.todoapprooom.view

import com.ydh.todoapprooom.model.TodoModel

interface TodoContract {
    interface View {
        fun onSuccessGetAllTodo(todo: List<TodoModel>)
        fun onSuccessInsertTodo(todoModel: TodoModel)
        fun onSuccessDeleteTodo(id: Long)
        fun onSuccessUpdateTodo(todoModel: TodoModel)
    }

    interface Presenter {
        fun getAllTodo()
        fun insertTodo(todoModel: TodoModel)
        fun deleteTodo(todoModel: TodoModel)
        fun updateTodo(todoModel: TodoModel)
    }
}