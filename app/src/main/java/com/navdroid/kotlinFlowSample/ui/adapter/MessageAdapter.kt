package com.navdroid.kotlinFlowSample.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.navdroid.kotlinFlowSample.databinding.RcvItemBinding
import com.navdroid.kotlinFlowSample.model.MessageModel

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MyViewHolder>() {

    class MyViewHolder(var binding: RcvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(messageModel: MessageModel) {
            binding.messageText.text = messageModel.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RcvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    private val differCallback = object : DiffUtil.ItemCallback<MessageModel>() {
        override fun areItemsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}
