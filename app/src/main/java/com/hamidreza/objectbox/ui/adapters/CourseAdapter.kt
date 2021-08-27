package com.hamidreza.objectbox.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamidreza.objectbox.databinding.ItemCourseBinding
import com.hamidreza.objectbox.db.models.Course


class CourseAdapter(val onCourseDelete:(Course)->Unit) : ListAdapter<Course, CourseAdapter.CourseViewHolder>(
    UserDiffUtil()
) {
    inner class CourseViewHolder(val view: ItemCourseBinding) : RecyclerView.ViewHolder(view.root){

        init {
            view.ivDelete.setOnClickListener {
                val positon = adapterPosition
                if (positon != RecyclerView.NO_POSITION){
                    val course = getItem(positon)
                    onCourseDelete(course)
                }
            }
        }

        fun bind(course: Course){
            view.courseId.text = course.id.toString()
            view.courseName.text = course.courseName

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = ItemCourseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentUser = getItem(position)
        holder.bind(currentUser)
    }

    class UserDiffUtil : DiffUtil.ItemCallback<Course>(){
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }
}