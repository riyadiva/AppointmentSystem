package com.riya.doctorpatientappointmentsystem.data.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {

    @Insert
    suspend fun insertBooking(booking: BookingEntity)

    @Query("SELECT * FROM bookings")
    fun getAllBookings(): Flow<List<BookingEntity>>  // Using Flow

    @Query("DELETE FROM bookings")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteBooking(booking: BookingEntity)

}
