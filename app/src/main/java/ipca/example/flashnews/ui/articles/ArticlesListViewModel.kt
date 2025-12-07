package ipca.example.flashnews.ui.articles

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ipca.example.flashnews.models.Article
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

data class ArticlesListState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ArticlesListViewModel : ViewModel() {

    var uiState = mutableStateOf(ArticlesListState())
        private set

    fun loadArticles(source : String, query: String) {
        uiState.value = uiState.value.copy(isLoading = true)
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://newsapi.org/v2/everything?sources=${source}&q=${query}&apiKey=3744b32c38574ae9b5859f11e650a449")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful){
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            error = "Unexpected code $response"
                        )
                    }

                    val result = response.body!!.string()
                    val jsonResult = JSONObject(result)
                    if (jsonResult.getString("status") == "ok") {
                        val jsonArticles = jsonResult.getJSONArray("articles")

                        val articlesList = arrayListOf<Article>()
                        for( i in 0..<jsonArticles.length()){
                            val jsonArticle = jsonArticles.get(i) as JSONObject
                            val article = Article.fromJson(jsonArticle)
                            articlesList.add(article)
                        }
                        uiState.value = uiState.value.copy(
                            articles = articlesList,
                            isLoading = false,
                            error = null
                        )
                    }

                }
            }
        })
    }
}