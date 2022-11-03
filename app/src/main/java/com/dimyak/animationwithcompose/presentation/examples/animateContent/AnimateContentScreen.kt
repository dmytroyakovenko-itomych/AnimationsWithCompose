@file:OptIn(ExperimentalAnimationApi::class)

package com.dimyak.animationwithcompose.presentation.examples.animateContent

import androidx.compose.animation.*
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
fun AnimateContentScreen() {
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

            AnimatedContent(
                targetState = page,
                transitionSpec = {
                    slideInVertically { -it } with slideOutVertically { it }
                }
            ) { state ->
                when (state) {
                    Page.A -> Page("Page A", Color.Red)
                    Page.B -> Page("Page B", Color.Green)
                    Page.C -> Page("Page C", Color.Blue)
                }
            }
        }
    }
}

@Composable
fun Page(name: String, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(text = name, color = Color.White)
    }
}
