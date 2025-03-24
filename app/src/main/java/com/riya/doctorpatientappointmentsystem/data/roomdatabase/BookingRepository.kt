package com.riya.doctorpatientappointmentsystem.data.roomdatabase

import kotlinx.coroutines.flow.Flow

class BookingRepository(private val dao: BookingDao) {

    suspend fun insertBooking(booking: BookingEntity) {
        dao.insertBooking(booking)
    }

    fun getAllBookings(): Flow<List<BookingEntity>> {
        return dao.getAllBookings()
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun deleteBooking(booking: BookingEntity) {
        dao.deleteBooking(booking)
    }

}
