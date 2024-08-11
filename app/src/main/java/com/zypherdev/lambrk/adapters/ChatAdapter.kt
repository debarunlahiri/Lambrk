package com.zypherdev.lambrk.adapters

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zypherdev.lambrk.R
import com.zypherdev.lambrk.databinding.ItemListMessageLayoutBinding
import com.zypherdev.lambrk.models.Chat

class ChatAdapter(var mContext: Context, var chatList:MutableList<Chat>):
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    class ViewHolder(val viewBinding: ItemListMessageLayoutBinding): RecyclerView.ViewHolder(viewBinding.root)

    fun updateChatList(chatList:MutableList<Chat>) {
        this.chatList = chatList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListMessageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = chatList[position]

        val layoutParams = holder.viewBinding.cvMessage.layoutParams as FrameLayout.LayoutParams
        holder.viewBinding.tvMessage.text = chat.message
        if (chat.isSender) {
            layoutParams.gravity = Gravity.END
        } else {
            layoutParams.gravity = Gravity.START
        }
    }

    override fun getItemViewType(position: Int): Int {
        val chat = chatList[position]

        return position

    }
}