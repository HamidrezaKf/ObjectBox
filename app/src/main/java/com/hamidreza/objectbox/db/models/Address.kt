package com.hamidreza.objectbox.db.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Address(
    @Id var id:Long =0,
    var street:String? =""
)
