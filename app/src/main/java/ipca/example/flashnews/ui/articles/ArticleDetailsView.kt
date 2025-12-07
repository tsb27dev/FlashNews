package ipca.example.flashnews.ui.articles

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import ipca.example.flashnews.ui.theme.FlashNewsTheme

@Composable
fun ArticleDetailView(
    modifier: Modifier = Modifier,
    url : String
){
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply{
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
            }
        },
        update = {
            it.loadUrl(url)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ArticleDetailViewPreview(){
    FlashNewsTheme {
        ArticleDetailView(
            url = "https://www.google.com"
        )
    }
}