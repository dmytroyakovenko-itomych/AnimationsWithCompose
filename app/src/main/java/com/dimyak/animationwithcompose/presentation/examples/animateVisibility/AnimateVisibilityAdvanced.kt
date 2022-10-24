package com.dimyak.animationwithcompose.presentation.examples.animateVisibility

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateVisibilityAdvanced() {
    Scaffold { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            var isVisible by remember { mutableStateOf(true) }

            Button(onClick = { isVisible = !isVisible }) {
                Text(text = if (isVisible) "Hide" else "Show")
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(),
                exit = fadeOut(tween(durationMillis = 600)),
            ) {
                val textColor by transition.animateColor(label = "color") { state ->
                    when (state) {
                        EnterExitState.PreEnter,
                        EnterExitState.Visible -> Color.Gray
                        EnterExitState.PostExit -> Color.Red
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Text slide in from left.",
                        modifier = Modifier.animateEnterExit(
                            enter = slideInHorizontally { offsetX -> -offsetX },
                            exit = ExitTransition.None
                        ),
                        color = textColor,
                    )

                    Text(
                        text = "Text slide in from right.",
                        modifier = Modifier.animateEnterExit(
                            enter = slideInHorizontally { offsetX -> offsetX },
                            exit = ExitTransition.None
                        ),
                        color = textColor,
                    )
                }
            }
        }
    }
}
