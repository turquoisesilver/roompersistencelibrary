package com.phiruse.roomapplication.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.contains
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.phiruse.roomapplication.R
import com.phiruse.roomapplication.data.TeacherWithStudents
import com.phiruse.roomapplication.data.entity.StudentEntity
import com.phiruse.roomapplication.ui.ListFragmentDirections
import kotlinx.android.synthetic.main.custom_row.view.*


class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var teacherWithStudents = emptyList<TeacherWithStudents>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return teacherWithStudents.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = teacherWithStudents[position]
        var studentNames = ""
        for (s in currentItem.students) {
            studentNames += s.name + "\n"
    }

        var teacher = currentItem.teacher
        holder.itemView.txtTeacherName.text = teacher.name
        var imgPath =
            when (teacher.name) {
                "Teacher1" ->
                    R.mipmap.teacher
                "Teacher2" ->
                    R.mipmap.teacher2
                else ->
                    R.mipmap.teacher3
            }
        holder.itemView.imgPhoto.setImageResource(imgPath)
        //holder.itemView.txtStudentNames.text = studentNames

        if (studentNames.isEmpty()) {
            holder.itemView.lnrStudents.removeAllViews()
        } else
            for (s in currentItem.students) {
                var linearRow = LinearLayout(holder.itemView.context)
                linearRow.orientation = LinearLayout.HORIZONTAL
                var img = ImageView(holder.itemView.context)
                img.setPadding(10, 10, 10, 10)
                var index = currentItem.students.indexOf(s)
                if (index % 2 == 0)
                    img.setImageResource(R.mipmap.student_female)
                else
                    img.setImageResource(R.mipmap.student_male)

                var txt = TextView(holder.itemView.context)
                txt.text = s.name
                txt.tag = s
                txt.setPadding(10, 10, 10, 10)
                txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
                val paramsTxt = LinearLayout.LayoutParams(
                    AbsListView.LayoutParams.WRAP_CONTENT,
                    AbsListView.LayoutParams.MATCH_PARENT
                )
                val paramsImg = LinearLayout.LayoutParams(
                    100,
                    AbsListView.LayoutParams.WRAP_CONTENT
                )
                txt.layoutParams = paramsTxt
                img.layoutParams = paramsImg
                linearRow.addView(img)
                linearRow.addView(txt)
                paramsTxt.weight = 2.0f
                if (!holder.itemView.lnrStudents.contains(linearRow))
                    holder.itemView.lnrStudents.addView(linearRow)

                txt.setOnClickListener {
                    val action =
                        ListFragmentDirections.actionListFragmentToUpdateFragment(txt.tag as StudentEntity)
                    holder.itemView.findNavController().navigate(action)
                }
            }
    }

    fun setData(list: List<TeacherWithStudents>) {
        this.teacherWithStudents = list
        notifyDataSetChanged()
    }
}