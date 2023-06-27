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
        ).addMigrations(MIGRATION_2)
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

    private val MIGRATION_1 = object : Migration(1, 1) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("DROP TABLE IF EXISTS `WeeklySchedule`")
        }
    }
    private val MIGRATION_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE weeklyschedule ADD COLUMN breakId INTEGER ")
            database.execSQL("ALTER TABLE Courses ADD COLUMN coursePic INTEGER DEFAULT 0")

        }
    }
//    private val MIGRATION_3 = object : Migration(2, 3) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("ALTER TABLE Courses ADD COLUMN coursePic INTEGER DEFAULT 0")
//        }
//    }

}

