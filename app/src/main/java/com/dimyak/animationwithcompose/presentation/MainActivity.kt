package com.dimyak.animationwithcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.dimyak.animationwithcompose.presentation.common.ui.theme.AnimationWithComposeTheme
import com.dimyak.animationwithcompose.presentation.examples.animateAsState.AnimateColorAsStateScreen
import com.dimyak.animationwithcompose.presentation.examples.animateVisibility.AnimateVisibilityAdvanced
import com.dimyak.animationwithcompose.presentation.examples.animateVisibility.AnimateVisibilityScreen
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.AnimatedCircleViewModel
import com.dimyak.animationwithcompose.presentation.examples.crossfade.CrossfadeScreen
import com.dimyak.animationwithcompose.presentation.examples.keyframes.KeyframesScreen
import com.dimyak.animationwithcompose.presentation.examples.transition.TransitionScreen
import com.dimyak.animationwithcompose.presentation.examples.transition.TransitionScreenV2

class MainActivity : ComponentActivity() {
    private val animatedCircleViewModel by viewModels<AnimatedCircleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationWithComposeTheme {
                TransitionScreen()
                //AnimatedCircleScreen(animatedCircleViewModel)
            }
        }
    }
}
