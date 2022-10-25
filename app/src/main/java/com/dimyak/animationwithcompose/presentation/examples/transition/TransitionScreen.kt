package com.dimyak.animationwithcompose.presentation.examples.transition

import androidx.compose.runtime.Composable
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

enum class ComponentState { Pressed, Released }

@Composable
fun TransitionScreen() {
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
    val transition: Transition<ComponentState> = updateTransition(targetState = toState, label = "transition")
// Defines a float animation as a child animation the transition. The current animation value
// can be read from the returned State<Float>.
    val scale: Float by transition.animateFloat(
        // Defines a transition spec that uses the same low-stiffness spring for *all*
        // transitions of this float, no matter what the target is.
        transitionSpec = { spring(stiffness = 50f) }, label = ""
    ) { state ->
        // This code block declares a mapping from state to value.
        if (state == ComponentState.Pressed) 3f else 1f
    }

// Defines a color animation as a child animation of the transition.
    val color: Color by transition.animateColor(
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
                .size((100 * scale).dp)
                .background(color)
        )
    }
}
