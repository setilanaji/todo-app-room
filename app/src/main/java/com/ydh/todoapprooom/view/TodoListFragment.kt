package com.ydh.todoapprooom.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ydh.todoapprooom.R
import com.ydh.todoapprooom.data.TodoRepository
import com.ydh.todoapprooom.data.remote.TodoClient
import com.ydh.todoapprooom.data.remote.TodoRemoteRepository
import com.ydh.todoapprooom.data.remote.TodoService
import com.ydh.todoapprooom.databinding.FragmentTodoListBinding
import com.ydh.todoapprooom.model.TodoModel
import com.ydh.todoapprooom.presenter.TodoPresenter
import com.ydh.todoapprooom.util.SwipeToDelete

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
        binding.run {
            rvTodo.adapter = adapter
            val swipeHandler = object : SwipeToDelete(requireContext()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = rvTodo.adapter as TodoAdapter
                    val pos = viewHolder.adapterPosition
                    val item = adapter.getData(pos)
                    presenter.deleteTodo(item)

//                    val snack =
//                        view?.let {
//                            Snackbar.make(
//                                it,
//                                "Item was removed from the list.",
//                                Snackbar.LENGTH_LONG
//                            )
//                        }
//                    snack?.setAction("UNDO") {
//                        adapter.restoreItem(item, pos)
//                        rvUsers.scrollToPosition(pos)
//                    }
//
//                    snack?.setActionTextColor(Color.BLUE)
//                    snack?.show()
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(rvTodo)
        }

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
        adapter.deleteTodo(id)
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