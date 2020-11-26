package com.ydh.todoapprooom.model

import com.google.gson.annotations.SerializedName

data class TodoBodyUpdate(
    @SerializedName("task")
    val task: String,
    @SerializedName("status")
    val status: Boolean = false)