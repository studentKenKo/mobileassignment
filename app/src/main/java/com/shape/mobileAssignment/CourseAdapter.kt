package com.shape.mobileAssignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

    class CourseAdapter(private val courseList: ArrayList<CourseModel>) :
        RecyclerView.Adapter<CourseAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseAdapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.coursedata,
            parent, false         )
        return CourseAdapter.MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val getdatabaseInfo = courseList[position]

        holder.name.text = getdatabaseInfo.name
        holder.sdesc.text = getdatabaseInfo.desc
        val imageTarget = getdatabaseInfo.src
        Picasso.get().load(imageTarget).into(holder.src)
    }

        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val src: ImageView = itemView.findViewById(R.id.course_image)
            val name: TextView = itemView.findViewById(R.id.course_name)
            val sdesc: TextView = itemView.findViewById(R.id.course_sdesc)

        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }
    }


    }