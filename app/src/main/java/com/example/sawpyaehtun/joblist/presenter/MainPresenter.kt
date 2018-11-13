package com.example.sawpyaehtun.joblist.presenter

import com.example.sawpyaehtun.joblist.interactor.JobInteractor
import com.example.sawpyaehtun.joblist.model.Job
import com.example.sawpyaehtun.joblist.views.MainView


class MainPresenter(val mainView: MainView, val jobInteractor: JobInteractor){

  private val TODOTABLE = "TODO"
  private val COMPLETED = "COMPLETED"

  fun  addJob(jobName : String,table : String){
    when {
      jobName.trim() == "" -> mainView.ShowErrorMessage()
      else -> {
        val newjob = Job(0, jobName)
        when {
          jobInteractor.addingJob(newjob, table) -> mainView.showDataInRecyclerView()
          }
        }
      }
    }

  fun changeJobvalue( id : Int, con : String)
  {
    when (con) {
      TODOTABLE -> {
        val jb = jobInteractor.gettingAJob(id, TODOTABLE)
        when {
          jobInteractor.deletingJob(id, TODOTABLE) -> jobInteractor.addingJob(jb, COMPLETED)
        }
      }
      else -> {
        val jb = jobInteractor.gettingAJob(id, COMPLETED)
        when {
          jobInteractor.deletingJob(id, COMPLETED) -> jobInteractor.addingJob(jb, TODOTABLE)
        }
      }
    }
    mainView.replaceDataFromRecyclerView()
  }

  fun getallJobList(table: String) : ArrayList<Job>
  {
    return jobInteractor.getall(table)
  }

}
