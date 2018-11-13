package com.example.sawpyaehtun.joblist.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sawpyaehtun.joblist.R
import com.example.sawpyaehtun.joblist.adapter.JobAdapter
import com.example.sawpyaehtun.joblist.base.DBhandler
import com.example.sawpyaehtun.joblist.interactor.JobInteractor
import com.example.sawpyaehtun.joblist.listner.ChangeJobListner
import com.example.sawpyaehtun.joblist.presenter.MainPresenter
import com.example.sawpyaehtun.joblist.views.MainView

class MainActivity : AppCompatActivity(), ChangeJobListner, MainView {

  private lateinit var insret: Button
  private lateinit var jname: EditText
  private lateinit var todoList: RecyclerView
  private lateinit var completedList: RecyclerView
  private lateinit var todojbadapter: JobAdapter
  private lateinit var completejbadapter: JobAdapter
  private lateinit var viewManager: RecyclerView.LayoutManager
  private lateinit var dBhandler: DBhandler
  private val TODOTABLE = "TODO"
  private val COMPLETED = "COMPLETED"
  private lateinit var jobInteractor: JobInteractor
  private lateinit var mainPresenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    jobInteractor = JobInteractor(this)
    mainPresenter = MainPresenter(this, jobInteractor)

    insret = findViewById(R.id.insert)
    jname = findViewById(R.id.jname)
    todoList = findViewById(R.id.todolist)
    completedList = findViewById(R.id.completedlist)
    todojbadapter = JobAdapter(this)
    completejbadapter = JobAdapter(this)
    dBhandler = DBhandler(this)
    jobInteractor = JobInteractor(this)

    viewJobList()
    insret.setOnClickListener {
      mainPresenter.addJob(jname.text.toString(), TODOTABLE)
    }
  }

  private fun viewJobList() {
    todojbadapter.addJoblist(mainPresenter.getallJobList(TODOTABLE), TODOTABLE)
    viewManager = LinearLayoutManager(this)
    todoList.adapter = todojbadapter
    todoList.layoutManager = viewManager

    completejbadapter.addJoblist(mainPresenter.getallJobList(COMPLETED), COMPLETED)
    viewManager = LinearLayoutManager(this)
    completedList.adapter = completejbadapter
    completedList.layoutManager = viewManager

  }

  override fun ShowErrorMessage() {
    Toast.makeText(this, "Please Insert A Value", Toast.LENGTH_SHORT)
        .show()
  }

  override fun showDataInRecyclerView() {
    viewJobList()
    jname.text.clear()
    Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT)
        .show()
  }

  override fun replaceDataFromRecyclerView() {
    viewJobList()
  }


  override fun changejob(
    id: Int,
    con: String
  ) {
    mainPresenter.changeJobvalue(id, con)
  }

}
