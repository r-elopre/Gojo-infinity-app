package com.example.counter_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.counter_app.R
import com.example.counter_app.viewModel.CounterViewModel

@Composable
fun CounterScreen(counterViewModel: CounterViewModel = viewModel()) {
    // Observe the counter value and playback state from the ViewModel
    val counterValue by counterViewModel.counter.observeAsState(10)
    val isPlaying by counterViewModel.isPlaying.observeAsState(false)

    // Calculate the size of the image based on the counter value
    val imageSize = (20 + (counterValue - 10) * 0.8).dp

    // Calculate the transparency based on the counter value
    val imageAlpha = 0.5f - (counterValue - 10) * 0.005f

    Box(modifier = Modifier.fillMaxSize()) {
        VideoBackground(
            modifier = Modifier.fillMaxSize(),
            isPlaying = isPlaying,
            onPlaybackEnded = {
                counterViewModel.setPlayingState(false)
            }
        )

        // Header with the counter value
        Text(
            text = "Purple $counterValue %",
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 200.dp, end = 200.dp)
        )

        // Centered image on top of the video background
        Image(
            painter = painterResource(id = R.drawable.purple_remove), // Replace with your image resource ID
            contentDescription = null,
            modifier = Modifier
                .size(imageSize) // Adjust the size based on counter value
                .align(Alignment.Center)
                .graphicsLayer(alpha = imageAlpha) // Adjust transparency based on counter value
        )

        // Row with + and - icons at the bottom right
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 50.dp, end = 20.dp), // Adjust padding as needed
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Spacing between icons
        ) {
            IconButton(onClick = {
                if (!isPlaying) counterViewModel.increment()
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(90.dp), // Adjust the size as needed
                    tint = Color.White // Set the color to white
                )
            }
            IconButton(onClick = {
                if (!isPlaying) counterViewModel.decrement()
            }) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Remove",
                    modifier = Modifier.size(90.dp), // Adjust the size as needed
                    tint = Color.White // Set the color to white
                )
            }
        }
    }
}
