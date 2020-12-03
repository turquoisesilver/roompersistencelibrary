package com.phiruse.roomapplication.data

import androidx.room.Embedded
import androidx.room.Relation
import com.phiruse.roomapplication.data.entity.StudentEntity
import com.phiruse.roomapplication.data.entity.TeacherEntity

// ONE TO ONE RELATION
data class TeacherAndStudent(
    @Embedded val teacher: TeacherEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "studentId"
    )
    val student: StudentEntity
)
