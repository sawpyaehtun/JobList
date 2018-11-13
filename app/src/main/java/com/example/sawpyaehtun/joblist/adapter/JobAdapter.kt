package com.example.sawpyaehtun.joblist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sawpyaehtun.joblist.R
import com.example.sawpyaehtun.joblist.listner.ChangeJobListner
import com.example.sawpyaehtun.joblist.model.Job
import com.example.sawpyaehtun.joblist.viewholder.JobViewHolder


class JobAdapter(private val changeJobListner: ChangeJobListner) : RecyclerView.Adapter<JobViewHolder>() {

  var jobs = ArrayList<Job>()
  lateinit var con: String

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): JobViewHolder {
    val v: View = LayoutInflater.from(parent.context).inflate(
        R.layout.job_item, parent, false
    ) as View
    return JobViewHolder(v, jobs, changeJobListner)
  }

  override fun getItemCount(): Int {
    return jobs.size
  }

  override fun onBindViewHolder(
    holder: JobViewHolder,
    position: Int
  ) {
    holder.onBind(position, con)
  }

  fun addJoblist(
    jobs: ArrayList<Job>,
    con: String
  ) {
    this.jobs = jobs
    this.con = con
  }

}
