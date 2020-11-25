package com.ydh.todoapprooom

import com.google.gson.annotations.SerializedName
import com.ydh.todoapprooom.TodoModel

data class TodoResponse(
    @SerializedName("data")
    val data: List<TodoModel>
)