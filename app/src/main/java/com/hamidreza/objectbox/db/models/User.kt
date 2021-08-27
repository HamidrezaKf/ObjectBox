package com.hamidreza.objectbox.db.models

import android.os.Parcelable
import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class User(
    @Id var id:Long = 0,
    var name:String?=""
):Parcelable{
    lateinit var address : ToOne<Address>
    @Backlink(to = "user")
    lateinit var todos : ToMany<Todo>
    @Backlink(to = "users")
    lateinit var courses : ToMany<Course>
}