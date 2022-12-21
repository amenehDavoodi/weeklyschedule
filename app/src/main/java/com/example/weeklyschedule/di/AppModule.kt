package com.example.weeklyschedule.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.weeklyschedule.data.local.database.WeeklyScheduleRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application)
    {

    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): WeeklyScheduleRoomDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            WeeklyScheduleRoomDataBase::class.java,
            "WeeklyScheduleDataBase.db"
        ).build()
    }
}