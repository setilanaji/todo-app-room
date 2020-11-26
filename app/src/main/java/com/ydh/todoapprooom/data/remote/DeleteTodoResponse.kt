package com.ydh.todoapprooom.data.remote

import com.google.gson.annotations.SerializedName

data class DeleteTodoResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: String

)