package wat.app.taskmanager.shizuku

import android.content.pm.PackageManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import rikka.shizuku.Shizuku

object ShizukuTools {
    private fun getShizukuState() = if (running) if (granted) ShizukuState.GRANTED else ShizukuState.NOT_GRANTED
    else ShizukuState.UNAVAILABLE
    var state by mutableStateOf(getShizukuState())
        private set
    init {
        Shizuku.addBinderReceivedListener {
            state = getShizukuState()
        }
        Shizuku.addBinderDeadListener {
            state = getShizukuState()
        }
    }
    val running get() = Shizuku.pingBinder()

    fun requireBinder(block: ()->Unit) {
        if (running && granted) block()
        else Shizuku.addBinderReceivedListener(block)
    }

    val granted get() = running && Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
    fun requestPermission(callback: () -> Unit = {}) {
        state = getShizukuState()
        if (!running) return
        if (!granted) {
            Shizuku.addRequestPermissionResultListener { _, _ ->
                callback()
            }
            Shizuku.requestPermission(30433)
        } else callback()
    }
}