package com.ydh.todoapprooom.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class TodoBodyInsert(
    @SerializedName("task")
    val task: String,
    @SerializedName("status")
    val status: String = "false")