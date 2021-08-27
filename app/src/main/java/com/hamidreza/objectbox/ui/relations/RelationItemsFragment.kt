package com.hamidreza.objectbox.ui.relations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamidreza.objectbox.R
import com.hamidreza.objectbox.databinding.RelationItemsFragmentBinding
import com.hamidreza.objectbox.db.models.User
import com.hamidreza.objectbox.ui.adapters.CourseAdapter
import com.hamidreza.objectbox.ui.adapters.TodoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RelationItemsFragment : Fragment(R.layout.relation_items_fragment) {

    private var _binding: RelationItemsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RelationItemsViewModel by viewModels()
    private val args: RelationItemsFragmentArgs by navArgs()
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var courseAdapter: CourseAdapter
    private lateinit var user: User
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = RelationItemsFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        //Todo : get type if todo setup todoadapter else setup courseadapter
        val type = args.type
        user = args.user

        if (type == 1){
            setUpTodoRecyclerView()
            todoAdapter.submitList(user.todos)
        }else{
            setUpCourseRecyclerView()
            courseAdapter.submitList(user.courses)
        }



    }

    private fun setUpCourseRecyclerView() {
        courseAdapter = CourseAdapter{
            user.courses.remove(it)
            viewModel.addUser(user)
            findNavController().popBackStack()
        }
        binding.rv.apply {
            adapter = courseAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    private fun setUpTodoRecyclerView() {
        todoAdapter = TodoAdapter{
            user.todos.remove(it)
            viewModel.addUser(user)
            findNavController().popBackStack()
        }
        binding.rv.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}