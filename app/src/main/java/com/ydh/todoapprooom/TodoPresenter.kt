package com.ydh.todoapprooom

import java.util.concurrent.Executors

class TodoPresenter(private val view: TodoContract.View, private val repository: TodoRepository) :
    TodoContract.Presenter {

    private val executor by lazy { Executors.newFixedThreadPool(4) }

    override fun getAllTodo() {
        executor.execute {
            val todo = repository.getAllTodo()
            view.onSuccessGetAllTodo(todo)
        }
    }


    override fun insertTodo(todoModel: TodoModel) {
        executor.execute {
            val todo = repository.insertTodo(todoModel)
            view.onSuccessInsertTodo(todo)
        }
    }

    override fun deleteTodo(todoModel: TodoModel) {
        executor.execute {
            val todoId = repository.deleteTodo(todoModel)
            view.onSuccessDeleteTodo(todoId)
        }
    }

    override fun updateTodo(todoModel: TodoModel) {
        executor.execute {
            val todo = repository.updateTodo(todoModel)
            view.onSuccessUpdateTodo(todo)
        }
    }

}