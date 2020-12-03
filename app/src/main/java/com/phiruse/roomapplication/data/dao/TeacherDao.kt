package com.phiruse.roomapplication.data.dao

import androidx.room.*
import com.phiruse.roomapplication.data.TeacherAndStudent
import com.phiruse.roomapplication.data.TeacherWithStudents
import com.phiruse.roomapplication.data.TeachersWithStudents
import com.phiruse.roomapplication.data.entity.TeacherEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface TeacherDao : BaseDao<TeacherEntity> {

    @Transaction //Because Room runs the two queries for us under the hood, add the @Transaction annotation, to ensure that this happens atomically.
    @Query("SELECT * FROM teachers")
    fun getTeacherAndStudent(): Flow<List<TeacherAndStudent>>

    @Transaction
    @Query("SELECT * FROM teachers")
    fun getTeacherWithStudents(): Flow<List<TeacherWithStudents>>

    fun getDistinctTeacherWithStudents(): Flow<List<TeacherWithStudents>> =
        getTeacherWithStudents().distinctUntilChanged()

    @Transaction
    @Query("SELECT * FROM teachers")
    fun getTeachersWithStudents(): Flow<List<TeachersWithStudents>>

    @Query("SELECT * FROM teachers")
    fun getTeachers(): Flow<List<TeacherEntity>>

    @Query("DELETE FROM teachers")
    fun deleteAllTeachers()

}
