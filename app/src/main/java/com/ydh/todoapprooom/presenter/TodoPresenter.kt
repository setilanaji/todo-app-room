package com.ydh.todoapprooom.presenter

import com.ydh.todoapprooom.view.TodoContract
import com.ydh.todoapprooom.model.TodoModel
import com.ydh.todoapprooom.data.TodoRepository
import com.ydh.todoapprooom.data.remote.DeleteTodoResponse
import com.ydh.todoapprooom.data.remote.InsertResponse
import com.ydh.todoapprooom.data.remote.TodoResponse
import com.ydh.todoapprooom.model.TodoBodyInsert
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class TodoPresenter(private val view: TodoContract.View, private val repository: TodoRepository) :
    TodoContract.Presenter {

    private val executor by lazy { Executors.newFixedThreadPool(4) }

    override fun getAllTodo(list: List<TodoModel>) {
        println("getallTodo $list")
            repository.getAllTodoOnline().enqueue(object : Callback<TodoResponse> {
                override fun onResponse(
                    call: Call<TodoResponse>,
                    response: Response<TodoResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            response.body()?.let {
                                val todoEdited = mutableListOf<TodoModel>()

                                for (item in it.data){
                                    for (x in list){
                                        if (item.id == x.id ){
                                            item.favStatus = true
                                        }
                                    }
                                    todoEdited.add(item)
                                }
                                view.onSuccessGetAllTodo(
                                    todoEdited.toList()
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

    override fun getAllFavTodo(): List<TodoModel> {
        val todo = mutableListOf<TodoModel>()
        executor.execute {
             todo.addAll(repository.getAllTodo())
            view.onSuccessGetAllFavTodo(todo)
        }
        print("getFav $todo")
        return todo
    }

    override fun insertFavTodo(todoModel: TodoModel) {
        executor.execute {
            val todo = repository.insertTodo(todoModel)
            view.onSuccessInsertFavTodo(todo)
        }
    }

    override fun deleteFavTodo(todoModel: TodoModel) {
        executor.execute {
            val todoId = repository.deleteTodo(todoModel)
            view.onSuccessDeleteFavTodo(todoId)
        }
    }

    override fun insertTodo(task: String) {

        repository.createTodoOnline(task).enqueue(object : Callback<InsertResponse> {
            override fun onResponse(
                call: Call<InsertResponse>,
                response: Response<InsertResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        response.body()?.let {
//                            val todoEdited = mutableListOf<TodoModel>()
//
//                            for (item in it.data){
//                                for (x in list){
//                                    if (item.id == x.id ){
//                                        item.favStatus = true
//                                    }
//                                }
//                                todoEdited.add(item)
//                            }
                            view.onSuccessInsertTodo(
                                it.data
                            )
                        }
                    } else {
                        Throwable(message = "Responsenya adalah null")
                    }
                } else {
                    Throwable(message = response.message())
                }

            }

            override fun onFailure(call: Call<InsertResponse>, t: Throwable) {

            }
        })
    }

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

    }

    override fun updateTodo(todoModel: TodoModel) {


        executor.execute {
            val todo = repository.updateTodo(todoModel)
            view.onSuccessUpdateTodo(todo)
        }    }


}
