package com.riya.doctorpatientappointmentsystem.di

import android.content.Context
import androidx.room.Room
import com.riya.doctorpatientappointmentsystem.data.roomdatabase.BookingDao
import com.riya.doctorpatientappointmentsystem.data.roomdatabase.BookingDatabase
import com.riya.doctorpatientappointmentsystem.data.roomdatabase.BookingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): BookingDatabase {
        return Room.databaseBuilder(
            appContext,
            BookingDatabase::class.java,
            "booking_database"
        ).build()
    }

    @Provides
    fun provideBookingDao(database: BookingDatabase): BookingDao {
        return database.bookingDao()
    }

    @Provides
    fun provideBookingRepository(dao: BookingDao): BookingRepository {
        return BookingRepository(dao)
    }
}
