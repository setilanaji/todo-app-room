package com.ydh.todoapprooom.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ydh.todoapprooom.R
import com.ydh.todoapprooom.databinding.ItemTodoBinding
import com.ydh.todoapprooom.model.TodoModel

class TodoFavAdapter(
        private val context: Context, private val listener: TodoListener
) : RecyclerView.Adapter<TodoFavAdapter.MyViewHolder>() {
    private var favTodoList = mutableListOf<TodoModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemTodoBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_todo,parent,false)
        return MyViewHolder(binding, listener)
    }

    fun setData(item: MutableList<TodoModel>){
        this.favTodoList = item
        notifyDataSetChanged()
    }

    fun deleteTodo(id: Long) {
        val index = favTodoList.indexOfFirst { it.id == id }
        if (index != -1) {
            favTodoList.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun getData(position: Int): TodoModel{
        return favTodoList[position]
    }

    fun addTodo(todoModel: TodoModel) {
        favTodoList.add(0, todoModel)
        notifyItemInserted(0)
    }

    interface TodoListener {
        fun onClick(todoModel: TodoModel)
        fun onDelete(id: Long)
        fun onChange(todoModel: TodoModel)
        fun onFavClick(todoModel: TodoModel)
        fun onDelFavClick(todoModel: TodoModel)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemBinding.todo = favTodoList[position]
    }

    override fun getItemCount(): Int {
        return favTodoList.size
    }

    class MyViewHolder(val itemBinding: ItemTodoBinding,
                       private val listener: TodoListener
    ) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : ItemTodoBinding? = null

        init {
            this.binding = itemBinding
        }

    }

}