package com.hamidreza.objectbox.db.boxes

import com.hamidreza.objectbox.db.models.User
import io.objectbox.BoxStore
import io.objectbox.android.ObjectBoxLiveData

class UserBox (val boxStore: BoxStore) {
    private val box = boxStore.boxFor(User::class.java)
    fun addUser(user: User) = box.put(user)
    fun deleteUser(user: User) = box.remove(user)
    fun getUsers() = ObjectBoxLiveData(box.query().build())
}