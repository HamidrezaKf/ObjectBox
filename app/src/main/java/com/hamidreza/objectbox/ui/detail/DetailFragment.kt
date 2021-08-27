package com.hamidreza.objectbox.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hamidreza.objectbox.R
import com.hamidreza.objectbox.databinding.FragmentDetailBinding
import com.hamidreza.objectbox.db.models.Address
import com.hamidreza.objectbox.db.models.Course
import com.hamidreza.objectbox.db.models.Todo
import com.hamidreza.objectbox.db.models.User

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailFragmentViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private var user: User? = null
    private val courseList = mutableListOf<Course>()
    private val todoList = mutableListOf<Todo>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        // Todo : get use (if != null fill name & address)
        user = args.user
        user?.let {
            binding.userName.editText?.setText(it.name)
            binding.userAddress.editText?.setText(it.address.target.street)
        }
        binding.btnCourseSave.setOnClickListener {
            //Todo : create course and insert to list
            val course = Course(courseName = binding.userCourse.editText?.text.toString())
            courseList.add(course)
            binding.userCourse.editText?.setText("")
        }
        binding.btnTodoSave.setOnClickListener {
            //Todo : create todo and insert to list
            val todo = Todo(title = binding.userTodo.editText?.text.toString())
            todoList.add(todo)
            binding.userTodo.editText?.setText("")
        }
        binding.btnDelete.setOnClickListener {
            //Todo : if user !=null delete user
            user?.let {
                viewModel.deleteUser(it)
                findNavController().popBackStack()
            }
        }
        binding.btnShowTodos.setOnClickListener {

            //Todo : if user !=null navigate to next fragment with todo id
            if (user == null)
                return@setOnClickListener
            val action = DetailFragmentDirections.actionDetailFragmentToRelationItemsFragment(1,user!!)
            findNavController().navigate(action)

        }
        binding.btnShowCourses.setOnClickListener {

            //Todo : if user !=null navigate to next fragment with course id
            if (user == null)
                return@setOnClickListener
            val action = DetailFragmentDirections.actionDetailFragmentToRelationItemsFragment(2,user!!)
            findNavController().navigate(action)
        }
        binding.btnSaveUser.setOnClickListener {
            saveUser()
        }
    }


    private fun saveUser() {
        getUserInfo()
        findNavController().popBackStack()
    }

    private fun getUserInfo() {

        //Todo : get name,address,todos,courses (if user == null create new user else update current user
        val name = binding.userName.editText?.text.toString()
        val address = Address(street = binding.userAddress.editText?.text.toString())
        var insertUser: User
        if (user == null) {
            insertUser = User(name = name)
        } else {
            insertUser = user!!
            insertUser.name = name
        }
        insertUser.address.target = address
        insertUser.courses.addAll(courseList)
        insertUser.todos.addAll(todoList)
        viewModel.addUser(insertUser)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}