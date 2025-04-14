package com.riya.doctorpatientappointmentsystem.data.chatroomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ChatEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}
