package com.dimyak.animationwithcompose.presentation.examples.animated_circle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimyak.animationwithcompose.presentation.common.ui.theme.AnimationWithComposeTheme
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables.MapPlaceholder
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables.circle.CircleWithButton

@Composable
fun AnimatedCircleScreen(
    viewModel: AnimatedCircleViewModel
) {
    Scaffold { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {

            MapPlaceholder()

            val showNearbyPeople by viewModel.showNearbyPeople.collectAsState()
            FloatingActionButton(
                onClick = { viewModel.switchShowNearbyPeople() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Text(text = if (showNearbyPeople) "Hide" else "Show")
            }

            val user by viewModel.user.collectAsState()
            val showCircle by viewModel.showCircle.collectAsState()
            val nearbyPeople by viewModel.nearbyPeople.collectAsState()
            CircleWithButton(
                user = user,
                showCircle = showCircle,
                nearbyPeople = nearbyPeople,
                showCircleClicked = { viewModel.switchShowCircle(true) },
                hideCircleClicked = { viewModel.switchShowCircle(false) }
            )
        }
    }
}

@Preview
@Composable
private fun AnimatedCircleScreenPreview() {
    AnimationWithComposeTheme {
        AnimatedCircleScreen(
            viewModel = AnimatedCircleViewModel()
        )
    }
}
