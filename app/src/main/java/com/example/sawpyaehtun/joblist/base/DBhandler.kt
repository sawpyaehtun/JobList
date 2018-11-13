package com.example.sawpyaehtun.joblist.base

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sawpyaehtun.joblist.model.Job

class DBhandler(context: Context) : SQLiteOpenHelper(
    context, DBhandler.DB_NAME, null, DBhandler.DB_VERSION
) {

  override fun onCreate(db: SQLiteDatabase) {
    var createtable = "CREATE TABLE $TODOTABLE (" +
        ID + " INTEGER PRIMARY KEY," +
        JNAME + " TEXT);"
    db.execSQL(createtable)

    createtable = "CREATE TABLE $COMPLETED (" +
        ID + " INTEGER PRIMARY KEY," +
        JNAME + " TEXT);"
    db.execSQL(createtable)
  }

  override fun onUpgrade(
    db: SQLiteDatabase,
    oldVersion: Int,
    newVersion: Int
  ) {
    var DROP_TABLE = "DROP TABLE IF EXISTS " + TODOTABLE
    db.execSQL(DROP_TABLE)

    DROP_TABLE = "DROP TABLE IF EXISTS " + COMPLETED
    db.execSQL(DROP_TABLE)
    onCreate(db)
  }

  fun addJob(
    job: Job,
    table: String
  ): Boolean {
    val db = this.writableDatabase
    val values = ContentValues()
    values.put(JNAME, job.j_name)
    val success = db.insert(table, null, values)
    db.close()
    return (Integer.parseInt("$success") != -1)
  }

  fun getAjob(
    id: Int,
    table: String
  ): Job {
    var jb = Job(0, "")
    val db = writableDatabase
    val selectQuery = "SELECT  * FROM $table WHERE $ID = $id"
    val cursor = db.rawQuery(selectQuery, null)
    if (cursor != null) {
      while (cursor.moveToNext()) {
        jb.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
        jb.j_name = cursor.getString(cursor.getColumnIndex(JNAME))
      }
    }
    cursor.close()
    return jb
  }

  fun getallJobList(table: String): ArrayList<Job> {
    val joblist = ArrayList<Job>()
    val db = writableDatabase
    val selectQuery = "SELECT  * FROM $table ORDER BY id DESC"
    val cursor = db.rawQuery(selectQuery, null)
    if (cursor != null) {
      while (cursor.moveToNext()) {
        joblist.add(
            Job(
                cursor.getInt(cursor.getColumnIndex(ID)), cursor.getString(
                cursor.getColumnIndex(
                    JNAME
                )
            )
            )
        )
      }
    }
    cursor.close()
    return joblist
  }

  fun deletejob(
    id: Int,
    table: String
  ): Boolean {
    val db = this.writableDatabase
    val success = db.delete(table, ID + "=?", arrayOf(id.toString()))
        .toLong()
    db.close()
    return Integer.parseInt("$success") != -1
  }

  companion object {

    private val DB_VERSION = 1
    private val DB_NAME = "JobDB"
    private val TODOTABLE = "TODO"
    private val COMPLETED = "COMPLETED"
    private val ID = "id"
    private val JNAME = "jname"
  }
}