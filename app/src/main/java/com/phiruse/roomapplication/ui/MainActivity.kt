package com.phiruse.roomapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.phiruse.roomapplication.R
import com.phiruse.roomapplication.data.LocalDatabase
import com.phiruse.roomapplication.data.entity.StudentEntity
import com.phiruse.roomapplication.data.entity.TeacherEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private var coroutineContext = SupervisorJob() + Dispatchers.Main
    private var coroutineScope = CoroutineScope(coroutineContext)
    private lateinit var db: LocalDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = LocalDatabase.getInstance(applicationContext)
    }
}