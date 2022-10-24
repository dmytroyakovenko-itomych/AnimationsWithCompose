package com.dimyak.animationwithcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.dimyak.animationwithcompose.presentation.common.ui.theme.AnimationWithComposeTheme
import com.dimyak.animationwithcompose.presentation.examples.animateAsState.AnimateColorAsStateScreen
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.AnimatedCircleViewModel

class MainActivity : ComponentActivity() {
    private val animatedCircleViewModel by viewModels<AnimatedCircleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationWithComposeTheme {
                AnimateColorAsStateScreen()
                //AnimatedCircleScreen(animatedCircleViewModel)
            }
        }
    }
}
