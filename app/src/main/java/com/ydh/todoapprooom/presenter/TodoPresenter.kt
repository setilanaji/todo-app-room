package com.ydh.todoapprooom.presenter

import com.ydh.todoapprooom.view.TodoContract
import com.ydh.todoapprooom.model.TodoModel
import com.ydh.todoapprooom.data.TodoRepository
import com.ydh.todoapprooom.data.remote.DeleteTodoResponse
import com.ydh.todoapprooom.data.remote.TodoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class TodoPresenter(private val view: TodoContract.View, private val repository: TodoRepository) :
    TodoContract.Presenter {

    private val executor by lazy { Executors.newFixedThreadPool(4) }

    override fun getAllTodo() {
//        executor.execute {
//            val todo = repository.getAllTodo()
//            view.onSuccessGetAllTodo(todo)
//        }

            repository.getAllTodoOnline().enqueue(object : Callback<TodoResponse> {
                override fun onResponse(
                    call: Call<TodoResponse>,
                    response: Response<TodoResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            response.body()?.let {
                                view.onSuccessGetAllTodo(
                                    it.data.toList()
                                )
                            }
                        } else {
                            Throwable(message = "Responsenya adalah null")
                        }
                    } else {
                        Throwable(message = response.message())
                    }

                }

                override fun onFailure(call: Call<TodoResponse>, t: Throwable) {

                }
            })
        }

    override fun insertTodo(todoModel: TodoModel) {
        executor.execute {
            val todo = repository.insertTodo(todoModel)
            view.onSuccessInsertTodo(todo)
        }    }

    override fun deleteTodo(todoModel: TodoModel) {
        repository.deleteTodoById(todoModel.id).enqueue(object : Callback<DeleteTodoResponse> {
            override fun onResponse(
                call: Call<DeleteTodoResponse>,
                response: Response<DeleteTodoResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        response.body()?.let {
                            view.onSuccessDeleteTodo(
                            todoModel.id)
                        }
                    } else {
                        Throwable(message = "Responsenya adalah null")
                    }
                } else {
                    Throwable(message = response.message())
                }

            }

            override fun onFailure(call: Call<DeleteTodoResponse>, t: Throwable) {

            }
        })
//        executor.execute {
//            val todoId = repository.deleteTodo(todoModel)
//            view.onSuccessDeleteTodo(todoId)
//        }
    }

    override fun updateTodo(todoModel: TodoModel) {


        executor.execute {
            val todo = repository.updateTodo(todoModel)
            view.onSuccessUpdateTodo(todo)
        }    }


}
