package com.example.counter_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    // LiveData to hold the counter value
    private val _counter = MutableLiveData(10)
    val counter: LiveData<Int> = _counter

    // LiveData to hold the playback state of the video
    private val _isPlaying = MutableLiveData(false)
    val isPlaying: LiveData<Boolean> = _isPlaying

    // Function to increment the counter
    fun increment() {
        if (_isPlaying.value == true) return

        val currentValue = _counter.value ?: 10
        val newValue = (currentValue + 10) % 110
        if (newValue == 100) {
            _isPlaying.value = true
        }
        _counter.value = if (newValue == 0) 10 else newValue
    }

    // Function to decrement the counter
    fun decrement() {
        if (_isPlaying.value == true) return

        val currentValue = _counter.value ?: 10
        if (currentValue > 0) {
            _counter.value = currentValue - 10
        }
    }

    // Function to set the playing state
    fun setPlayingState(isPlaying: Boolean) {
        _isPlaying.value = isPlaying
    }
}
