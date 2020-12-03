package com.phiruse.roomapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.phiruse.roomapplication.data.LocalDatabase
import com.phiruse.roomapplication.data.TeacherWithStudents
import com.phiruse.roomapplication.data.entity.StudentEntity
import com.phiruse.roomapplication.data.entity.TeacherEntity
import com.phiruse.roomapplication.repository.SchoolRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SchoolViewModel(application: Application): AndroidViewModel(application) {

    val teacherWithStudents: Flow<List<TeacherWithStudents>>
    private val repository: SchoolRepository

    init {
        val studentDao = LocalDatabase.getInstance(
            application
        ).studentDao()
        val teacherDao = LocalDatabase.getInstance(
            application
        ).teacherDao()
        repository = SchoolRepository(teacherDao, studentDao)
        teacherWithStudents = repository.getTeacherWithStudents
    }

    fun addStudent(student: StudentEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(student)
        }
    }

    fun addTeacher(teacher: TeacherEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTeacher(teacher)
        }
    }


    fun addTeacherAndStudent(teacher: TeacherEntity, student: StudentEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTeacherAndStudent(teacher, student)
        }
    }

    fun addTeacherWithStudents(students: List<StudentEntity>){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTeacherWithStudents(students)
        }
    }

    fun updateStudent(student: StudentEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStudent(student)
        }
    }

    fun deleteStudent(student: StudentEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStudent(student)
        }
    }

    fun deleteAllStudents(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllStudents()
        }
    }

}