package com.zypherdev.lambrk.activities

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zypherdev.lambrk.R
import com.zypherdev.lambrk.adapters.ChatAdapter
import com.zypherdev.lambrk.databinding.ActivityChatBinding
import com.zypherdev.lambrk.fragments.ChatBoxFragment
import com.zypherdev.lambrk.models.Chat


class ChatActivity : AppCompatActivity(), ChatBoxFragment.OnChatBoxListener {

    private lateinit var mContext: Context

    private lateinit var viewBinding: ActivityChatBinding

    private lateinit var chatAdapter: ChatAdapter
    private var chatList: MutableList<Chat> = ArrayList<Chat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mContext = this




        chatAdapter = ChatAdapter(mContext, chatList)
        viewBinding.rvChat.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, true)
        viewBinding.rvChat.adapter = chatAdapter


        viewBinding.ibChatBack.setOnClickListener {
            onBackPressed()
        }


    }



    override fun onSendMessage(message: String) {
        val chat = Chat(message, true)
        chatList.add(chat)
        chatAdapter.updateChatList(chatList)
    }
}