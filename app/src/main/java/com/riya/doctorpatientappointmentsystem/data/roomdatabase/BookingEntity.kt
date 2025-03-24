package com.riya.doctorpatientappointmentsystem.data.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val doctorId: String,
    val doctorName: String,
    val specialty: String,
    val date: String,
    val time: String
)
