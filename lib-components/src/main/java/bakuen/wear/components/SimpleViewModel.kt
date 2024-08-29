package bakuen.wear.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.MainScope

@Composable
fun <VM : SimpleViewModel<*>> rememberViewModel(factory: ()->VM) = remember(factory)

abstract class SimpleViewModel<T> {
    val scope = MainScope()
    abstract fun dispatch(event: T)
}