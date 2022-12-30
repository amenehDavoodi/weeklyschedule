package com.example.weeklyschedule.di

import android.app.Application
import androidx.room.Room
import com.example.weeklyschedule.data.local.daos.WeeklyScheduleDao
import com.example.weeklyschedule.data.local.database.WeeklyScheduleRoomDataBase
import com.example.weeklyschedule.data.local.database.WeeklyScheduleRoomDataBase.Companion.DATABASE_NAME
import com.example.weeklyschedule.data.repository.WeeklyScheduleRepositoryImp
import com.example.weeklyschedule.domain.WeeklyScheduleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class WeeklyDaoSource
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


//    @Provides
//    fun provideWeeklyDao(database: WeeklyScheduleRoomDataBase) = Unit

    @Provides
    @Singleton
    fun provideDatabase( appContext: Application): WeeklyScheduleRoomDataBase {
        return Room.databaseBuilder(
            appContext,
            WeeklyScheduleRoomDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun weeklyScheduleRepository(db:WeeklyScheduleRoomDataBase
    ): WeeklyScheduleRepository {
        return WeeklyScheduleRepositoryImp(db.weeklyScheduleDao)
    }

}

//@Module
//@InstallIn(SingletonComponent::class)
//object RepositoryModule {
//
//    @Provides
//    @Singleton
//    fun weeklyScheduleRepository(
//       @WeeklyDaoSource weeklyDao:WeeklyScheduleDao
//    ): WeeklyScheduleRepositoryImp {
//        return WeeklyScheduleRepositoryImp(weeklyDao)
//    }
//}