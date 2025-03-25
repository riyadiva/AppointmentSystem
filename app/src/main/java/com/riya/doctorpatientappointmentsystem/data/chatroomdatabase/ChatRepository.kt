package com.riya.doctorpatientappointmentsystem.data.chatroomdatabase

import kotlinx.coroutines.flow.Flow

class ChatRepository(private val dao: ChatDao) {

    suspend fun insertMessage(message: ChatEntity) {
        dao.insertMessage(message)
    }

    fun getAllMessages(): Flow<List<ChatEntity>> {
        return dao.getAllMessages()
    }

    suspend fun deleteMessage(message: ChatEntity) {
        dao.deleteMessage(message.id)
    }

    fun getMessagesByDoctorId(doctorId: String): Flow<List<ChatEntity>> {
        return dao.getMessagesByDoctorId(doctorId)
    }
}
