package fi.lauriari.recipe_app.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fi.lauriari.recipe_app.data.database.FavoriteRecipeDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        FavoriteRecipeDatabase::class.java,
        "favorite_recipe_database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: FavoriteRecipeDatabase) = database.favoriteRecipeDao()

}