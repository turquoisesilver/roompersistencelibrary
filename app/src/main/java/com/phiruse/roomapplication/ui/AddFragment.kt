package com.phiruse.roomapplication.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.phiruse.roomapplication.R
import com.phiruse.roomapplication.data.entity.StudentEntity
import com.phiruse.roomapplication.data.entity.TeacherEntity
import com.phiruse.roomapplication.viewmodel.SchoolViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
class AddFragment : Fragment() {

    private lateinit var studentViewModel: SchoolViewModel
    lateinit var teacherArray: MutableList<String>
    lateinit var teachers: MutableList<TeacherEntity>
    var selectedTeacher: Long = 0
    private var coroutineContext = SupervisorJob() + Dispatchers.Main
    private var coroutineScope = CoroutineScope(coroutineContext)
    var spinnerArrayAdapter: ArrayAdapter<String>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        studentViewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)
        teacherArray = mutableListOf()
        teachers = mutableListOf()
        view.addStudents.setOnClickListener {
            if (selectedTeacher == 0L)
                Toast.makeText(requireContext(), "Select a teacher name", Toast.LENGTH_LONG).show()
            else
                insertStudents()
        }
        spinnerArrayAdapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_dropdown_item, teacherArray)
        spinnerArrayAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view?.spinnerTeachers?.adapter = spinnerArrayAdapter
        view.spinnerTeachers.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedTeacher = teachers[position].id
            }

        }
        coroutineScope.launch {
            studentViewModel.teacherWithStudents.collect {
                for (teacherWithStudents in it)
                {
                    teacherArray.add(teacherWithStudents.teacher.name)
                    teachers.add(teacherWithStudents.teacher)
                }
                spinnerArrayAdapter?.notifyDataSetChanged()
            }

        }

        return view
    }

    private fun insertStudents() {
        val firstName = txtFirstName.text.toString()

        if(inputCheck(firstName)){
            val student = StudentEntity(0,
                 selectedTeacher,
                firstName
            )

            studentViewModel.addStudent(student)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String): Boolean{
        return !(TextUtils.isEmpty(firstName))
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = "Add Student";
    }
}