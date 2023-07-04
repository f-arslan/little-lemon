package com.example.littlelemon.core.model

import android.content.Context
import androidx.room.Room
import com.example.littlelemon.util.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            LittleLemonDatabase::class.java,
            DB_NAME
        )

    @Singleton
    @Provides
    fun provideLittleLemonDao(db: LittleLemonDatabase) = db.LittleLemonDao()
}