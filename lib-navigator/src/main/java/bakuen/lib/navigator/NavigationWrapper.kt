package bakuen.lib.navigator

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
internal fun NavigationWrapper(event: NavigationEvent?) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val maxOffset = constraints.maxWidth.toFloat()
        val swipeOffset = remember { Animatable(0f) }
        LaunchedEffect(event) {
            launch {
                when (event) {
                    is NavigationEvent.Backward -> {
                        swipeOffset.animateTo(maxOffset)
                        Navigator.nodeBackward()
                        swipeOffset.snapTo(0f)
                    }

                    is NavigationEvent.Forward -> {
                        Navigator.nodeForward(event.next)
                        swipeOffset.snapTo(maxOffset)
                        swipeOffset.animateTo(0f)
                    }

                    is NavigationEvent.Replace -> {
                        Navigator.nodeForward(event.target)
                        swipeOffset.snapTo(maxOffset)
                        swipeOffset.animateTo(0f)
                        screens.removeAt(screens.lastIndex - 1)
                    }

                    else -> {}
                }
            }
        }
        val scope = rememberCoroutineScope()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .run {
                    if (screens.last().enableSwipeBack) {
                        draggable(
                            orientation = Orientation.Horizontal,
                            state = rememberDraggableState {
                                scope.launch {
                                    swipeOffset.snapTo((swipeOffset.value + it).coerceAtLeast(0f))
                                }
                            },
                            onDragStopped = { velocity ->
                                val targetValue =
                                    if (FloatExponentialDecaySpec().getTargetValue(
                                            swipeOffset.value, velocity
                                        ) > maxOffset / 2
                                    ) maxOffset else 0f
                                swipeOffset.animateTo(targetValue)
                                if (targetValue != 0f) {
                                    Navigator.nodeBackward()
                                    swipeOffset.snapTo(0f)
                                }
                            }
                        )
                    } else this
                }
        ) {
            screens.forEachIndexed { index, screen ->
                stateHolder.SaveableStateProvider(key = screen.hashCode()) {
                    if (index >= screens.size - 2) {
                        Box(Modifier.run {
                            when (index) {
                                screens.size - 2 -> {
                                    this.offset {
                                        IntOffset(
                                            x = ((-maxOffset + swipeOffset.value * 1f) * 0.3f).toInt(),
                                            y = 0
                                        )
                                    }
                                }

                                screens.size - 1 -> {
                                    this.offset {
                                        IntOffset(
                                            x = swipeOffset.value.toInt(),
                                            y = 0
                                        )
                                    }
                                }

                                else -> this
                            }
                        }) {
                            CompositionLocalProvider(LocalScreen provides screen) {
                                screenRoot(screen.content)
                            }
                        }
                    }
                }
            }
        }
    }
}