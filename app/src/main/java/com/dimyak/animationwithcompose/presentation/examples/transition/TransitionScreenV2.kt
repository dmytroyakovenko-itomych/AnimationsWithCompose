package com.dimyak.animationwithcompose.presentation.examples.transition

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun TransitionScreenV2() {
    var useRed by remember { mutableStateOf(false) }
    var toState by remember { mutableStateOf(ComponentState.Released) }
    val modifier = Modifier.pointerInput(Unit) {
        detectTapGestures(
            onPress = {
                toState = ComponentState.Pressed
                tryAwaitRelease()
                toState = ComponentState.Released
            }
        )
    }
    val transitionData = updateTransitionData(componentState = toState, useRed = useRed)

    Column {
        Button(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally),
            onClick = { useRed = !useRed }
        ) {
            Text("Change Color")
        }
        Box(
            modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .size((100 * transitionData.scale).dp)
                .background(transitionData.color)
        )
    }
}

// Holds the animation values.
private class TransitionData(
    scale: State<Float>,
    color: State<Color>
) {
    val scale by scale
    val color by color
}

// Create a Transition and return its animation values.
@Composable
private fun updateTransitionData(componentState: ComponentState, useRed: Boolean): TransitionData {
    val transition = updateTransition(targetState = componentState, label = "component_transition")
    val scale = transition.animateFloat(
        // Defines a transition spec that uses the same low-stiffness spring for *all*
        // transitions of this float, no matter what the target is.
        transitionSpec = { spring(stiffness = 50f) }, label = ""
    ) { state ->
        // This code block declares a mapping from state to value.
        if (state == ComponentState.Pressed) 3f else 1f
    }

    val color = transition.animateColor(
        transitionSpec = {
            when {
                ComponentState.Pressed isTransitioningTo ComponentState.Released ->
                    // Uses spring for the transition going from pressed to released
                    spring(stiffness = 50f)
                else ->
                    // Uses tween for all the other transitions. (In this case there is
                    // only one other transition. i.e. released -> pressed.)
                    tween(durationMillis = 500)
            }
        }, label = ""
    ) { state ->
        when (state) {
            // Similar to the float animation, we need to declare the target values
            // for each state. In this code block we can access theme colors.
            ComponentState.Pressed -> MaterialTheme.colors.primary
            // We can also have the target value depend on other mutableStates,
            // such as `useRed` here. Whenever the target value changes, transition
            // will automatically animate to the new value even if it has already
            // arrived at its target state.
            ComponentState.Released -> if (useRed) Color.Red else MaterialTheme.colors.secondary
        }
    }

    return remember(transition) { TransitionData(scale, color) }
}
