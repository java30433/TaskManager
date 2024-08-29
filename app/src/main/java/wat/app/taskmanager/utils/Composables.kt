package wat.app.taskmanager.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bakuen.lib.navigator.Navigator
import bakuen.wear.components.Space
import bakuen.wear.components.calculate
import bakuen.wear.components.condition
import bakuen.wear.components.wear.verticalRotaryScroll
import wat.app.taskmanager.screens.about.AboutScreen
import wat.app.taskmanager.screens.main.InfoText

@Composable
inline fun ScreenColumn(center: Boolean = false, bg: Color? = null, content: @Composable ColumnScope.()->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .calculate { if (bg!=null) Modifier.background(color = bg) else null }
            .padding(horizontal = Const.scrPaddingHor)
            .condition(center, Modifier.wrapContentSize(Alignment.Center))
            .verticalRotaryScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Const.colArrangement,
        content = content
    )
}

@Composable
inline fun ColumnScope.Title(content: @Composable ColumnScope.()->Unit) {
    Space(size = Const.scrPaddingTop)
    content()
    Space(size = 8.dp)
}