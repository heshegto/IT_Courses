package com.bandeev.it_courses.course.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bandeev.it_courses.course.R
import com.bandeev.it_courses.domain.models.Course
import com.bandeev.it_courses.domain.models.CourseList
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class CourseAdapter(
    var dataSet: CourseList,
    val onFavouriteClick: (Course) -> Unit
) : RecyclerView.Adapter<CourseViewHolder>() {

    private val formatter = DateTimeFormatter.ofPattern(
        "d MMMM yyyy",
        Locale.getDefault()
    )

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_course, viewGroup, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CourseViewHolder, position: Int) {
        val course = dataSet.courses[position]
        viewHolder.title.text = course.title
        viewHolder.text.text = course.text
        viewHolder.price.text = course.price
        viewHolder.rate.text = course.rate
        viewHolder.startDate.text = LocalDate.parse(course.startDate).format(formatter)
        viewHolder.publishDate = LocalDate.parse(course.publishDate)
        viewHolder.id = course.id
        viewHolder.btnFavourite.isSelected = course.hasLike

        viewHolder.btnFavourite.setOnClickListener {
            it.isSelected = !it.isSelected
            onFavouriteClick(course)
        }
    }

    fun updateData(newData: CourseList) {
        this.dataSet = newData
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.getSize()
}