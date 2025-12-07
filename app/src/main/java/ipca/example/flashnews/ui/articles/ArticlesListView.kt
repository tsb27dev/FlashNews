package ipca.example.flashnews.ui.articles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ipca.example.flashnews.models.Article
import ipca.example.flashnews.ui.theme.FlashNewsTheme
import java.net.URLEncoder

@Composable
fun ArticlesListView(
    modifier: Modifier = Modifier,
    navController: NavController,
    source : String,
    query : String,
    onItemClick: (Article) -> Unit = {}
) {

    val viewModel : ArticlesListViewModel = viewModel()
    val uiState by viewModel.uiState

    ArticlesListViewContent(
        modifier = modifier,
        uiState = uiState,
        navController = navController,
        onItemClick = onItemClick
    )

    LaunchedEffect(Unit) {
        viewModel.loadArticles(source, query)
    }
}

@Composable
fun ArticlesListViewContent(
    modifier: Modifier = Modifier,
    uiState: ArticlesListState,
    navController: NavController,
    onItemClick: (Article) -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else if (uiState.error != null) {
            Text(uiState.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(
                    items = uiState.articles
                ) { index, item ->
                    ArticleViewCell( article = item){
                        onItemClick(item)
                        navController
                            .navigate("details/${item.title}/${item.url?.encodeURL()}")
                    }
                }
            }
        }
    }
}

fun String.encodeURL() : String {
    return URLEncoder.encode(this, "UTF-8")
}

@Preview(showBackground = false)
@Composable
fun ArticlesListViewPreview() {
    FlashNewsTheme {
        ArticlesListViewContent(
            navController = rememberNavController(),
            uiState = ArticlesListState(
                isLoading = false,
                articles = listOf(
                    Article(
                        title = "Title 1",
                        description = "Description 1"
                    ),
                    Article(
                        title = "Title 2",
                        description = "Description 2"
                    )
                )
            )
        )
    }
}