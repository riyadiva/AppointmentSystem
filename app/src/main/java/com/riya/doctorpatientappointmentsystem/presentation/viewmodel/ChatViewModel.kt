package com.riya.doctorpatientappointmentsystem.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riya.doctorpatientappointmentsystem.data.chatroomdatabase.ChatEntity
import com.riya.doctorpatientappointmentsystem.data.chatroomdatabase.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {

    private val _chatMessages = MutableStateFlow<List<ChatEntity>>(emptyList())
    val chatMessages: StateFlow<List<ChatEntity>> = _chatMessages

    fun loadMessages() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMessages().collectLatest { messages ->
                _chatMessages.value = messages
            }
        }
    }

    fun sendMessage(message: ChatEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMessage(message)
        }
    }

    fun deleteMessage(message: ChatEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMessage(message)
        }
    }
}
