package hu.autsoft.nytimes_gyulai_rainbowcake.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import co.zsmb.rainbowcake.navigation.navigator
import dagger.hilt.android.AndroidEntryPoint
import hu.autsoft.nytimes_gyulai_rainbowcake.R
import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model.NewsListItem
import hu.autsoft.nytimes_gyulai_rainbowcake.ui.detail.DetailsFragment

@AndroidEntryPoint
class ListFragment: RainbowCakeFragment<ListViewState, ListViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.load()
    }

    override fun render(viewState: ListViewState) {
        (view as ComposeView).setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                Toolbar(
                    title = stringResource(R.string.app_name)
                )
                when (viewState) {
                    is Loading -> { }
                    is NewsContent -> NewsList(viewState)
                }.exhaustive
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun NewsList(viewState: NewsContent) {
        val pullRefreshState = rememberPullRefreshState(viewState.isLoading, { viewModel.onSwipeRefresh() })

        Box(Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(viewState.news) { _, newsListItem ->
                    NewsItem(
                        newsListItem = newsListItem,
                        onClicked = ::onNewsSelected
                    )
                }
            }

            PullRefreshIndicator(viewState.isLoading, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }
        /*SwipeRefresh(
            state = rememberSwipeRefreshState(viewState.isLoading),
            onRefresh = {
                if(isConnected(requireContext())) {
                    viewModel.onSwipeRefresh()
                }
                else {
                    viewState.isLoading = false
                    Snackbar.make(requireView(), R.string.network_error, Snackbar.LENGTH_SHORT).show()
                }
            }
        ) { LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(viewState.news) { _, newsListItem ->
                    NewsItem(
                        newsListItem = newsListItem,
                        onClicked = ::onNewsSelected
                    )
                }
            }
        }*/
    }

    @Composable
    private fun Toolbar(
        title: String
    ) {
        TopAppBar(
            title = { Text(title) },
            backgroundColor = colorResource(id = R.color.toolbar_color),
            contentColor = colorResource(id = R.color.white),
            navigationIcon = {
                IconButton(
                    content = { Icon(painter = painterResource(id = R.drawable.ic_baseline_menu_24), contentDescription = null) },
                    onClick = {}
                )
            }
        )
    }

    private fun onNewsSelected(newsListItem: NewsListItem) {
        navigator?.add(DetailsFragment.newInstance(newsListItem))
    }
}