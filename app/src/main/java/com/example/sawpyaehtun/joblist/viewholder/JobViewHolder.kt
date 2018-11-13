package com.example.sawpyaehtun.joblist.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.sawpyaehtun.joblist.R
import com.example.sawpyaehtun.joblist.listner.ChangeJobListner
import com.example.sawpyaehtun.joblist.model.Job

class JobViewHolder(
  private val view: View,
  private val jobs: ArrayList<Job>,
  private val changeJobListner: ChangeJobListner
) : RecyclerView.ViewHolder(view) {
  fun onBind(
    position: Int,
    con: String
  ) {
    val jobname: TextView = view.findViewById(R.id.job)
    jobname.text = jobs[position].j_name
    jobname.setOnClickListener {
      changeJobListner.changejob(jobs[position].id, con)
    }
  }

}

