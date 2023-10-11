package hu.autsoft.nytimes_gyulai_rainbowcake.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import co.zsmb.rainbowcake.navigation.extensions.applyArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import hu.autsoft.nytimes_gyulai_rainbowcake.R
import hu.autsoft.nytimes_gyulai_rainbowcake.data.disk.model.NewsListItem
import hu.autsoft.nytimes_gyulai_rainbowcake.data.network.ConnectivityChecker.isConnected
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.zsmb.rainbowcake.navigation.navigator
import com.skydoves.landscapist.glide.GlideImage

@AndroidEntryPoint
class DetailsFragment : RainbowCakeFragment<DetailsViewState, DetailViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()

    companion object {
        private const val EXTRA_NEWS = "NEWS"


        fun newInstance(newsItem: NewsListItem): DetailsFragment {
            return DetailsFragment().applyArgs {
                putParcelable(EXTRA_NEWS, newsItem)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel.setNews(arguments?.getParcelable(EXTRA_NEWS)!!)

        return ComposeView(requireContext())
    }

    override fun render(viewState: DetailsViewState) {
        (view as ComposeView).setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                Toolbar(
                    title = stringResource(R.string.app_name),
                    onIconClick = { navigator?.pop() }
                )
                when (viewState) {
                    is Loading -> {}
                    is DetailsLoaded -> ShowNews(viewState)
                }.exhaustive
            }
        }
    }

    @Composable
    private fun ShowNews(viewState: DetailsLoaded) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val news = viewState.news
            Box(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .size(120.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            ) {
                if (news?.iconUrl != null) {
                    GlideImage(imageModel = { news.iconUrl })
                } else {
                    Image(painter = painterResource(id = R.drawable.baseline_circle_24), contentDescription = null, modifier = Modifier.fillMaxSize())
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                if(news?.title != null)
                    Text(news.title, color = colorResource(R.color.title_color), fontSize = 20.sp)
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                if(news?.byline != null)
                    Text(news.byline, color = colorResource(R.color.secondary_text), fontSize = 15.sp)
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                if(news?.publishedDate != null)
                    Text(news.publishedDate, color = colorResource(R.color.secondary_text), fontSize = 15.sp)
            }
            Spacer(Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)
            ) {
                Button(
                    content = { Text(getString(R.string.full_article), color = colorResource(R.color.button_text_color)) },
                    onClick = {
                        if(isConnected(requireContext())) {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse(news?.url)
                            startActivity(intent)
                        }
                        else
                            Snackbar.make(requireView(), R.string.network_error, Snackbar.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.button_color))
                )
            }
        }
    }

    @Composable
    private fun Toolbar(
        title: String,
        onIconClick: () -> Unit = {}
    ) {
        TopAppBar(
            title = { Text(title) },
            backgroundColor = colorResource(id = R.color.toolbar_color),
            contentColor = colorResource(id = R.color.white),
            navigationIcon = {
                IconButton(
                    content = { Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = null) },
                    onClick = onIconClick
                )
            }
        )
    }
}