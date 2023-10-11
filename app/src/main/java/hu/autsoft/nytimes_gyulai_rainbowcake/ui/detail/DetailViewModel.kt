package hu.autsoft.nytimes_gyulai_rainbowcake.ui.detail

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model.NewsListItem

class DetailViewModel : RainbowCakeViewModel<DetailsViewState>(Loading) {

    fun setNews(news: NewsListItem) {
        viewState = DetailsLoaded(news)
    }
}
