package com.hamidreza.objectbox.db.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Todo(
    @Id var id: Long = 0,
    var title: String ?= ""
){
    lateinit var user : ToOne<User>
}
