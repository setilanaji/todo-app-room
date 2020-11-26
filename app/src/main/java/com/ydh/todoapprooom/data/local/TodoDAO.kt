package com.ydh.todoapprooom.data.local

import androidx.room.*

@Dao
interface TodoDAO {
    @Query("SELECT * FROM todo ORDER BY ID DESC")
    fun getAllTodo(): List<TodoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createTodo(entity: TodoEntity): Long

    @Update
    fun updateTodo(entity: TodoEntity)

    @Delete
    fun deleteTodo(entity: TodoEntity)
}