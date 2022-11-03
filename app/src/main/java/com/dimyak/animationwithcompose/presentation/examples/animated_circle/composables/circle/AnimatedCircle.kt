package com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables.circle

import androidx.compose.animation.*
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dimyak.animationwithcompose.R
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.Profile
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables.PersonPin
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables.circle.AnimatedCircleConfig.CIRCLE_DELAY
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables.circle.AnimatedCircleConfig.CIRCLE_SHADOW_DELAY
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables.circle.AnimatedCircleConfig.ELEMENT_ANIMATION_DURATION
import com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables.circle.AnimatedCircleConfig.PERSON_PIN_DELAY
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

const val MAX_VISIBLE_PEOPLE = 4
const val MAX_CIRCLE_POINTS = 1 + MAX_VISIBLE_PEOPLE
const val POINT_ANGLE_STEP = 360 / MAX_CIRCLE_POINTS

val pointSize = 80.dp
val pointsCircleRadius = 130.dp//radius of the circle used to center the points
val circleDiameter = 290.dp

@Composable
fun AnimatedCircle(
    visibilityTransition: Transition<Boolean>,
    user: Profile,
    peopleNearby: List<Profile>,
    dismissClicked: () -> Unit = {},
) {

    val circleScale = visibilityTransition.animateFloat(
        label = "circleScale",
        transitionSpec = { tween(CIRCLE_DELAY, ELEMENT_ANIMATION_DURATION) }
    ) { visible ->
        if (visible) 1f else 0f
    }

    val shadowColor = visibilityTransition.animateColor(
        label = "shadowColor",
        transitionSpec = { tween(CIRCLE_SHADOW_DELAY, ELEMENT_ANIMATION_DURATION) }
    ) { visible ->
        if (visible) colorResource(id = R.color.basic_black_15) else Color.Transparent
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(shadowColor.value)
            .let { modifier ->
                if (visibilityTransition.targetState) {
                    modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { dismissClicked() }
                } else {
                    modifier
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_p2p_circle_bg),
            contentDescription = null,
            modifier = Modifier
                .size(circleDiameter)
                .align(Alignment.Center)
                .scale(circleScale.value)
        )

        UserPin(
            visibilityTransition = visibilityTransition,
            user = user
        )

        NearbyPeoplePins(
            visibilityTransition = visibilityTransition,
            peopleNearby = peopleNearby
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun BoxScope.UserPin(
    visibilityTransition: Transition<Boolean>,
    user: Profile
) {
    val userAnimSpec = tween<Float>(durationMillis = ELEMENT_ANIMATION_DURATION)
    visibilityTransition.AnimatedVisibility(
        visible = { it },
        modifier = Modifier.align(Alignment.Center),
        enter = scaleIn(userAnimSpec),
        exit = scaleOut(userAnimSpec)
    ) {
        PersonPin(
            color = user.color,
            title = "You",
            circleSize = 34.dp
        )
    }
}
//TODO: Instead of the delay approach, use Animatable!!
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun NearbyPeoplePins(
    visibilityTransition: Transition<Boolean>,
    peopleNearby: List<Profile>
) {
    val visiblePeople = peopleNearby.take(MAX_VISIBLE_PEOPLE)

    val circlePoints = if (visiblePeople.size < peopleNearby.size) {
        MAX_CIRCLE_POINTS
    } else {
        visiblePeople.size
    }

    for (index in 0..circlePoints.dec()) {
        val personDefaultDelay = PERSON_PIN_DELAY * index
        val enterAnimSpec = tween<Float>(delayMillis = CIRCLE_SHADOW_DELAY + personDefaultDelay)
        val exitAnimSpec = tween<Float>(delayMillis = personDefaultDelay / 2)

        visibilityTransition.AnimatedVisibility(
            visible = { it },
            enter = scaleIn(enterAnimSpec) + fadeIn(enterAnimSpec),
            exit = scaleOut(exitAnimSpec) + fadeOut(exitAnimSpec),
            modifier = Modifier.placeOnCircle(index * POINT_ANGLE_STEP)
        ) {
            if (index < visiblePeople.size) {
                PersonPin(
                    color = visiblePeople[index].color,
                    title = visiblePeople[index].name,
                    circleSize = pointSize,
                )
            } else {
                PersonPin(
                    color = colorResource(id = R.color.orchid),
                    title = "See more ${peopleNearby.size - MAX_VISIBLE_PEOPLE}",
                    circleSize = pointSize,
                )
            }
        }
    }
}

/**
 * Place the content element to a specific position on the circle.
 *
 * @param angle - Angle of a point on a circle.
 */
private fun Modifier.placeOnCircle(angle: Int, circleRadius: Dp = pointsCircleRadius) =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val centerX = constraints.maxWidth / 2
        val centerY = constraints.maxHeight / 2
        layout(constraints.maxWidth, constraints.maxHeight) {
            val realAngle = ((angle) + 270) * Math.PI / 180f // starts at 12 o'clock
            val placeableCenterX = placeable.width / 2
            val placeableCenterY = placeable.height / 2
            val x = centerX + (circleRadius.toPx() * cos(realAngle)).roundToInt()
            val y = centerY + (circleRadius.toPx() * sin(realAngle)).roundToInt()
            placeable.place(x - placeableCenterX, y - placeableCenterY, 0f)
        }
    }
