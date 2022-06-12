package com.shape.mobileAssignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso.get
import java.util.ArrayList

class CourseAdapter(private val courseList: ArrayList<CourseModel>) :
        RecyclerView.Adapter<CourseAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val dataView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycleview_course_data,
            parent, false
        )
        return MyViewHolder(dataView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val getdatabaseInfo = courseList[position]

        holder.name.text = getdatabaseInfo.name
        holder.desc.text = getdatabaseInfo.desc
        val imageTarget = getdatabaseInfo.src
        get().load(imageTarget).into(holder.src)
    }

    class MyViewHolder(dataView: View) : RecyclerView.ViewHolder(dataView) {

        val src: ImageView = dataView.findViewById(R.id.course_image)
        val name: TextView = dataView.findViewById(R.id.course_name)
        val desc: TextView = dataView.findViewById(R.id.course_desc)

    }

    override fun getItemCount(): Int {

        return courseList.size
    }

}
