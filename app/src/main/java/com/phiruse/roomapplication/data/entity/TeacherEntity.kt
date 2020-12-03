package com.phiruse.roomapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "teachers",
    indices = [Index("id")]
)
data class TeacherEntity(
    @PrimaryKey(autoGenerate = true) //Using default column name
    var id: Long,
    @ColumnInfo(name = "teacher_name") // Column name: teacher_name
    val name: String,
    val age: Int = 1
)