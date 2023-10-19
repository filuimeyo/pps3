package com.example.nikakudirko.pps3.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.nikakudirko.pps3.data.local.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles ORDER BY createdDate")
    fun getAllArticles(): Flow<List<Article>>

    @Query("SELECT * FROM articles WHERE id=:id ORDER BY createdDate")
    fun getArticleById(id: Long): Flow<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateArticle(article: Article)

    @Query("DELETE FROM articles WHERE id=:id")
    suspend fun delete(id: Long)

    //tut net rue i false tolko 1 i 0

    @Query("SELECT * FROM articles WHERE isBookMarked = 1 ORDER BY createdDate DESC")
    fun getBookMarkedArticles():Flow<List<Article>>


}