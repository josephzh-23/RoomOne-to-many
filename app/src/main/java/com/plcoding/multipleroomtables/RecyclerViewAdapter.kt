package com.plcoding.multipleroomtables

import com.plcoding.multipleroomtables.entities.relations.SchoolWithStudents

//package com.demo.roomdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.plcoding.multipleroomtables.entities.Student
import kotlinx.android.synthetic.main.recyclerview_row.view.*

//import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerViewAdapter(val listener: RowClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


    //This should be a list of students here still
    var items  = ArrayList<Student>()


    // This method will be called in the mainActivity for displaying results
    fun setListData(data: ArrayList<SchoolWithStudents>) {

        //We can loop thru the list and assign
        // All the students for each SchoolWithStudent


        // We want the items to get all the list of students
        // Add it to the items
        data.forEach{
            it.students.forEach{
                items.add(it)
            }
        }
//        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(
            inflater,
            listener
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])

    }



    class MyViewHolder(view: View, val listener: RowClickListener): RecyclerView.ViewHolder(view) {

        val Name = view.Name
        val Semester = view.Semester
        val School = view.School
        val deleteUserID = view.deleteUserID

        fun bind(data: Student) {
            Name.text = data.studentName

            Semester.text = data.semester.toString()


            School.text = data.schoolName

            deleteUserID.setOnClickListener {
                listener.onDeleteUserClickListener(data)
            }
        }
    }

    interface RowClickListener{
        fun onDeleteUserClickListener(user: Student)
        fun onItemClickListener(user: Student)
    }
}