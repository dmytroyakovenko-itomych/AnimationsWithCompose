package com.dimyak.animationwithcompose.presentation.examples.keyframes

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val maxWidth = 300.dp
private val boxSize = 50.dp
private val maxBoxOffset = maxWidth - boxSize

@Composable
fun KeyframesScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            var start by remember { mutableStateOf(true) }

            Box(
                modifier = Modifier
                    .size(width = maxWidth, height = boxSize)
                    .border(2.dp, Color.Gray)
            ) {
                val boxOffset by animateDpAsState(
                    targetValue = if (start) 0.dp else maxBoxOffset,
                    animationSpec = keyframes {
                        durationMillis = 1500
                        125.dp at (1300) with LinearEasing
                        250.dp at (1500) with LinearEasing
                    }
                )
                Box(
                    modifier = Modifier
                        .offset(x = boxOffset)
                        .size(boxSize)
                        .background(Color.Blue)
                )
            }

            Button(onClick = { start = !start }) {
                Text(text = "Move")
            }
        }
    }
}
