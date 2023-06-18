package com.caminaapps.bookworm.features.searchBookOnline.presentation.searchTitle

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.caminaapps.bookworm.R
import com.caminaapps.bookworm.core.model.Book
import com.caminaapps.bookworm.core.ui.theme.BookwormTheme
import com.caminaapps.bookworm.util.forwardingPainter
import com.caminaapps.bookworm.util.previewParameterProvider.BookPreviewParameterProvider

@Composable
fun SearchResults(
    searchResults: List<Book>,
    onResultClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = pluralStringResource(
                R.plurals.search_count,
                searchResults.size,
                searchResults.size
            ),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
        )
        LazyColumn {
            itemsIndexed(searchResults) { index, book ->
                SearchResultItem(
                    book = book,
                    showDivider = index != 0,
                    modifier = Modifier.clickable { onResultClick(book) }
                )
            }
        }
    }
}

// TODO simplify layout with final design
@Composable
private fun SearchResultItem(
    book: Book,
    showDivider: Boolean,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        val (divider, image, name) = createRefs()
        val (tag, priceSpacer, price) = createRefs()
        val add = createRef()
        createVerticalChain(name, tag, priceSpacer, price, chainStyle = ChainStyle.Packed)
        if (showDivider) {
            Divider(
                Modifier.constrainAs(divider) {
                    linkTo(start = parent.start, end = parent.end)
                    top.linkTo(parent.top)
                }
            )
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(book.coverUrl)
                .crossfade(true)
                .build(),
            placeholder = forwardingPainter(
                painter = painterResource(id = R.drawable.ic_baseline_book_24),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
            ),
            contentDescription = book.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.constrainAs(image) {
                linkTo(
                    top = parent.top,
                    topMargin = 16.dp,
                    bottom = parent.bottom,
                    bottomMargin = 16.dp,
                )
                start.linkTo(parent.start)
            }
        )

        Text(
            text = book.title,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.constrainAs(name) {
                start.linkTo(image.end, margin = 16.dp)
                end.linkTo(add.start, margin = 16.dp)
            }
        )
        Text(
            text = "subtitle",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.constrainAs(tag) {
                start.linkTo(image.end, margin = 16.dp)
                end.linkTo(add.start, goneMargin = 16.dp)
            }
        )
        Spacer(
            Modifier
                .height(8.dp)
                .constrainAs(priceSpacer) {
                    top.linkTo(tag.bottom)
                    bottom.linkTo(price.top)
                }
        )
        Text(
            text = book.author,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.constrainAs(price) {
                start.linkTo(image.end, margin = 16.dp)
                end.linkTo(add.start, margin = 16.dp)
            }
        )
    }
}

@Composable
fun NoResults(
    query: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .wrapContentSize()
            .padding(24.dp)
    ) {
        Icon(
            painterResource(R.drawable.ic_baseline_search_42), // empty search
            contentDescription = null
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.search_no_matches, query),
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
private fun SearchResultPreview(
    @PreviewParameter(BookPreviewParameterProvider::class, limit = 1) book: Book,
) {
    BookwormTheme {
        Surface {
            SearchResultItem(
                book = book,
                showDivider = false
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 160)
@Composable
fun NoResultPreview() {
    BookwormTheme {
        NoResults(query = "foobar")
    }
}
