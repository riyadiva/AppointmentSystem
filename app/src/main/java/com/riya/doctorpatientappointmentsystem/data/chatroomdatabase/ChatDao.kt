package com.riya.doctorpatientappointmentsystem.data.chatroomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Insert
    suspend fun insertMessage(message: ChatEntity)

    @Query("SELECT * FROM chats WHERE (senderId = :user1 AND receiverId = :user2) OR (senderId = :user2 AND receiverId = :user1) ORDER BY timestamp ASC")
    fun getChatBetweenUsers(user1: String, user2: String): Flow<List<ChatEntity>>

    @Query("SELECT * FROM chats")
    fun getAllMessages(): Flow<List<ChatEntity>>

    @Query("DELETE FROM chats WHERE id = :messageId")
    suspend fun deleteMessage(messageId: Int)

    @Query("DELETE FROM chats")
    suspend fun deleteAllMessages()

    @Query("SELECT * FROM chats WHERE doctorId = :doctorId ORDER BY timestamp ASC")
    fun getMessagesByDoctorId(doctorId: String): Flow<List<ChatEntity>>

}
