package bakuen.wear.components

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity

@Composable
fun Float.pxToDp() = with(LocalDensity.current) {
    this@pxToDp.toDp()
}

object DensityData {
    val screenHeight = Resources.getSystem().displayMetrics.heightPixels
    val screenWidth = Resources.getSystem().displayMetrics.widthPixels
}