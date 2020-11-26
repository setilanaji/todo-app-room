package com.ydh.todoapprooom.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ydh.todoapprooom.R
import com.ydh.todoapprooom.databinding.ItemTodoBinding
import com.ydh.todoapprooom.model.TodoModel

class TodoAdapter(
        private val context: Context, private val listener: TodoListener
) : RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {
    private var todoList = mutableListOf<TodoModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemTodoBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_todo,parent,false)
        return MyViewHolder(binding, listener)
    }

    fun setData(item: MutableList<TodoModel>){
        println(item)
        println("set Data")
        this.todoList = item
        notifyDataSetChanged()
    }

    fun deleteTodo(id: Long) {
        val index = todoList.indexOfFirst { it.id == id }
        if (index != -1) {
            todoList.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun updateTodo(todoModel: TodoModel) {
        val index = todoList.indexOfFirst { it.id == todoModel.id }
        if (index != -1) {
            todoList[index] = todoModel
            notifyItemChanged(index)
        }
    }

    fun getData(position: Int): TodoModel{
        return todoList[position]
    }

    fun addTodo(todoModel: TodoModel) {
        todoList.add(0, todoModel)
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
        holder.itemBinding.todo = todoList[position]
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    class MyViewHolder(val itemBinding: ItemTodoBinding,
                       private val listener: TodoListener
    ) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : ItemTodoBinding? = null

        init {
            this.binding = itemBinding
            itemBinding.ivFavTodo.setOnClickListener{
                if (itemBinding.ivFavTodo.isChecked){
                    listener.onFavClick(itemBinding.todo!!)
                }else{
                    listener.onDelFavClick(itemBinding.todo!!)
                }
            }

            itemBinding.ivStatus.setOnClickListener{
                val item = itemBinding.todo
                if (itemBinding.ivStatus.isChecked){
                    item!!.status = true
                    listener.onChange(item)
                }else{
                    item!!.status = false
                    listener.onChange(item)
                }
            }
        }

    }

}