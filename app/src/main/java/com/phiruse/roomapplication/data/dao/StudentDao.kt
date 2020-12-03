package com.phiruse.roomapplication.data.dao

import androidx.room.*
import com.phiruse.roomapplication.data.TeacherWithStudents
import com.phiruse.roomapplication.data.dao.BaseDao
import com.phiruse.roomapplication.data.entity.StudentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao:
    BaseDao<StudentEntity> {

    @Query("SELECT * FROM students")
    fun getStudents(): Flow<List<StudentEntity>>

    @Query("SELECT * FROM students where name = :name")
    fun getStudentByName(name: String): Flow<StudentEntity>

    @Query("DELETE FROM students")
    fun deleteAllStudents()

}
