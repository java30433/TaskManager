package bakuen.lib.navigator

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import bakuen.lib.navigator.Navigator.ReduceRecompose
import bakuen.lib.navigator.Navigator.event

val LocalScreen = staticCompositionLocalOf<ContentNode> { error("") }
internal lateinit var stateHolder: SaveableStateHolder
internal lateinit var screenRoot: @Composable (currentScreen: @Composable () -> Unit) -> Unit
internal val screens = mutableStateListOf<ContentNode>()

object Navigator {

    internal var event = EventChannel<NavigationEvent>()

    @SuppressLint("Range")
    internal fun nodeBackward() {
        screens.removeLastOrNull()
    }

    internal fun nodeForward(node: ContentNode) {
        screens.add(node)
    }

    fun navigateBack(): Boolean {
        if (screens.size >= 2) {
            event.emit(NavigationEvent.Backward)
            return true
        } else return false
    }

    fun forward(key: Any? = null, screen: @Composable () -> Unit) {
        if (screens.last().key?.equals(key) == true) return
        event.emit(NavigationEvent.Forward(ContentNode(screen, key)))
    }

    fun replaceTop(screen: @Composable () -> Unit) {
        event.emit(NavigationEvent.Replace(ContentNode(screen)))
    }

    @Composable
    internal fun ReduceRecompose(content: @Composable () -> Unit) {
        content()
    }
}

@Composable
fun NavHost(
    initScreen: @Composable () -> Unit,
    screenWrapper: @Composable (currentScreen: @Composable () -> Unit) -> Unit
) {
    stateHolder = rememberSaveableStateHolder()
    screenRoot = screenWrapper
    var isInit by rememberSaveable { mutableStateOf(true) }
    if (isInit) {
        screens.clear()
        screens.add(ContentNode(initScreen))
        isInit = false
    }
    ReduceRecompose {
        BackHandler(enabled = screens.size > 1) {
            Navigator.navigateBack()
        }
        val ac = (LocalContext.current as? Activity)
        LaunchedEffect(screens.size) {
            if (screens.size == 0) {
                ac?.finish()
            }
        }
        if (screens.isNotEmpty()) {
            NavigationWrapper(
                event = event.consumeAsState().value
            )
        }
        DialogMan.Dialogs()
    }
}