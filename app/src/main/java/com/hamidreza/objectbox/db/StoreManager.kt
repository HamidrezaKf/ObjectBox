package com.hamidreza.objectbox.db

import android.content.Context
import com.hamidreza.objectbox.db.boxes.UserBox
import com.hamidreza.objectbox.db.models.MyObjectBox
import com.hamidreza.objectbox.db.models.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreManager @Inject constructor(@ApplicationContext val context: Context) {

    private val boxStore = MyObjectBox.builder().androidContext(context).build()
    private val userBox = UserBox(boxStore)

    fun addUser(user: User) = userBox.addUser(user)
    fun deleteUser(user: User) = userBox.deleteUser(user)
    fun getUsers() = userBox.getUsers()
}