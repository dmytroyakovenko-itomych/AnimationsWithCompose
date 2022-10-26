package com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables.circle

import com.dimyak.animationwithcompose.presentation.examples.animated_circle.Profile

/**
 * Possibility states of animated circle.
 */
enum class AnimatedCircleState {
    /**
     * The circle and the button should not be displayed at the screen.
     */
    INITIAL,

    /**
     * Button should be displayed at the bottom of the screen.
     */
    BUTTON_VISIBLE,

    /**
     * User clicked on the button. Circle should be displayed.
     */
    CIRCLE_VISIBLE
}

/**
 * Determine the [AnimatedCircleState] state.
 *
 * @param receiversNearby - Represents receivers nearby.
 * @param circleEnable - Represents [P2PCircle] visibility state. true - if the user pressed on button.
 */
fun mapToAnimatedCircleState(
    receiversNearby: List<Profile>,
    circleEnable: Boolean
): AnimatedCircleState = when {
    receiversNearby.isEmpty() -> AnimatedCircleState.INITIAL
    receiversNearby.isNotEmpty() && circleEnable.not() -> AnimatedCircleState.BUTTON_VISIBLE
    receiversNearby.isNotEmpty() && circleEnable -> AnimatedCircleState.CIRCLE_VISIBLE
    else -> throw Throwable("Unable to determine the state. [receiversNearby.size - ${receiversNearby.size}, circleEnable - $circleEnable]")
}

object AnimatedCircleConfig {
    const val ELEMENT_ANIMATION_DURATION = 250

    //after the user will be displayed start the circle scale animation
    const val CIRCLE_DELAY = ELEMENT_ANIMATION_DURATION

    //after circle scale animation start the shadow animation
    const val CIRCLE_SHADOW_DELAY = CIRCLE_DELAY + ELEMENT_ANIMATION_DURATION

    const val PERSON_PIN_DELAY = 100
}
