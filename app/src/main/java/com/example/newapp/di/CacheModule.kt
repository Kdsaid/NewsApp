package com.example.newapp.di

import android.content.Context
import androidx.room.Room
import com.example.newapp.common.Constants
import com.example.newapp.data.dao.AppDao
import com.example.newapp.data.dao.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {


    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constants.DB_NAME)
            .build()

    @Provides
    fun providesAppDatabaseDao(appDatabase: AppDatabase): AppDao =
        appDatabase.appDao()


}

//@Provides
//@Singleton
//fun providesDatabase(@ApplicationContext context: Context): PostsDatabase =
//    Room.databaseBuilder(context, PostsDatabase::class.java, Constants.DB_NAME)
//        .build()
//
//@Provides
//fun providesPostDao(postDatabase: PostsDatabase): PostDao =
//    postDatabase.postsDao()
//
//
//}

//@Database(
//    entities = [PostsItem::class],
//    version = Constants.DB_VERSION,
//    exportSchema = false
//)
//abstract class PostsDatabase : RoomDatabase() {
//
//    abstract fun postsDao(): PostDao
//}

