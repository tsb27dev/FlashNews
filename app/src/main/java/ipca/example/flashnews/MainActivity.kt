package ipca.example.flashnews

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import kotlinx.coroutines.withContext
import java.net.URLDecoder

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()
            var title by remember { mutableStateOf("Futebol") }
            var isHome by remember { mutableStateOf(true) }
            var articleOnScreen by remember { mutableStateOf<Article?>(null) }
            val scope = rememberCoroutineScope()

            // 1. Estado para saber se o artigo é um favorito
            var isFavorite by remember { mutableStateOf(false) }
            val articleDao = remember { AppDatabase.getInstance(context)?.articleDao() }

            // 2. Verifica na BD se o artigo é favorito sempre que muda
            LaunchedEffect(articleOnScreen) {
                if (articleOnScreen == null || articleDao == null) {
                    isFavorite = false // Garante que o estado é falso se não houver artigo
                    return@LaunchedEffect
                }
                scope.launch(Dispatchers.IO) {
                    val count = articleDao.countByUrl(articleOnScreen!!.url)
                    withContext(Dispatchers.Main) {
                        isFavorite = count > 0
                    }
                }
            }

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
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                            },
                            actions = {
                                // Mostra o ícone apenas se estiver num detalhe de artigo
                                if (!isHome && articleOnScreen != null)
                                    IconButton(
                                        onClick = {
                                            scope.launch(Dispatchers.IO) {
                                                articleOnScreen?.let { article ->
                                                    // 3. Lógica para inserir ou apagar
                                                    if (isFavorite) {
                                                        articleDao?.delete(article)
                                                        Log.d("MainActivity", "Artigo removido")
                                                    } else {
                                                        articleDao?.insert(article)
                                                        Log.d("MainActivity", "Artigo guardado")
                                                    }
                                                    // Atualiza o estado na thread principal
                                                    withContext(Dispatchers.Main) {
                                                        isFavorite = !isFavorite
                                                    }
                                                }
                                            }
                                        }
                                    ) {
                                        Icon(
                                            // 4. Muda o ícone com base no estado
                                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                            contentDescription = "Favorite",
                                            tint = Color.Red // A cor mantém-se vermelha para ambos os ícones
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
                        startDestination = "futebol",
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable ("futebol"){
                            isHome = true
                            title = "Futebol"
                            ArticlesListView(navController = navController, source = "espn", query= "soccer"){
                                articleOnScreen = it
                            }
                        }
                        composable ("basquetebol"){
                            isHome = true
                            title = "Basquetebol"
                            ArticlesListView(navController = navController, source = "espn", query= "basketball"){
                                articleOnScreen = it
                            }
                        }
                        composable ("favoritos"){
                            isHome = true
                            title = "Favoritos"
                            ArticlesDatabaseView(
                                navController = navController,
                                onArticleClick = { article ->
                                    articleOnScreen = article
                                    navController.navigate("details/${article.title?.encodeURL()}/${article.url.encodeURL()}")
                                }
                            )
                        }
                        composable ("details/{title}/{url}"){
                            isHome = false
                            val encodedTitle = it.arguments?.getString("title") ?: ""
                            val encodedUrl = it.arguments?.getString("url") ?: ""
                            val decodedTitle = encodedTitle.decodeURL()
                            val url = encodedUrl.decodeURL()
                            title = if (decodedTitle.length > 30) "${decodedTitle.take(30)}..." else decodedTitle
                            ArticleDetailView(url = url)
                        }
                    }
                }
            }
        }
    }
}

fun String.encodeURL(): String {
    return java.net.URLEncoder.encode(this, "utf-8")
}

fun String.decodeURL(): String {
    return URLDecoder.decode(this, "utf-8")
}