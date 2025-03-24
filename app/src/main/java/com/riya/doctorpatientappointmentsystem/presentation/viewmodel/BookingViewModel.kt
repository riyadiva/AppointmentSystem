package com.riya.doctorpatientappointmentsystem.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riya.doctorpatientappointmentsystem.data.roomdatabase.BookingEntity
import com.riya.doctorpatientappointmentsystem.data.roomdatabase.BookingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val repository: BookingRepository
) : ViewModel() {

    private val _bookings = MutableStateFlow<List<BookingEntity>>(emptyList())
    val bookings: StateFlow<List<BookingEntity>> = _bookings

    init {
        loadBookings()
    }

    fun loadBookings() {
        viewModelScope.launch {
            repository.getAllBookings().collect { bookingList ->
                _bookings.value = bookingList
            }
        }
    }

    fun insertBooking(booking: BookingEntity) {
        viewModelScope.launch {
            repository.insertBooking(booking)
        }
    }

    fun deleteBooking(booking: BookingEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBooking(booking)
            loadBookings() // Refresh the list after deletion
        }
    }
}


