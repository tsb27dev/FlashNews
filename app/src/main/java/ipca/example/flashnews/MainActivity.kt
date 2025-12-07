package ipca.example.flashnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ipca.example.flashnews.models.AppDatabase
import ipca.example.flashnews.models.Article
import ipca.example.flashnews.ui.articles.ArticleDetailView
import ipca.example.flashnews.ui.articles.ArticlesDatabaseView
import ipca.example.flashnews.ui.articles.ArticlesListView
import ipca.example.flashnews.ui.components.MyBottomBar
import ipca.example.flashnews.ui.theme.FlashNewsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()
            var title by remember { mutableStateOf("ojogo") }
            var isHome by remember { mutableStateOf(true) }
            var articleOnScreen by remember { mutableStateOf<Article?>(null) }
            val scope = rememberCoroutineScope()
            FlashNewsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(title = {
                            Text(title)
                        },
                            navigationIcon = {
                                if (!isHome)
                                    IconButton(
                                        onClick = {
                                            navController.popBackStack()
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                            },
                            actions = {
                                if (!isHome)
                                    IconButton(
                                        onClick = {
                                            scope.launch(Dispatchers.IO) {
                                                articleOnScreen?.let {
                                                    AppDatabase.getInstance(context)?.articleDao()
                                                        ?.insert(it)
                                                }
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.FavoriteBorder,
                                            contentDescription = "Favorite"
                                        )
                                    }

                            }
                        )
                    },
                    bottomBar = {
                        MyBottomBar(navController = navController)
                    }

                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "techcrunch",
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable ("techcrunch"){
                            isHome = true
                            title = "TechCrunch"
                            ArticlesListView(navController = navController, source = "techcrunch"){
                                articleOnScreen = it
                            }
                        }
                        composable ("bloomberg"){
                            isHome = true
                            title = "Bloomberg"
                            ArticlesListView(navController = navController, source = "bloomberg"){
                                articleOnScreen = it
                            }
                        }
                        composable ("espn"){
                            isHome = true
                            title = "ESPN"
                            ArticlesListView(navController = navController, source = "espn"){
                                articleOnScreen = it
                            }
                        }
                        composable ("favorites"){
                            isHome = true
                            title = "Favorites"
                            ArticlesDatabaseView(navController = navController)
                        }
                        composable ("details/{title}/{url}"){
                            isHome = false
                            val titleNews = it.arguments?.getString("title")?:""
                            val url = it.arguments?.getString("url")?:""
                            title = if (titleNews.length > 30) "${titleNews.take(30)}..." else titleNews
                            ArticleDetailView(url = url)
                        }
                    }
                }
            }
        }
    }
}