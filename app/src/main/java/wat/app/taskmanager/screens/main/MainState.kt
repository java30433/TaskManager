package wat.app.taskmanager.screens.main

import wat.app.taskmanager.shizuku.ShizukuState

data class MainState(
    val shizuku: ShizukuState,
    val runningAppProgress: List<AppInfo> = listOf()
)