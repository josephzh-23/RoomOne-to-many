package com.plcoding.multipleroomtables.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = false)

    val studentName: String,
    val semester: String,
    val schoolName: String,

    // Default value for student is 0
    val stuId: Int,

)