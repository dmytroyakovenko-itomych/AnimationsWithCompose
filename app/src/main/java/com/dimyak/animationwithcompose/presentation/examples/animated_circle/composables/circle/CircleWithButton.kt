package com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables.circle

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.createChildTransition
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.Profile
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables.NearbyPeopleAnimatedButton

@OptIn(ExperimentalTransitionApi::class, ExperimentalAnimationApi::class)
@Composable
fun CircleWithButton(
    user: Profile,
    showCircle: Boolean,
    nearbyPeople: List<Profile>,
    showCircleClicked: () -> Unit,
    hideCircleClicked: () -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        var circleContentCache by remember {
            mutableStateOf(emptyList<Profile>())
        }
        var circleState by remember {
            mutableStateOf(mapToAnimatedCircleState(nearbyPeople, showCircle))
        }
        val circleTransition = updateTransition(
            targetState = circleState, "circleTransition"
        )

        LaunchedEffect(nearbyPeople, showCircle) {
            if (nearbyPeople.isNotEmpty()) {
                circleContentCache = nearbyPeople
            }
            circleState = mapToAnimatedCircleState(nearbyPeople, showCircle)
        }

        AnimatedCircle(
            visibilityTransition = circleTransition.createChildTransition { state ->
                state == AnimatedCircleState.CIRCLE_VISIBLE
            },
            user = user,
            peopleNearby = circleContentCache,
            dismissClicked = hideCircleClicked
        )

        circleTransition.AnimatedVisibility(
            visible = { state ->
                state.isButtonVisible
            },
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            val expand = circleTransition.targetState.isButtonInExpandState
            NearbyPeopleAnimatedButton(
                expand = expand,
                count = nearbyPeople.size,
                onClick = {
                    if (expand) {
                        showCircleClicked()
                    } else {
                        hideCircleClicked()
                    }
                }
            )
        }
    }
}

/**
 * Map [AnimatedCircleState] into [NearbyPeopleAnimatedButton]'s visibility.
 */
private val AnimatedCircleState.isButtonVisible
    get() = this == AnimatedCircleState.BUTTON_VISIBLE || this == AnimatedCircleState.CIRCLE_VISIBLE

/**
 * Map [AnimatedCircleState] to [NearbyPeopleAnimatedButton]'s expand mode.
 *
 * In expanding mode, the [NearbyPeopleAnimatedButton] will display the count of the nearby people.
 * In collapse mode, the [NearbyPeopleAnimatedButton] will display the close circle icon.
 */
private val AnimatedCircleState.isButtonInExpandState
    get() = this == AnimatedCircleState.BUTTON_VISIBLE || this == AnimatedCircleState.INITIAL
