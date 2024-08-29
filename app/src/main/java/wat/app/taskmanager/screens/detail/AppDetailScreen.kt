package wat.app.taskmanager.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import bakuen.lib.navigator.Navigator
import bakuen.lib.protostore.setStore
import bakuen.wear.components.Space
import bakuen.wear.components.SurfaceButton
import bakuen.wear.components.Text
import wat.app.taskmanager.prefs.Settings
import wat.app.taskmanager.utils.Const
import wat.app.taskmanager.utils.ScreenColumn
import wat.app.taskmanager.screens.main.AppInfo

@Composable
fun AppDetailScreen(info: AppInfo) {
    ScreenColumn {
        Space(size = Const.scrPaddingTop)
        Image(bitmap = info.iconBitmap, contentDescription = null)
        Text(text = info.label)
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "应用包名：${info.packageName}")
        }
        SurfaceButton(modifier = Modifier.clickable {
            setStore<Settings> { it.copy(hidePackages = it.hidePackages.plus(info.packageName)) }
            Navigator.navigateBack()
        }) {
            Text(text = "在列表中隐藏此应用")
        }
    }
}