package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchBarcode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme

@Composable
fun NoBookFoundView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.text_search_no_result),
            style = MaterialTheme.typography.h6
        )
        Spacer(Modifier.height(16.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_sentiment_dissatisfied_60),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true, heightDp = 160)
@Composable
fun NoBookFoundViewPreview() {
    BookwormTheme {
        NoBookFoundView(Modifier.padding(20.dp))
    }
}
