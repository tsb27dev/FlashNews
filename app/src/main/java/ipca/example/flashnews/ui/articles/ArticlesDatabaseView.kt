package ipca.example.flashnews.ui.articles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ipca.example.flashnews.models.AppDatabase
import ipca.example.flashnews.models.Article
import ipca.example.flashnews.ui.theme.FlashNewsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch

@Composable
fun ArticlesDatabaseView(
    modifier: Modifier = Modifier,
    navController: NavController,
    onArticleClick: (Article) -> Unit
) {
    val context = LocalContext.current
    var articles by remember { mutableStateOf(emptyList<Article>()) }
    val articleDao = remember { AppDatabase.getInstance(context)?.articleDao() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        scope.launch(Dispatchers.IO) {
            val favoriteArticles = articleDao?.getAll() ?: emptyList()
            withContext(Dispatchers.Main) {
                articles = favoriteArticles
            }
        }
    }

    LazyColumn(modifier = modifier) {
        items(articles) { article ->
            ArticleViewCell(
                article = article,
                onItemClick = {
                    onArticleClick(article)
                }
            )
        }
    }
}

@Composable
fun ArticlesDatabaseViewContent(
    modifier: Modifier = Modifier,
    uiState: ArticlesDatabaseState,
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

@Preview(showBackground = false)
@Composable
fun ArticlesDatabaseViewPreview() {
    FlashNewsTheme {
        ArticlesDatabaseViewContent(
            navController = rememberNavController(),
            uiState = ArticlesDatabaseState(
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