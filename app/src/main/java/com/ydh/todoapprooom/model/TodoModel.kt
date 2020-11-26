package com.ydh.todoapprooom.model

import android.os.Parcelable
import com.ydh.todoapprooom.data.local.TodoEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoModel(val id: Long, var task: String, var status: Boolean = false, var favStatus: Boolean = false) : Parcelable

fun TodoModel.toEntity() : TodoEntity = TodoEntity(id, task, status, favStatus)