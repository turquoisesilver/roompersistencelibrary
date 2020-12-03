package com.phiruse.roomapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.phiruse.roomapplication.data.dao.StudentDao
import com.phiruse.roomapplication.data.dao.TeacherDao
import com.phiruse.roomapplication.data.entity.StudentEntity
import com.phiruse.roomapplication.data.entity.StudentsTeachersEntity
import com.phiruse.roomapplication.data.entity.TeacherEntity
import com.phiruse.roomapplication.utils.ioThread

private const val DATABASE_NAME = "schooldb"

@Database(entities = [TeacherEntity::class, StudentEntity::class, StudentsTeachersEntity::class], version = 2, exportSchema = false)
abstract class LocalDatabase : RoomDatabase(){

    abstract fun teacherDao(): TeacherDao
    abstract fun studentDao(): StudentDao

    companion object {

        val MIGRATION_1_2: Migration = object: Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE teachers " + " ADD COLUMN age INTEGER NOT NULL DEFAULT 1")
            }
        }

        @Volatile private var instance: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            return instance
                ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    )
                        .also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, LocalDatabase::class.java,
                DATABASE_NAME
            )
                //.addMigrations(MIGRATION_1_2)
                //.fallbackToDestructiveMigration()// If you don't need to preserve your database data
                //.fallbackToDestructiveMigrationFrom(1) // Destructively recreate the database only from a specific version
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // moving to a new thread
                        ioThread {
                            var teachers = listOf(teacher, teacher2, teacher3)
                            getInstance(context).teacherDao()
                                .insert(teachers)
                        }
                    }
                })
                .build()

        val teacher = TeacherEntity(1,"Teacher1",1)
        val teacher2 = TeacherEntity(2,"Teacher2",1)
        val teacher3 = TeacherEntity(3,"Teacher3",1)

    }

}