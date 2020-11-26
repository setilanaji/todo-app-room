package com.ydh.todoapprooom.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ydh.todoapprooom.R
import com.ydh.todoapprooom.data.TodoRepository
import com.ydh.todoapprooom.data.remote.TodoClient
import com.ydh.todoapprooom.data.remote.TodoRemoteRepository
import com.ydh.todoapprooom.data.remote.TodoService
import com.ydh.todoapprooom.databinding.FragmentTodoListBinding
import com.ydh.todoapprooom.model.TodoModel
import com.ydh.todoapprooom.presenter.TodoPresenter

class TodoListFragment : Fragment(), TodoContract.View, TodoAdapter.TodoListener {

    private val binding by lazy { FragmentTodoListBinding.inflate(layoutInflater) }
    private val adapter by lazy { TodoAdapter(requireContext(), this) }
    private val service: TodoService by lazy { TodoClient.todoApiService }
    private val repository: TodoRepository by lazy { TodoRemoteRepository(service) }
    private val presenter: TodoContract.Presenter by lazy { TodoPresenter(this, repository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.rvTodo.adapter = adapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        presenter.getAllTodo()
    }


    override fun onSuccessGetAllTodo(todo: List<TodoModel>) {
        adapter.setData(todo.toMutableList())

    }

    override fun onSuccessInsertTodo(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteTodo(id: Long) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateTodo(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override fun onClick(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override fun onDelete(id: Long) {
        TODO("Not yet implemented")
    }

    override fun onChange(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }
}