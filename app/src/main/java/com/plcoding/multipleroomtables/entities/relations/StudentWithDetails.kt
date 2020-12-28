package com.plcoding.multipleroomtables.entities.relations


import androidx.room.Embedded
import androidx.room.Relation
import com.plcoding.multipleroomtables.entities.School
import com.plcoding.multipleroomtables.entities.Student
import com.plcoding.multipleroomtables.entities.StudentDetails

data class StudentWithDetails(
        @Embedded
        val student: Student,

        @Relation(
                parentColumn= "stuId",
                entityColumn = "stuId",
                entity= StudentDetails::class
        )
        val studentDetails: List<StudentDetails>
)
