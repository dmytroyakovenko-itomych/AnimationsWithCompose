package com.dimyak.animationwithcompose.presentation.examples.animated_circle.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dimyak.animationwithcompose.R
import com.dimyak.animationwithcompose.presentation.common.ui.theme.AnimationWithComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NearbyPeopleAnimatedButton(
    expand: Boolean,
    count: Int,
    onClick: () -> Unit,
    buttonHeight: Dp = 56.dp,
) {
    val buttonShape = RoundedCornerShape(buttonHeight / 2)
    val expandedColor = colorResource(id = R.color.orchid)
    val collapsedColor = colorResource(id = R.color.white)

    val color by animateColorAsState(
        targetValue = if (expand) expandedColor else collapsedColor
    )
    val contentColor by animateColorAsState(
        targetValue = if (expand) collapsedColor else expandedColor
    )
    val horizontalPadding by animateDpAsState(
        targetValue = if (expand) 20.dp else 0.dp
    )

    Surface(
        modifier = Modifier
            .height(buttonHeight)
            .widthIn(min = buttonHeight),
        shape = buttonShape,
        color = color,
        contentColor = contentColor,
        onClick = onClick,
        elevation = 6.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = horizontalPadding)
                .animateContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = expand) {
                Text(
                    text = LocalContext.current.resources.getQuantityString(
                        R.plurals.people_nearby_count_title, count, count
                    ).uppercase(),
                    modifier = Modifier.padding(end = 10.dp),
                    style = MaterialTheme.typography.button,
                    color = contentColor,
                )
            }
            Crossfade(targetState = expand) { state ->
                Icon(
                    painter = painterResource(id = if (state) R.drawable.ic_group_of_people else R.drawable.ic_close),
                    modifier = Modifier.size(22.dp),
                    contentDescription = null,
                    tint = LocalContentColor.current,
                )
            }
        }
    }
}

@Preview
@Composable
fun NearbyPeopleAnimatedButtonPreview() {
    AnimationWithComposeTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            NearbyPeopleAnimatedButton(expand = true, count = 2, onClick = {})
            NearbyPeopleAnimatedButton(expand = false, count = 2, onClick = {})
        }
    }
}
