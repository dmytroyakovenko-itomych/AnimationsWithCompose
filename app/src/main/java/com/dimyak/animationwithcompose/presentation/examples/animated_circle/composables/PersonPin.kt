package com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dimyak.animationwithcompose.presentation.common.ui.theme.AnimationWithComposeTheme

@Composable
fun PersonPin(
    color: Color,
    title: String,
    circleSize: Dp = 64.dp,
    circleInnerBorder: Dp = 4.dp,
    nameplateTopPadding: Dp = circleSize - (circleSize.value * 20 / 100).dp
) {
    Box(contentAlignment = Alignment.TopCenter) {

        Box(
            modifier = Modifier
                .size(circleSize)
                .background(color, CircleShape)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            //Instead of image
            Box(
                modifier = Modifier
                    .padding(circleInnerBorder)
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.5f), CircleShape)
            )
        }

        Surface(
            modifier = Modifier.padding(top = nameplateTopPadding),
            shape = RoundedCornerShape(16.dp),
            color = color,
            contentColor = Color.White
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(4.dp)
            )
        }

    }
}


@Preview
@Composable
private fun PersonPinPreview() {
    AnimationWithComposeTheme {
        PersonPin(
            color = Color.Blue,
            title = "User"
        )
    }
}
