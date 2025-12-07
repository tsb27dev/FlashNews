package ipca.example.flashnews.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import org.json.JSONObject

@Entity
data class Article (
    var title : String? = null,
    var author : String? = null,
    var description : String? = null,
    @PrimaryKey
    var url : String = "",
    var urlToImage : String? = null,
    var publishedAt : String? = null
){
    companion object {
        fun fromJson( jsonObject: JSONObject) : Article {
            return Article(
                title = jsonObject.getString("title"),
                author = jsonObject.getString("author"),
                description = jsonObject.getString("description"),
                url = jsonObject.getString("url"),
                urlToImage = jsonObject.getString("urlToImage"),
                publishedAt = jsonObject.getString("publishedAt")
            )
        }
    }
}

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAll(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg article: Article)

    @Delete
    fun delete(article: Article)

    // Adicione esta função
    @Query("SELECT COUNT(*) FROM article WHERE url = :url")
    fun countByUrl(url: String): Int
}