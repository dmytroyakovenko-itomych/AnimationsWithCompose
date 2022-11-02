package com.dimyak.animationwithcompose.presentation.examples.animateAsState

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private const val stateChangeInterval = 2000

enum class ColorState {
    RED,
    GREEN,
    BLUE;
}

fun ColorState.next(): ColorState {
    val nextIndex = ColorState.values().indexOf(this) + 1
    return ColorState.values().getOrElse(nextIndex) { ColorState.values().first() }
}

@Composable
fun AnimateColorAsStateScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var useRed by remember { mutableStateOf(false) }

            val color by animateColorAsState(
                targetValue = if (useRed) Color.Red else Color.Blue,
                animationSpec = tween(
                    durationMillis = 1500,
                    easing = LinearEasing
                ),
                finishedListener = { color ->

                }
            )

            Box(
                Modifier
                    .size(100.dp)
                    .background(color)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { useRed = !useRed }) {
                Text(text = "Change color")
            }

        }
    }
}
