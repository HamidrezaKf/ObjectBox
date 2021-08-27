package com.hamidreza.objectbox.ui.relations

import androidx.lifecycle.ViewModel
import com.hamidreza.objectbox.db.StoreManager
import com.hamidreza.objectbox.db.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RelationItemsViewModel @Inject constructor(private val storeManager: StoreManager) :
    ViewModel() {

        fun addUser(user: User) = storeManager.addUser(user)

}