package io.muhsin.taskapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import io.muhsin.taskapp.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}