package com.caminaapps.bookworm.presentation.screens.bookshelf.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.caminaapps.bookworm.presentation.theme.BookwormTheme


@ExperimentalMaterialApi
@Composable
fun BookItem(
    title: String,
    author: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onBackground)
    ) {
        Row() {
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = "book cover",
                modifier = Modifier
                    .size(width = 105.dp, height = 160.dp)
                    .background(MaterialTheme.colors.secondary)
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
    }
}


@ExperimentalMaterialApi
@Preview
@Composable
fun BookItemPreview() {
    BookwormTheme {
        BookItem(
            title = "Die 7 Wege zur Effektivität - Workbook",
            author = "Stephen R. Covey",
            imageUrl = "https://books.google.com/books/content?id=f94S3a1SzvoC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
            onClick = {}
        )
    }

}