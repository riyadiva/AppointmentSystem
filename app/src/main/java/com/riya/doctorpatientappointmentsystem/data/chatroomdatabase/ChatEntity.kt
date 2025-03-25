package com.riya.doctorpatientappointmentsystem.data.chatroomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "chats")
data class ChatEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val doctorId: String, // Needed for getMessagesByDoctorId
    val senderId: String,
    val receiverId: String,
    val message: String,
    val timestamp: Long

)
