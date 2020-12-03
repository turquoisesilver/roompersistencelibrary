package com.phiruse.roomapplication.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.phiruse.roomapplication.R
import com.phiruse.roomapplication.data.entity.StudentEntity
import com.phiruse.roomapplication.viewmodel.SchoolViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var viewModel: SchoolViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        viewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)
        view.txtStudentName.setText(args.currentStudent.name)

        view.btnUpdate.setOnClickListener {
            updateItem()
        }

        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {

        val firstName = txtStudentName.text.toString()

        if (inputCheck(firstName)) {
            val updatedStudent = StudentEntity(  args.currentStudent.studentId,args.currentStudent.teacherId, firstName)
            viewModel.updateStudent(updatedStudent)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String): Boolean {
        return !(TextUtils.isEmpty(firstName))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteStudent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteStudent() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteStudent(args.currentStudent)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentStudent.name}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentStudent.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentStudent.name}?")
        builder.create().show()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = "Update Student";
    }
}