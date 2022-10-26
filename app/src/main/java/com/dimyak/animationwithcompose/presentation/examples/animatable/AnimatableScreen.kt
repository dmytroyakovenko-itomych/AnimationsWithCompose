package com.dimyak.animationwithcompose.presentation.examples.animatable

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color

@Composable
fun AnimatableScreen() {
    var ok by remember { mutableStateOf(false) }
    val color = remember { Animatable(Color.Gray) }
    LaunchedEffect(ok) {
        color.animateTo(
            targetValue = if (ok) Color.Green else Color.Red,
            animationSpec = tween(durationMillis = 1000),
        )
    }
    Box(
        Modifier
            .fillMaxSize()
            .drawBehind { drawRect(color.value) },
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { ok = !ok }) {
            Text(text = "Change")
        }
    }
}
