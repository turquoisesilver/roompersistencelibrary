package com.phiruse.roomapplication.repository

import com.phiruse.roomapplication.data.dao.StudentDao
import com.phiruse.roomapplication.data.dao.TeacherDao
import com.phiruse.roomapplication.data.TeacherWithStudents
import com.phiruse.roomapplication.data.entity.StudentEntity
import com.phiruse.roomapplication.data.entity.TeacherEntity
import kotlinx.coroutines.flow.Flow

class SchoolRepository(private val teacherDao: TeacherDao, private val studentDao: StudentDao) {

    val getTeacherWithStudents: Flow<List<TeacherWithStudents>> = teacherDao.getDistinctTeacherWithStudents()

    suspend fun addStudent(student: StudentEntity){
        studentDao.insert(student)
    }

    suspend fun addTeacher(teacher: TeacherEntity){
        teacherDao.insert(teacher)
    }

    suspend fun addTeacherAndStudent(teacher: TeacherEntity, student: StudentEntity){
        teacherDao.insert(teacher)
        studentDao.insert(student)
    }

    suspend fun addTeacherWithStudents(students: List<StudentEntity>){

        for (s in students)
        {
            s.teacherId = 1
            var sId = studentDao.insert(s)
            s.studentId = sId + 1
            studentDao.insert(s)

        }

    }

    suspend fun updateStudent(student: StudentEntity){
        studentDao.update(student)
    }

    suspend fun deleteStudent(student: StudentEntity){
        studentDao.delete(student)
    }

    suspend fun deleteAllStudents(){
        studentDao.deleteAllStudents()
    }

}