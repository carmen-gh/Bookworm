package com.caminaapps.bookworm.core.ui.theme

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BookwormTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Preview(name = "Theme test", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ThemeTest() {
    BookwormTheme {
        Surface {
            Column(
                Modifier.padding(48.dp),
            ) {

                Button(onClick = {}) {
                    Text("Button1")
                }
                Spacer(Modifier.height(16.dp))
                Card {
                    Column(
                        Modifier.padding(16.dp)
                    ) {
                        Text("This is a card")
                    }
                }
            }
        }
    }
}
