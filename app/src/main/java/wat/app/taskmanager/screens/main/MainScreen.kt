package wat.app.taskmanager.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bakuen.lib.navigator.Navigator
import bakuen.lib.protostore.rememberStore
import bakuen.wear.components.Space
import bakuen.wear.components.SwipeToDismiss
import bakuen.wear.components.Text
import wat.app.taskmanager.prefs.Settings
import wat.app.taskmanager.utils.ScreenColumn
import wat.app.taskmanager.shizuku.ShizukuState
import wat.app.taskmanager.screens.about.AboutScreen
import wat.app.taskmanager.screens.detail.AppDetailScreen
import wat.app.taskmanager.utils.Title

@Composable
fun MainScreen(model: MainViewModel) {
    LaunchedEffect(rememberStore<Settings>().value.hidePackages.size) {
        model.updateAppList()
    }
    MainScreenUI(state = model.state, dispatch = model::dispatch)
}

@Composable
private fun MainScreenUI(state: MainState, dispatch: (MainEvent)->Unit) {
    if (state.shizuku == ShizukuState.GRANTED) {
        ScreenColumn {
            Title {
                InfoText(onClick = {
                    Navigator.forward { AboutScreen() }
                })
            }
            if (state.runningAppProgress.isEmpty()) {
                Text(text = "当前没有运行中的应用")
            } else state.runningAppProgress.forEach {
                key(it.packageName) {
                    SwipeToDismiss(
                        velocity = 16.dp,
                        reverse = true,
                        onDismiss = { dispatch(MainEvent.Kill(it.packageName)) }) {
                        AppCell(
                            info = it,
                            onClick = { dispatch(MainEvent.Open(it.packageName)) },
                            onLongClick = { Navigator.forward { AppDetailScreen(info = it) } }
                        )
                    }
                }
            }
        }
    } else {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(),
            text = if (state.shizuku == ShizukuState.NOT_GRANTED) "请授予 Shizuku 权限！"
            else "Shizuku 服务不可用！"
        )
    }
}