package bakuen.lib.navigator

import android.content.res.Resources
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class DialogNode(
    content: @Composable () -> Unit,
    key: Any? = null
) : ContentNode(content, key) {
    fun dismiss() {
        DialogMan.dialogs.remove(this)
    }
}

val LocalDialog = staticCompositionLocalOf<DialogNode> { error("") }

object DialogMan {
    internal val dialogs = mutableStateListOf<DialogNode>()

    fun push(key: Any? = null, content: @Composable () -> Unit) {
        dialogs.add(DialogNode(content = {
            val dialog = LocalDialog.current
            SwipeToDismiss(velocity = 16.dp, enabled = dialog.enableSwipeBack, onDismiss = { dialog.dismiss() }) {
                content()
            }
        }, key = key))
    }

    @Composable
    internal fun Dialogs() {
        dialogs.forEach { node ->
            CompositionLocalProvider(
                LocalDialog provides node,
                content = node.content
            )
        }
    }
}