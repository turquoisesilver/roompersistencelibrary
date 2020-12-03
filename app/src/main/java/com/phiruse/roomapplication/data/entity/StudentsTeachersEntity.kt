package com.phiruse.roomapplication.data.entity

import androidx.room.Entity

@Entity(primaryKeys = ["studentId", "id"])
data class StudentsTeachersEntity(
    val studentId: Int,
    val id: Int
)