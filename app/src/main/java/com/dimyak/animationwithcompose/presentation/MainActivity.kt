package com.dimyak.animationwithcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import com.dimyak.animationwithcompose.presentation.common.ui.theme.AnimationWithComposeTheme
import com.dimyak.animationwithcompose.presentation.examples.animatable.AnimatableScreen
import com.dimyak.animationwithcompose.presentation.examples.animateAsState.AnimateColorAsStateScreen
import com.dimyak.animationwithcompose.presentation.examples.animateContent.AnimateContentScreen
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.AnimatedCircleScreen
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.AnimatedCircleViewModel
import com.dimyak.animationwithcompose.presentation.examples.keyframes.KeyframesScreen
import com.dimyak.animationwithcompose.presentation.examples.transition.InfiniteTransitionScreen

class MainActivity : ComponentActivity() {
    private val animatedCircleViewModel by viewModels<AnimatedCircleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationWithComposeTheme {
                //AnimateContentScreen()
                AnimatedCircleScreen(animatedCircleViewModel)
            }
        }
    }
}
