package com.caminaapps.bookworm.features.bookshelf.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme

@ExperimentalMaterialApi
@Composable
fun BookListItem(
    title: String,
    author: String,
    imageUrl: String,
    isFinished: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
    ) {
        Box {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "book cover",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(width = 105.dp, height = 160.dp)
                )

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h5,
                    )
                    Text(
                        text = author,
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.Gray // change to theme
                    )
                }
            }

            if (isFinished) {
                Image(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.ic_baseline_check_circle_24),
                    contentDescription = "circle done",
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun BookItemPreview() {
    BookwormTheme {
        BookListItem(
            title = "Die 7 Wege zur Effektivität - Workbook",
            author = "Stephen R. Covey",
            imageUrl = "https://tinyurl.com/yv8t5scy",
            isFinished = true,
            onClick = {}
        )
    }
}
