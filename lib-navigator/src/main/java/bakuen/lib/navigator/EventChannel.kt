package bakuen.lib.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking

class EventChannel<T> {
    private val channel = Channel<T>(capacity = Channel.BUFFERED)
    private val flow = channel.receiveAsFlow()
    fun emit(value: T) {
        runBlocking {
            channel.send(value)
        }
    }
    @Composable
    fun consumeAsState() = flow.collectAsState(initial = null)
}