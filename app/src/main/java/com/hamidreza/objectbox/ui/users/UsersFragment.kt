package com.hamidreza.objectbox.ui.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamidreza.objectbox.R
import com.hamidreza.objectbox.databinding.FragmentUsersBinding
import com.hamidreza.objectbox.ui.adapters.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.fragment_users) {
    private var _binding : FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private val viewModel : UsersFragmentViewModel by viewModels()
    private lateinit var userAdapter : UsersAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentUsersBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        //Todo : get users and insert to adapter
        viewModel.getUsers.observe(viewLifecycleOwner){
            userAdapter.submitList(it)
        }
        binding.fb.setOnClickListener {
            val action = UsersFragmentDirections.actionUsersFragmentToDetailFragment(null)
            findNavController().navigate(action)
        }
    }

    private fun setUpRecyclerView(){
        userAdapter = UsersAdapter{
            val action = UsersFragmentDirections.actionUsersFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
        binding.rv.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}