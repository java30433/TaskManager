package bakuen.lib.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class ContentNode internal constructor(
    internal val content: @Composable () -> Unit,
    internal val key: Any? = null,
) {
    var enableSwipeBack by mutableStateOf(true)
}