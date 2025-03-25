package com.riya.doctorpatientappointmentsystem.di

import android.content.Context
import androidx.room.Room
import com.riya.doctorpatientappointmentsystem.data.roomdatabase.BookingDao
import com.riya.doctorpatientappointmentsystem.data.roomdatabase.BookingDatabase
import com.riya.doctorpatientappointmentsystem.data.roomdatabase.BookingRepository
import com.riya.doctorpatientappointmentsystem.data.chatroomdatabase.ChatDao
import com.riya.doctorpatientappointmentsystem.data.chatroomdatabase.ChatDatabase
import com.riya.doctorpatientappointmentsystem.data.chatroomdatabase.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Booking
    @Provides
    @Singleton
    fun provideBookingDatabase(
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

    // Chat
    @Provides
    @Singleton
    fun provideChatDatabase(
        @ApplicationContext appContext: Context
    ): ChatDatabase {
        return Room.databaseBuilder(
            appContext,
            ChatDatabase::class.java,
            "chat_database"
        ).build()
    }

    @Provides
    fun provideChatDao(database: ChatDatabase): ChatDao {
        return database.chatDao()
    }

    @Provides
    fun provideChatRepository(dao: ChatDao): ChatRepository {
        return ChatRepository(dao)
    }
}
