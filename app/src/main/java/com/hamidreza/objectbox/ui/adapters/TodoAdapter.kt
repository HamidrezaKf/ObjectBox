package com.hamidreza.objectbox.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamidreza.objectbox.databinding.ItemTodoBinding
import com.hamidreza.objectbox.db.models.Todo


class TodoAdapter(val onDeleteTodo:(Todo) -> Unit) : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(
    UserDiffUtil()
) {
    inner class TodoViewHolder(val view: ItemTodoBinding) : RecyclerView.ViewHolder(view.root){

        init {
            view.ivDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val todo = getItem(position)
                    onDeleteTodo(todo)
                }
            }
        }

        fun bind(todo: Todo){
            view.todoId.text = todo.id.toString()
            view.todoName.text = todo.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.bind(currentUser)
    }

    class UserDiffUtil : DiffUtil.ItemCallback<Todo>(){
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }
}