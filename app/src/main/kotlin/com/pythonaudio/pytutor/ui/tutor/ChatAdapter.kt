package com.pythonaudio.pytutor.ui.tutor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pythonaudio.pytutor.R
import com.pythonaudio.pytutor.model.ChatMessage

class ChatAdapter(private val messages: List<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_USER = 0
        private const val VIEW_TYPE_ASSISTANT = 1
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMessage: TextView = view.findViewById(R.id.tv_chat_message)
    }

    inner class AssistantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMessage: TextView = view.findViewById(R.id.tv_chat_message)
        val tvAvatar: TextView = view.findViewById(R.id.tv_avatar)
    }

    override fun getItemViewType(position: Int): Int =
        if (messages[position].role == "user") VIEW_TYPE_USER else VIEW_TYPE_ASSISTANT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_USER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chat_user, parent, false)
            UserViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_chat_assistant, parent, false)
            AssistantViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        when (holder) {
            is UserViewHolder -> holder.tvMessage.text = message.content
            is AssistantViewHolder -> {
                holder.tvMessage.text = message.content
                holder.tvAvatar.text = "🎵"
            }
        }
    }

    override fun getItemCount() = messages.size
}
