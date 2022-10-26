package com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.dimyak.animationwithcompose.R
import com.dimyak.animationwithcompose.presentation.common.ui.theme.AnimationWithComposeTheme

@Composable
fun MapPlaceholder() {
    Image(
        painter = painterResource(id = R.drawable.ic_map_placeholder),
        contentDescription = "Map",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}

@Preview
@Composable
fun MapPlaceholderPreview() {
    AnimationWithComposeTheme {
        MapPlaceholder()
    }
}
