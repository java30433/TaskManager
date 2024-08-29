package wat.app.taskmanager.screens.about

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import bakuen.lib.navigator.Navigator
import bakuen.wear.components.SurfaceButton
import bakuen.wear.components.Text
import wat.app.taskmanager.BuildConfig
import wat.app.taskmanager.screens.hides.HideAppListScreen
import wat.app.taskmanager.utils.ScreenColumn

@Composable
fun AboutScreen() {
    ScreenColumn(center = true) {
        Text(text = "任务管理器 TaskManager")
        Text(text = "版本：${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
        Text(text = "By java30433")
        Text(text = "交流Q群: 1002991206")
        Text(text = "以 GPLv3 协议开源")
        SurfaceButton(modifier = Modifier.clickable { Navigator.forward { HideAppListScreen() } }) {
            Text(text = "所有被隐藏应用的列表")
        }
    }
}