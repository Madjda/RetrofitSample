package com.example.tasklist_retrofit.Tasks

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tasklist_retrofit.R


class TaskAdapter(val context: Context, val tasks: ArrayList<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val newsCard = LayoutInflater.from(context).inflate(R.layout.vert_task_view, p0, false)
        return ViewHolder(newsCard)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val newsContent = tasks[p1]
        p0.bind(newsContent)
    }

    fun updateList(newlist: List<Task>) {
        tasks.clear()
        tasks.addAll(newlist)
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(private val objet: View) : RecyclerView.ViewHolder(objet) {
        init {

        }

        fun bind(item: Task) {
            objet.findViewById<TextView>(R.id.task_title).text = item.title
            objet.findViewById<TextView>(R.id.task_id).text = item.id.toString()
            objet.findViewById<TextView>(R.id.task_userId).text = item.UserId.toString()
            if (item.completed)
                objet.findViewById<TextView>(R.id.task_state).text = "Completed"
            else objet.findViewById<TextView>(R.id.task_state).text = "UnCompleted"

        }


    }
}