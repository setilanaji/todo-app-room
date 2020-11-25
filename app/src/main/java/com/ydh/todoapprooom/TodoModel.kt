package com.ydh.todoapprooom

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoModel(val id: Long, var task: String, var status: Boolean = false) : Parcelable

fun TodoModel.toEntity() : TodoEntity = TodoEntity(id, task, status)