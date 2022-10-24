package com.dimyak.animationwithcompose.presentation.examples.animateVisibility

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun AnimateVisibilityScreen() {
    Scaffold { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            var isVisible by remember { mutableStateOf(true) }

            Button(onClick = { isVisible = !isVisible }) {
                Text(text = if (isVisible) "Hide" else "Show")
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(tween()) + slideInVertically(tween()) { initialOffsetY ->
                    -initialOffsetY
                },
                exit = fadeOut(tween()) + slideOutVertically(tween()) { fullHeight ->
                    fullHeight
                },
            ) {
                Text(text = "Text to hide")
            }
        }
    }
}
