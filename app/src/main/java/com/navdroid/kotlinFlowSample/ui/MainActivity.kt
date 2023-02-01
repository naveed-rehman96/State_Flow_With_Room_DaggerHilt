package com.navdroid.kotlinFlowSample.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.navdroid.kotlinFlowSample.ConnectivityObserver
import com.navdroid.kotlinFlowSample.NetworkConnectivityObserver
import com.navdroid.kotlinFlowSample.databinding.ActivityMainBinding
import com.navdroid.kotlinFlowSample.model.MessageModel
import com.navdroid.kotlinFlowSample.states.MessageDataState
import com.navdroid.kotlinFlowSample.ui.adapter.MessageAdapter
import com.navdroid.kotlinFlowSample.viewModel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MyViewModel by viewModels()
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var adapter: MessageAdapter? = null

    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        lifecycleScope.launch {
            connectivityObserver.observe().collect {
                when (it) {
                    ConnectivityObserver.Status.Available -> {
                        Toast.makeText(this@MainActivity, "Available", Toast.LENGTH_SHORT)
                            .show()
                    }
                    ConnectivityObserver.Status.Unavailable -> {
                        Toast.makeText(this@MainActivity, "Unavailable", Toast.LENGTH_SHORT)
                            .show()
                    }
                    ConnectivityObserver.Status.Losing -> {
                        Toast.makeText(this@MainActivity, "Losing", Toast.LENGTH_SHORT)
                            .show()
                    }
                    ConnectivityObserver.Status.Lost -> {
                        Toast.makeText(this@MainActivity, "Lost", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        viewModel.getAllMessages()
        adapter = MessageAdapter()
        binding.recyclerView.adapter = adapter

        binding.btnSend.setOnClickListener {
            viewModel.insertMessage(MessageModel(message = binding.editText.text.toString()))
            binding.editText.setText("")
        }

        lifecycleScope.launch {
            viewModel.allCvListStateFlow.collect {
                when (it) {
                    MessageDataState.Empty -> {
                        Toast.makeText(this@MainActivity, "No Message Found", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is MessageDataState.Failure -> {
                        Toast.makeText(this@MainActivity, "Unable To Fetch", Toast.LENGTH_SHORT)
                            .show()
                    }
                    MessageDataState.Loading -> {
                        Toast.makeText(
                            this@MainActivity,
                            "Loading Data Please Wait!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is MessageDataState.Success -> {
                        adapter?.differ?.submitList(it.response)

                        Toast.makeText(
                            this@MainActivity,
                            "Success ${it.response.size}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}
