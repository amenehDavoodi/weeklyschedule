package com.example.weeklyschedule.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.weeklyschedule.data.local.database.WeeklyScheduleRoomDataBase
import com.example.weeklyschedule.data.local.database.WeeklyScheduleRoomDataBase.Companion.DATABASE_NAME
import com.example.weeklyschedule.data.repository.HomeRepositoryImp
import com.example.weeklyschedule.data.repository.WeeklyScheduleRepositoryImp
import com.example.weeklyschedule.domain.HomeScheduleRepository
import com.example.weeklyschedule.domain.WeeklyScheduleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDatabase( appContext: Application): WeeklyScheduleRoomDataBase {
        return Room.databaseBuilder(
            appContext,
            WeeklyScheduleRoomDataBase::class.java,
            DATABASE_NAME
        ).addMigrations(MIGRATION_1)
            .build()
    }

    @Provides
    @Singleton
    fun weeklyScheduleRepository(db:WeeklyScheduleRoomDataBase
    ): WeeklyScheduleRepository {
        return WeeklyScheduleRepositoryImp(db.weeklyScheduleDao)
    }
    @Provides
    @Singleton
    fun homeScheduleRepository(db:WeeklyScheduleRoomDataBase
    ): HomeScheduleRepository {
        return HomeRepositoryImp(db.weeklyScheduleDao)
    }

    val MIGRATION_1 = object : Migration(1, 1) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("DROP TABLE IF EXISTS `WeeklySchedule`")
        }
    }

}

