package com.hamidreza.objectbox.ui.users

import androidx.lifecycle.ViewModel
import com.hamidreza.objectbox.db.StoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersFragmentViewModel @Inject constructor(private val storeManager: StoreManager) : ViewModel() {

    val getUsers = storeManager.getUsers()

}