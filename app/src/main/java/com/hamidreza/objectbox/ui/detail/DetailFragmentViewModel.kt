package com.hamidreza.objectbox.ui.detail

import androidx.lifecycle.ViewModel
import com.hamidreza.objectbox.db.StoreManager
import com.hamidreza.objectbox.db.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(val storeManager: StoreManager): ViewModel() {

    fun addUser(user: User) = storeManager.addUser(user)
    fun deleteUser(user: User) = storeManager.deleteUser(user)

}