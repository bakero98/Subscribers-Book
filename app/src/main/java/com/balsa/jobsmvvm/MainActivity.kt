package com.balsa.jobsmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.balsa.jobsmvvm.databinding.ActivityMainBinding
import com.balsa.jobsmvvm.db.Subscriber
import com.balsa.jobsmvvm.db.SubscriberDatabase
import com.balsa.jobsmvvm.db.SubscriberRepository
import com.balsa.jobsmvvm.ui.subscriber.SubscriberViewModelFactory
import com.balsa.jobsmvvm.ui.subscriber.SubscriberViewModel
import com.balsa.jobsmvvm.ui.subscriber.SubscribersRecycleViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this
        initRecyclerView()

        setContentView(binding.root)
    }

    private fun initRecyclerView() {
        binding.subscribersRecycleView.layoutManager = LinearLayoutManager(this)
        displaySubscribersList()
    }

    private fun displaySubscribersList() {
        subscriberViewModel.subscribers.observe(this, Observer { listOfSubscribers ->
            binding.subscribersRecycleView.adapter = SubscribersRecycleViewAdapter(listOfSubscribers) { selectedItem: Subscriber ->
                subscriberListItemClicked(
                    selectedItem
                )
            }
        })
    }

    private fun subscriberListItemClicked(subscriber: Subscriber) {
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }

}