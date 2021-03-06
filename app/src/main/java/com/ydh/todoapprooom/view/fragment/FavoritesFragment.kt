package com.ydh.todoapprooom.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ydh.todoapprooom.data.TodoRepository
import com.ydh.todoapprooom.data.local.LocalDB
import com.ydh.todoapprooom.data.local.TodoDAO
import com.ydh.todoapprooom.data.local.TodoRoomRepository
import com.ydh.todoapprooom.data.remote.TodoClient
import com.ydh.todoapprooom.data.remote.TodoRemoteRepository
import com.ydh.todoapprooom.data.remote.TodoService
import com.ydh.todoapprooom.databinding.FragmentFavoritesBinding
import com.ydh.todoapprooom.model.TodoModel
import com.ydh.todoapprooom.presenter.TodoPresenter
import com.ydh.todoapprooom.presenter.TodoContract
import com.ydh.todoapprooom.util.SwipeToDelete
import com.ydh.todoapprooom.view.adapter.TodoAdapter
import com.ydh.todoapprooom.view.adapter.TodoFavAdapter

class FavoritesFragment : Fragment(), TodoContract.View, TodoFavAdapter.TodoListener {

    private val binding by lazy { FragmentFavoritesBinding.inflate(layoutInflater) }
    private val dao: TodoDAO by lazy { LocalDB.getDatabase(requireContext()).dao() }
    private val adapter by lazy { TodoFavAdapter(requireContext(), this) }
    private val service: TodoService by lazy { TodoClient.todoApiService }
    private val repository: TodoRepository by lazy { TodoRemoteRepository(service) }
    private val offlineRepository: TodoRepository by lazy { TodoRoomRepository(dao) }
    private val presenter: TodoContract.Presenter by lazy { TodoPresenter(this, repository) }
    private val offlinePresenter: TodoContract.Presenter by lazy { TodoPresenter(
        this,
        offlineRepository
    ) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.run {
            rvFavorite.adapter = adapter
            val swipeHandler = object : SwipeToDelete(requireContext()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = rvFavorite.adapter as TodoAdapter
                    val pos = viewHolder.adapterPosition
                    val item = adapter.getData(pos)
                    presenter.deleteTodo(item)

                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(rvFavorite)
        }



        return binding.root

    }

    override fun onResume() {
        super.onResume()
        offlinePresenter.getAllFavTodo()
    }

    override fun onSuccessGetAllTodo(todo: List<TodoModel>) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetAllFavTodo(todo: List<TodoModel>) {
        requireActivity().runOnUiThread {
            adapter.setData(todo.toMutableList())
        }
    }

    override fun onSuccessInsertTodo(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override fun onSuccessInsertFavTodo(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteTodo(id: Long) {
        adapter.deleteTodo(id)
    }

    override fun onSuccessDeleteFavTodo(id: Long) {
        requireActivity().runOnUiThread {
            Toast.makeText(context, "task has been deleted from favorite", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSuccessUpdateTodo(todoModel: TodoModel) {
        requireActivity().runOnUiThread {
            todoModel.favStatus = true
            adapter.updateTodo(todoModel)
        }
    }

    override fun onClick(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override fun onDelete(id: Long) {
        TODO("Not yet implemented")
    }
    override fun onChange(todoModel: TodoModel) {
        presenter.updateTodo(todoModel)
        offlinePresenter.updateFavTodo(todoModel)
    }

    override fun onFavClick(todoModel: TodoModel) {
        todoModel.favStatus = true
        offlinePresenter.insertFavTodo(todoModel)
    }

    override fun onDelFavClick(todoModel: TodoModel) {
        offlinePresenter.deleteFavTodo(todoModel)
        onSuccessDeleteTodo(todoModel.id)
    }
}