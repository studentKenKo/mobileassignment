package com.shape.mobileAssignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private fun retrieveData() {
        val UserQuery = database.child("User")

        UserQuery.addChildEventListener(object : ChildEventListener {
            // TODO: implement the ChildEventListener methods as documented above
            // [START_EXCLUDE]
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val User = dataSnapshot.getValue<UserModel>()
                if (User != null) {
                //    UserList.add(User)
                }
                // courseList for ListView's content
            }
            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
            // [END_EXCLUDE]
        })
    }

    override fun onStart() {
        super.onStart()
        retrieveData()
    }

    override fun onStop() {
        super.onStop()
    }

    companion object {

    }
}