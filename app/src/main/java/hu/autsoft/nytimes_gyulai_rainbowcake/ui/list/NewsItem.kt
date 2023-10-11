package hu.autsoft.nytimes_gyulai_rainbowcake.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model.NewsListItem

@Composable
fun NewsItem(
    newsListItem: NewsListItem,
    onClicked: (NewsListItem) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .clickable(onClick = { onClicked(newsListItem) })
            .height(IntrinsicSize.Min)
            .padding(all = 16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight(),
        ) {
            ItemImage(imageUrl = newsListItem.iconUrl)
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.weight(1f),
        ) {
            Text(
                newsListItem.title,
                maxLines = 1,
                softWrap = true,
                color = colorResource(hu.autsoft.nytimes_gyulai_rainbowcake.R.color.title_color)
            )
            Text(
                newsListItem.byline,
                maxLines = 1,
                softWrap = true,
                color = colorResource(hu.autsoft.nytimes_gyulai_rainbowcake.R.color.secondary_text)
            )
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    tint = colorResource(hu.autsoft.nytimes_gyulai_rainbowcake.R.color.secondary_text),
                    modifier = Modifier.requiredSize(24.dp),
                    painter = painterResource(id = hu.autsoft.nytimes_gyulai_rainbowcake.R.drawable.baseline_event_24),
                    contentDescription = null
                )
                Text(
                    text = newsListItem.publishedDate,
                    color = colorResource(hu.autsoft.nytimes_gyulai_rainbowcake.R.color.secondary_text)
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .requiredWidth(42.dp)
                .fillMaxHeight(),
        ) {
            Icon(
                tint = colorResource(hu.autsoft.nytimes_gyulai_rainbowcake.R.color.secondary_text),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .requiredSize(36.dp),
                painter = painterResource(id = hu.autsoft.nytimes_gyulai_rainbowcake.R.drawable.baseline_navigate_next_24),
                contentDescription = null
            )
        }
    }
}

@Composable
fun ItemImage(imageUrl: String?) {
    Box(
        modifier = Modifier
            .background(
                color = Color.Transparent,
                shape = CircleShape,
            )
            .requiredSize(48.dp),
    ) {
        if (imageUrl != null) {
            GlideImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                imageModel = {imageUrl}
            )
        } else {
            Image(
                painter = painterResource(id = hu.autsoft.nytimes_gyulai_rainbowcake.R.drawable.baseline_circle_24),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
fun ArticleItemPreview() {
    NewsItem(
        newsListItem = NewsListItem(
            newsId =  0,
            url = "",
            title = "Test article written only for Jetpack Compose",
            byline = "By the Android Chapter",
            publishedDate = "2020-09-21",
            iconUrl = null
        ),
        onClicked = {}
    )
}

