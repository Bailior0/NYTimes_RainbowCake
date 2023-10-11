package hu.autsoft.nytimes_gyulai_rainbowcake.ui.list

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.autsoft.nytimes_gyulai_rainbowcake.domain.NewsInteractor
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor( private val newsInteractor: NewsInteractor) : RainbowCakeViewModel<ListViewState>(Loading) {

    fun load() = execute {
        viewState = NewsContent(news = newsInteractor.load())
    }

    fun onSwipeRefresh() = execute {
        viewState = NewsContent(isLoading = true)

        viewState = NewsContent(news = newsInteractor.refresh(), isLoading = false)
    }
}
