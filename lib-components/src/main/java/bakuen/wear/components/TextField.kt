package bakuen.wear.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    textStyle: TextStyle = Theme.typo.body,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    hint: String = ""
) {
    BasicTextField(
        modifier = modifier,
        state = state,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        lineLimits = lineLimits,
        decorator = { innerInputField ->
            if (state.text.isEmpty()) Text(
                text = hint,
                color = Theme.color.onSurfaceVariant,
                style = textStyle,
                lineLimits = TextFieldLineLimits.SingleLine
            )
            innerInputField()
        }
    )
}