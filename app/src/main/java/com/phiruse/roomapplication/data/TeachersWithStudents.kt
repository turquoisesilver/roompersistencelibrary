package com.phiruse.roomapplication.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.phiruse.roomapplication.data.entity.StudentEntity
import com.phiruse.roomapplication.data.entity.StudentsTeachersEntity
import com.phiruse.roomapplication.data.entity.TeacherEntity

// MANY TO MANY RELATION
data class TeachersWithStudents(
    @Embedded val teacher: TeacherEntity,
    @Relation(
        parentColumn = "id",
        entity = StudentEntity::class,
        entityColumn = "studentId",
        associateBy = Junction(StudentsTeachersEntity::class)
    )
    val students: List<StudentEntity>
)
