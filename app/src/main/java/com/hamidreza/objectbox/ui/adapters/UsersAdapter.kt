package com.hamidreza.objectbox.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamidreza.objectbox.databinding.ItemUserBinding
import com.hamidreza.objectbox.db.models.User


class UsersAdapter(val onUser:(User) ->Unit) : ListAdapter<User, UsersAdapter.UserViewHolder>(
    UserDiffUtil()
) {
    inner class UserViewHolder(val view: ItemUserBinding) : RecyclerView.ViewHolder(view.root){

        init {
            view.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val user = getItem(position)
                    onUser(user)
                }
            }
        }

        fun bind(user:User){
            view.userId.text = user.id.toString()
            view.userName.text = user.name
            view.tvCourses.text = user.courses.size.toString()
            view.tvTodos.text = user.todos.size.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.bind(currentUser)
    }

    class UserDiffUtil : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}