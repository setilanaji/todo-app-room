package com.ydh.todoapprooom.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ydh.todoapprooom.R
import com.ydh.todoapprooom.databinding.FragmentFavoritesBinding
import com.ydh.todoapprooom.databinding.FragmentTodoListBinding
import com.ydh.todoapprooom.model.TodoModel

class FavoritesFragment : Fragment(), TodoContract.View, TodoAdapter.TodoListener  {

    private val binding by lazy { FragmentFavoritesBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return binding.root

    }

    override fun onSuccessGetAllTodo(todo: List<TodoModel>) {
        TODO("Not yet implemented")
    }

    override fun onSuccessInsertTodo(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteTodo(id: Long) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDeleteFavTodo(id: Long) {
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

    override fun onFavClick(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }

    override fun onDelFavClick(todoModel: TodoModel) {
        TODO("Not yet implemented")
    }
}