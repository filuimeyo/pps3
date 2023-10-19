package com.example.nikakudirko.pps3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nikakudirko.pps3.data.local.converters.DateConverter
import com.example.nikakudirko.pps3.data.local.model.Article

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
abstract class ArticleDatabase: RoomDatabase(){
    abstract  val  articleDao: ArticleDao
}