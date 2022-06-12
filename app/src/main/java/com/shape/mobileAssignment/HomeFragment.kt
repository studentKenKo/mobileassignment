package com.shape.mobileAssignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    private lateinit var getFireBasedata: DatabaseReference
    lateinit var courseRecyclerview: RecyclerView
    lateinit var courseArrayList: ArrayList<CourseModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseRecyclerview = view.findViewById(R.id.course_list)
        courseRecyclerview.layoutManager = LinearLayoutManager(context)
        courseRecyclerview.setHasFixedSize(true)

        courseArrayList = arrayListOf()
        getUserData()

    }

    private fun getUserData() {

        getFireBasedata = FirebaseDatabase.getInstance("https://mobileassign-75563-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("course")

        getFireBasedata.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (dataSnapshot in snapshot.children) {
                        val course = dataSnapshot.getValue(CourseModel::class.java)
                        courseArrayList.add(course!!)

                    }
                    courseRecyclerview.adapter = CourseAdapter(courseArrayList)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }

}
