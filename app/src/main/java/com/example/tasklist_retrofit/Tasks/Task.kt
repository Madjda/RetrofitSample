package com.example.tasklist_retrofit.Tasks

import com.google.gson.annotations.SerializedName


data class Task(
    @SerializedName("userId")
    var UserId:Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title:String,
    @SerializedName("completed")
    var completed : Boolean
)
