package com.bandeev.it_courses.all_courses.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bandeev.it_courses.course.presentation.CourseAdapter
import com.bandeev.it_courses.domain.models.Course
import com.bandeev.it_courses.all_courses.R

import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AllCoursesFragment : Fragment(R.layout.all_courses_layout) {

    private val viewModel: AllCoursesViewModel by viewModel()

    private val adapter: CourseAdapter by inject {
        parametersOf(
            { course: Course -> viewModel.onFavouriteClicked(course) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycleView = view.findViewById<RecyclerView>(R.id.rcView_main)
        val informationText = view.findViewById<TextView>(R.id.informationText)
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is AllCoursesUiState.Loading -> {
                            informationText.visibility = View.VISIBLE
                            recycleView.visibility = View.GONE
                            informationText.text = getString(R.string.loading)
                        }

                        is AllCoursesUiState.Error -> {
                            informationText.visibility = View.VISIBLE
                            recycleView.visibility = View.GONE
                            informationText.text = getString(R.string.loading_error)
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.loading_error_message),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is AllCoursesUiState.Success -> {
                            informationText.visibility = View.GONE
                            recycleView.visibility = View.VISIBLE
                            adapter.updateData(uiState.courses)
                        }

                        else -> {
                            informationText.visibility = View.GONE
                            recycleView.visibility = View.GONE
                        }
                    }
                }
            }
        }

        view.findViewById<View>(R.id.sort).setOnClickListener {
            viewModel.onSortCoursesClicked()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            viewModel.loadAllCourses()
        }
    }
}
