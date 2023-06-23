package com.mehedisoftdev.sms.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mehedisoftdev.sms.R
import com.mehedisoftdev.sms.models.Student

class StudentListAdapter : ListAdapter<Student, StudentListAdapter.StudentListViewHolder>(CustomDiffUtilCallbacks()) {

    private lateinit var listner: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(student: Student)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listner = listener
    }

    inner class StudentListViewHolder(private val view: View, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(view) {

        private val tvName = view.findViewById<TextView>(R.id.row_student_name)
        private val tvPhone = view.findViewById<TextView>(R.id.row_student_contact)
        private val tvTuitionFees = view.findViewById<TextView>(R.id.row_student_tuition_fees)

        fun bind(student: Student) {
            tvName.text = student.studentName
            tvPhone.text = student.contactNumber
            tvTuitionFees.text = student.tuitionFees.toString()
            // click event
            view.setOnClickListener {
                listener.onItemClick(student)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_row_student,
            parent,
            false
        )
        return StudentListViewHolder(view, listner)
    }

    override fun onBindViewHolder(holder: StudentListViewHolder, position: Int) {
        val student = getItem(position)
        holder.bind(student)
    }

    class CustomDiffUtilCallbacks() : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.sid == newItem.sid
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }

    }
}