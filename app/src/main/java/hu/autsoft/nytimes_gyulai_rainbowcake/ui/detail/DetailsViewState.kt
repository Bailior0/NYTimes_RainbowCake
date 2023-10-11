package hu.autsoft.nytimes_gyulai_rainbowcake.ui.detail

import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model.NewsListItem

sealed class DetailsViewState

object Loading : DetailsViewState()

data class DetailsLoaded(
    var news: NewsListItem? = null
) : DetailsViewState()