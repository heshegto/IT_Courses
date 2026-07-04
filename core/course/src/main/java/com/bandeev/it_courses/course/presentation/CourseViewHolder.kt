package com.bandeev.it_courses.course.presentation

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bandeev.it_courses.course.R
import java.time.LocalDate

class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var id: Int = -1
    val title: TextView = view.findViewById(R.id.tv_title)
    val text: TextView = view.findViewById(R.id.tv_description)
    val price: TextView = view.findViewById(R.id.tv_price)
    val rate: TextView = view.findViewById(R.id.tv_rate)
    val startDate: TextView = view.findViewById(R.id.tv_date)
    val tvMore: TextView = view.findViewById(R.id.tv_more)
    val btnFavourite: ImageButton = view.findViewById(R.id.imButton_favourite)
    var publishDate: LocalDate = LocalDate.MIN
}