package com.plcoding.multipleroomtables

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.plcoding.multipleroomtables.entities.*
import com.plcoding.multipleroomtables.entities.relations.StudentSubjectCrossRef
import com.plcoding.multipleroomtables.entities.relations.StudentWithDetails
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener{


    var firstName: EditText? = null
    var secondName:EditText? = null
    var className:EditText? = null
    var updatename:EditText? = null
    var updateid:EditText? = null
    var deleteID:EditText? = null
    var insert: Button? = null
    var read:android.widget.Button? = null
    var btnUpdate:android.widget.Button? = null
    var btnDelete:android.widget.Button? = null
//    var myDatabase: MyDatabase? = null
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        // We need to call this to get all the record
        viewModel.getStudentWithDetails()
        //TODO: will change this back at some point
        viewModel.getAllDetailsObservers().observe(this, Observer {

            val stuData: List<StudentWithDetails> = viewModel.allStudentDetails.value!!
            for (i in 0 until stuData.size-1) {
                Log.i("STUDENT_DATA", (stuData[i].studentDetails[i].homeAddress + ": " +
                        stuData[i].studentDetails[i].phoneNumber))
            }
//            recyclerViewAdapter.setListData(ArrayList(it))
//            recyclerViewAdapter.notifyDataSetChanged()
        })

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }



//        val viewModel = SchoolDatabase.getInstance(this).schoolviewModel

        val directors = listOf(
            Director("Mike Litoris", "Jake Wharton School"),
            Director("Jack Goff", "Kotlin School"),
            Director("Chris P. Chicken", "JetBrains School")
        )
        val schools = listOf(
            School("Jake Wharton School"),
            School("Kotlin School"),
            School("JetBrains School")
        )
        val subjects = listOf(
            Subject("Dating for programmers"),
            Subject("Avoiding depression"),
            Subject("Bug Fix Meditation"),
            Subject("Logcat for Newbies"),
            Subject("How to use Google")
        )
        val students = listOf(
            Student("Beff Jezos", "2", "Kotlin School", 1),
//            Student("Mark Suckerberg", "5", "Jake Wharton School"),
//            Student("Gill Bates", "8", "Kotlin School"),
//            Student("Donny Jepp", "1", "Kotlin School")
        )
        val studentSubjectRelations = listOf(
            StudentSubjectCrossRef("Beff Jezos", "Dating for programmers"),
            StudentSubjectCrossRef("Beff Jezos", "Avoiding depression"),
            StudentSubjectCrossRef("Beff Jezos", "Bug Fix Meditation"),
            StudentSubjectCrossRef("Beff Jezos", "Logcat for Newbies"),
            StudentSubjectCrossRef("Mark Suckerberg", "Dating for programmers"),
            StudentSubjectCrossRef("Gill Bates", "How to use Google"),
            StudentSubjectCrossRef("Donny Jepp", "Logcat for Newbies"),
            StudentSubjectCrossRef("Hom Tanks", "Avoiding depression"),
            StudentSubjectCrossRef("Hom Tanks", "Dating for programmers")
        )

        val studentDetails = listOf(
            StudentDetails("Joseph", "32323", 1)
        )
        lifecycleScope.launch {
            directors.forEach { viewModel.insertDirector(it) }
            schools.forEach { viewModel.insertSchool(it) }
            subjects.forEach { viewModel.insertSubject(it) }
            students.forEach { viewModel.insertStudent(it) }
            studentDetails.forEach{viewModel.insertDetails(it)}
//            studentSubjectRelations.forEach { viewModel.insertStudentSubjectCrossRef(it) }

//            val schoolWithDirector = viewModel.getSchoolAndDirectorWithSchoolName("Kotlin School")


        }

//        override fun onDeleteUserClickListener(user: UserEntity) {
//            viewModel.deleteUserInfo(user)
//        }
//

        insertDetails.setOnClickListener {

            val studentDetails = StudentDetails( homeAddress.text.toString(), homePhone.text.toString(),0)
            viewModel.insertStudentDetails(studentDetails)
            viewModel.getStudentWithDetails()
        }

        btnRead!!.setOnClickListener {
            val stuData: List<StudentWithDetails> = viewModel.allStudentDetails.value!!
            stuData.forEach{

                val studentDetails = it.studentDetails
                for(i in 0 until studentDetails.size)
                Log.i("STUDENT_DATA", (studentDetails[i].homeAddress + ": " +
                        studentDetails[i].phoneNumber))

                Log.i("mainAct", " ${Thread.currentThread().name}")
            }
        }

        saveButton.setOnClickListener {
            val name  = nameInput.text.toString()
            val semester  = semesterInput.text.toString()
            val school = schoolInput.text.toString()
            if(saveButton.text.equals("Save")) {
                val user = Student( name, semester, school, 1)
                viewModel.insertStudent(user)
            } else {
//                val user = UserEntity(nameInput.getTag(nameInput.id).toString().toInt(), name, email, phone)
//                viewModel.updateUserInfo(user)
//                saveButton.setText("Save")
            }
            nameInput.setText("")
            semesterInput.setText("")
        }
    }

    override fun onDeleteUserClickListener(user: Student) {
        TODO("Not yet implemented")
    }

    override fun onItemClickListener(user: Student) {
        TODO("Not yet implemented")
    }


    // For updating the student
//    override fun onItemClickListener(student:Student) {
//        nameInput.setText(student.studentName)
//        emailInput.setText(student.semester)
//        phoneInput.setText(student.schoolName)
////        nameInput.setTag(nameInput.id, user.id)
//        saveButton.setText("Update")
//    }
}