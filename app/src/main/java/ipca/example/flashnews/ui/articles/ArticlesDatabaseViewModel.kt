package ipca.example.flashnews.ui.articles

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ipca.example.flashnews.models.AppDatabase
import ipca.example.flashnews.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class ArticlesDatabaseState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ArticlesDatabaseViewModel : ViewModel() {
    var uiState = mutableStateOf(ArticlesDatabaseState())
        private set

    fun loadArticles(context : Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val articles = AppDatabase.getInstance(context)?.articleDao()?.getAll()
            viewModelScope.launch(Dispatchers.Main) {
                uiState.value = uiState.value.copy(
                    articles = articles ?: emptyList()
                )
            }
        }
    }
}