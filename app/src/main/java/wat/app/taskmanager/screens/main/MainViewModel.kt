package wat.app.taskmanager.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import bakuen.lib.protostore.getStore
import bakuen.wear.components.SimpleViewModel
import wat.app.taskmanager.utils.Const
import wat.app.taskmanager.utils.PackageUtils.killApp
import wat.app.taskmanager.utils.PackageUtils.launchApp
import wat.app.taskmanager.shizuku.ShizukuAPI
import wat.app.taskmanager.shizuku.ShizukuTools
import wat.app.taskmanager.appContext
import wat.app.taskmanager.prefs.Settings

class MainViewModel : SimpleViewModel<MainEvent>() {

    var state by mutableStateOf(MainState(shizuku = ShizukuTools.state))
        private set

    fun updateAppList() {
        ShizukuTools.requireBinder {
            val pm = appContext.packageManager
            state = state.copy(
                runningAppProgress = ShizukuAPI.ACTIVITY_MANAGER.runningAppProcesses
                    .filter {
                        !(Const.ignoreApps.contains(it.processName) ||
                                killedApps.contains(it.processName) ||
                                getStore<Settings>().hidePackages.contains(it.processName))
                    }
                    .mapNotNull {
                        runCatching {
                            val info = pm.getApplicationInfo(it.processName, 0)
                            AppInfo(
                                packageName = it.processName,
                                icon = info.loadIcon(pm),
                                label = info.loadLabel(pm).toString()
                            )
                        }.getOrNull()
                    }
            )
        }
    }

    private val killedApps = mutableSetOf<String>()
    override fun dispatch(event: MainEvent) {
        when (event) {
            is MainEvent.Open -> appContext.launchApp(event.packageName)
            is MainEvent.Kill -> {
                appContext.killApp(event.packageName)
                killedApps.add(event.packageName)
                updateAppList()
            }
        }
    }
}