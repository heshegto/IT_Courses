package com.bandeev.it_courses.course.presentation

import androidx.recyclerview.widget.DiffUtil.Callback
import com.bandeev.it_courses.domain.models.CourseList

class CourseDiffCallback(
    private val oldData: CourseList,
    private val newData: CourseList
) : Callback() {

    override fun getOldListSize() = oldData.size
    override fun getNewListSize() = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].id == newData[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }
}