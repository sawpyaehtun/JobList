package com.example.sawpyaehtun.joblist.interactor

import android.content.Context
import com.example.sawpyaehtun.joblist.base.DBhandler
import com.example.sawpyaehtun.joblist.model.Job

class JobInteractor(context: Context) {

  var dBhandler = DBhandler(context)

  fun addingJob (job : Job,table: String) : Boolean
  {
    return dBhandler.addJob(job,table)
  }

  fun deletingJob ( id : Int , table : String) : Boolean
  {
    return dBhandler.deletejob(id,table)
  }

  fun gettingAJob(id: Int,table: String) : Job
  {
    return dBhandler.getAjob(id,table)
  }

  fun getall(table: String) : ArrayList<Job>
  {
    return dBhandler.getallJobList(table)
  }


}