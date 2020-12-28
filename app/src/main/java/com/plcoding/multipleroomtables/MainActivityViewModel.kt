package com.plcoding.multipleroomtables

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.plcoding.multipleroomtables.entities.*
import com.plcoding.multipleroomtables.entities.relations.StudentWithDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    lateinit var allStudentDetails : MutableLiveData<List<StudentWithDetails>>
    val schoolDao = SchoolDatabase.getInstance(getApplication())?.schoolDao
    init{
        allStudentDetails = MutableLiveData()
          getStudentWithDetails()
    }

    fun getAllDetailsObservers(): MutableLiveData<List<StudentWithDetails>> {
        return allStudentDetails
    }

//    fun getSchoolWithStudents(schoolName:String) {
//        var list = listOf<SchoolWithStudents>()
//        val schoolDao = SchoolDatabase.getInstance((getApplication())).schoolDao
//
//        //We want to get a list of students who belong to a school
//        GlobalScope.launch {
//            list = schoolDao?.getStu(schoolName)
//
//        }
//        allStudentDetails.postValue(list)
//    }


    fun getStudentWithDetails() {
        var list = listOf<StudentWithDetails>()
        val schoolDao = SchoolDatabase.getInstance((getApplication())).schoolDao
        //We want to get a list of details belonging to a student
        // Based on the student ID

        // DOne on a background thread
        GlobalScope.launch(Dispatchers.IO) {
            list = schoolDao?.getStudentWithDetails()
            Log.i("data found", list.toString())
            allStudentDetails.postValue(list)
        }
    }


    fun insertStudent(entity: Student){
        GlobalScope.launch(Dispatchers.IO) {
            schoolDao?.insertStudent(entity)
        }
     //   getSchoolWithStudents(entity.schoolName)
    }


    fun insertDetails(entity: StudentDetails){
        GlobalScope.launch(Dispatchers.IO) {
            schoolDao?.insertStudentDetails(entity)
        }
        //   getSchoolWithStudents(entity.schoolName)
    }
    fun insertStudentDetails(entity: StudentDetails){
        GlobalScope.launch(Dispatchers.IO) {
            schoolDao?.insertStudentDetails(entity)
        }
    }
    fun insertSchool(item: School) =
            GlobalScope.launch(Dispatchers.IO) {
                schoolDao?.insertSchool(item)
            }

    fun insertSubject(item: Subject) =
            GlobalScope.launch(Dispatchers.IO) {
                schoolDao?.insertSubject(item)
            }

    fun insertDirector(item: Director){
        GlobalScope.launch(Dispatchers.IO) {
            schoolDao?.insertDirector(item)
        }

    }

//    fun updateUserInfo(entity: UserEntity){
//        val userDao = RoomAppDb.getAppDatabase(getApplication())?.userDao()
//        userDao?.updateUser(entity)
//        getAllUsers()
//    }
//
//    fun deleteUserInfo(entity: UserEntity){
//        val userDao = RoomAppDb.getAppDatabase(getApplication())?.userDao()
//        userDao?.deleteUser(entity)
//        getAllUsers()
//    }
}