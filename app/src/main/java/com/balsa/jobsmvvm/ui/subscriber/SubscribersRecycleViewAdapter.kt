package com.balsa.jobsmvvm.ui.subscriber

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.balsa.jobsmvvm.R
import com.balsa.jobsmvvm.databinding.SubscriberListDataBinding
import com.balsa.jobsmvvm.db.Subscriber

class SubscribersRecycleViewAdapter(
    private val subscribers: List<Subscriber>,
    private val clickListener:(Subscriber) -> Unit
) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SubscriberListDataBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.subscriber_list_data, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribers[position], clickListener)
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }

}

class MyViewHolder(val binding: SubscriberListDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(subscriber: Subscriber, clickListener:(Subscriber) -> Unit) {
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.subscriberListItemView.setOnClickListener{
            clickListener(subscriber)
        }
    }
}