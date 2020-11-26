package com.ydh.todoapprooom.data.remote

import com.google.gson.annotations.SerializedName
import com.ydh.todoapprooom.model.TodoModel

data class TodoResponse(
    @SerializedName("data")
    val data: List<TodoModel>
)