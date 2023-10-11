package hu.autsoft.nytimes_gyulai_rainbowcake.ui.list

import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model.NewsListItem

sealed class ListViewState

object Loading : ListViewState()

data class NewsContent(
    var news: List<NewsListItem> = emptyList(),
    var isLoading: Boolean = false
) : ListViewState()