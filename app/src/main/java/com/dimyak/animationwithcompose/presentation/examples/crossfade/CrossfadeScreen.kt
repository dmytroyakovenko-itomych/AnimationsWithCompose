package com.dimyak.animationwithcompose.presentation.examples.crossfade

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class Page {
    A, B, C
}

@Composable
fun CrossfadeScreen() {
    Scaffold { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var page by remember { mutableStateOf(Page.A) }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { page = Page.A }) {
                    Text(text = "A")
                }
                Button(onClick = { page = Page.B }) {
                    Text(text = "B")
                }
                Button(onClick = { page = Page.C }) {
                    Text(text = "C")
                }
            }

            Crossfade(
                targetState = page,
                animationSpec = tween(),
                modifier = Modifier.fillMaxWidth()
            ) { state ->
                when (state) {
                    Page.A -> Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Red),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Page A", color = Color.White)
                    }
                    Page.B -> Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Blue),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Page B", color = Color.White)
                    }
                    Page.C -> {}
                }
            }

        }
    }
}
