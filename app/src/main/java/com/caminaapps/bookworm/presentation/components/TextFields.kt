package com.caminaapps.bookworm.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.presentation.theme.BookwormTheme


@Composable
fun IconTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    @StringRes label: Int,
    @DrawableRes imageResource: Int
) {
    Row(verticalAlignment = Alignment.Bottom, modifier = modifier) {
        Icon(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier.padding(bottom = 18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            label = { Text(stringResource(id = label)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IconTextField_Preview() {
    BookwormTheme {
        IconTextField(
            value = "", onValueChange = {},
            label = R.string.textfield_label_author,
            imageResource = R.drawable.ic_baseline_book_24
        )
    }
}