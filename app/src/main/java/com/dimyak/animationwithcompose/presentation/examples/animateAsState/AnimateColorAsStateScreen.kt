package com.dimyak.animationwithcompose.presentation.examples.animateAsState

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            var state by remember { mutableStateOf(ColorState.RED) }
            val color by animateColorAsState(
                targetValue = when (state) {
                    ColorState.RED -> Color.Red
                    ColorState.GREEN -> Color.Green
                    ColorState.BLUE -> Color.Blue
                },
                animationSpec = tween(
                    durationMillis = stateChangeInterval,
                    easing = LinearEasing
                ),
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .drawBehind {
                        drawRect(color)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.name, color = Color.White)
            }

            LaunchedEffect(Unit) {
                while (true) {
                    delay(stateChangeInterval.toLong())
                    state = state.next()
                }
            }
        }
    }
}
