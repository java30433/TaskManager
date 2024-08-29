package wat.app.taskmanager.screens.main

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap

data class AppInfo(
    val packageName: String,
    val label: String,
    val icon: Drawable
) {
    val iconBitmap by lazy { icon.toBitmap().asImageBitmap() }
}