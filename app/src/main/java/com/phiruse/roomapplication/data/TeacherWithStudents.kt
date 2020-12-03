package com.phiruse.roomapplication.data

import androidx.room.Embedded
import androidx.room.Relation
import com.phiruse.roomapplication.data.entity.StudentEntity
import com.phiruse.roomapplication.data.entity.TeacherEntity

// ONE TO MANY RELATION
data class TeacherWithStudents(
    @Embedded val teacher: TeacherEntity,
    @Relation(
        parentColumn = "id",
        entity = StudentEntity::class,
        entityColumn = "teacherId"
    )
    val students: List<StudentEntity>
)
