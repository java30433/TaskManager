package bakuen.wear.components

import android.content.res.Resources
import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset

private enum class DragAnchors {
    Normal,
    Dismiss,
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeToDismiss(
    modifier: Modifier = Modifier,
    velocity: Dp,
    onDismiss: ()->Unit,
    reverse: Boolean = false,
    content: @Composable BoxScope.()->Unit
) {
    val reverseSymbol = if (reverse) -1 else 1
    val maxOffset = reverseSymbol * Resources.getSystem().displayMetrics.widthPixels.toFloat()
    val density = LocalDensity.current
    val decay = rememberSplineBasedDecay<Float>()
    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Normal,
            positionalThreshold = { totalDistance ->
                totalDistance * 0.5f
            },
            velocityThreshold = {
                with(density) {
                    velocity.toPx()
                }
            },
            snapAnimationSpec = tween(),
            decayAnimationSpec = decay,
            anchors = DraggableAnchors {
                DragAnchors.Normal at 0f
                DragAnchors.Dismiss at maxOffset
            }
        )
    }
    Box(
        modifier = modifier
            .offset {
                val x = state.requireOffset()
                if (x == maxOffset) onDismiss()
                IntOffset(x = x.toInt(), y = 0)
            }
            .anchoredDraggable(
                state = state,
                orientation = Orientation.Horizontal,
            ),
        content = content
    )
}