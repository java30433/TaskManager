package wat.app.taskmanager.screens.main

sealed class MainEvent {
    class Open(val packageName: String) : MainEvent()
    class Kill(val packageName: String) : MainEvent()
}