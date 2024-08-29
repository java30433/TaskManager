package bakuen.wear.components.wear

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import kotlinx.coroutines.launch

@OptIn(ExperimentalWearFoundationApi::class)
@Composable
fun Modifier.verticalRotaryScroll(state: ScrollState): Modifier {
    val coroutineScope = rememberCoroutineScope()
    return then(Modifier
        .verticalScroll(state)
        .onRotaryScrollEvent {
            coroutineScope.launch {
                state.scrollBy(it.verticalScrollPixels)
                state.animateScrollBy(0f)
            }
            true
        }
        .focusRequester(rememberActiveFocusRequester())
        .focusable())
}