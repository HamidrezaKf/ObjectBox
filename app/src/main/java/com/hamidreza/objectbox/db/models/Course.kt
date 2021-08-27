package com.hamidreza.objectbox.db.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
data class Course(
    @Id var id:Long=0,
    var courseName:String?=""
){
    lateinit var users : ToMany<User>
}
