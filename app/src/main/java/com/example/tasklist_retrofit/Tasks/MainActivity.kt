package com.example.tasklist_retrofit.Tasks

import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.tasklist_retrofit.R
import com.example.tasklist_retrofit.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sayaradz.api.ServiceProvider

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    var tasks= ArrayList<Task>()
    val api: ServiceProvider
    lateinit var customVAdapter: TaskAdapter
    lateinit var rv: RecyclerView

    init {
        api = ServiceBuilder.buildService(ServiceProvider::class.java)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intialiser()
        tasks = getTaskList()
    }

    private fun intialiser() {
        rv = findViewById<RecyclerView>(R.id.recyler_view_tasks)
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        rv.layoutManager = layout
        customVAdapter = TaskAdapter(this,tasks)
        rv.adapter = customVAdapter


    }


    private fun getTaskList() : ArrayList<Task>{


        val call = api.getTasks() // The request included the token
        var TaskRespond: List<Task>?
        var TaskList = ArrayList<Task>()

        call.enqueue(object : Callback<List<Task>> {
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                Log.i(TAG, "DisplayTaskList: call enqueue")

                if (!response.isSuccessful) {
                    Log.i(TAG, "CODE:" + response.code().toString())
                    return
                }

                TaskRespond = response.body()  // Getting the list
              //  Toast.makeText(this@MainActivity,TaskRespond.toString(),Toast.LENGTH_SHORT).show()
                if (!TaskRespond!!.isEmpty()) {
                    Log.i(TAG, "REPONSES: HERE is ALL THE TASKS FROM OUR SERVER:")
                    for (m in TaskRespond!!) {
                        var content = ""
                        content += "ID: " + m.id + "\n"
                        content += "Title: " + m.title + "\n"
                        content += "UserId: " + m.UserId
                        Log.i(TAG, "\n=========\n$content")
                        TaskList.add(m)
                    }
                    //Log.i("TAG",TaskList.toString())
                    customVAdapter.updateList(TaskList)
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                Log.i(TAG, "error CODE:" + t.message)
            }
        })
        return TaskList
    }
}
