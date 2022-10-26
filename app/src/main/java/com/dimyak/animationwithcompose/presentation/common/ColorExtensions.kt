package com.dimyak.animationwithcompose.presentation.common

import androidx.annotation.ColorInt

@ColorInt
fun Int.toComposeColor() = androidx.compose.ui.graphics.Color(this)
