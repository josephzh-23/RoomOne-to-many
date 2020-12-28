package com.plcoding.multipleroomtables.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StudentDetails(
        @PrimaryKey(autoGenerate = false)
        val homeAddress : String,
        val phoneNumber : String,
        //StuId will be the joining column between the 2 joins
        val stuId: Int
)