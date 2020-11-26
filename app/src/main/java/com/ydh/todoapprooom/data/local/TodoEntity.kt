package com.ydh.todoapprooom.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ydh.todoapprooom.model.TodoModel


@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "task") var task: String = "",
    @ColumnInfo(name = "status") var status: Boolean = false,
)

fun TodoEntity.toModel() : TodoModel = TodoModel(id, task, status)