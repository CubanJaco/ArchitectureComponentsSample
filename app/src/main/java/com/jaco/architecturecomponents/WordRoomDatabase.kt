package com.jaco.architecturecomponents

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        var INSTANCE: WordRoomDatabase? = null
        private const val DB_NAME = "word-db"

        fun getDatabase(context: Context): WordRoomDatabase? {

            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context, WordRoomDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .addCallback(sRoomDatabaseCallback)
                        .build()
                }
            }

            return INSTANCE
        }

        private val sRoomDatabaseCallback = object : Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }

        }
    }

    class PopulateDbAsync(db: WordRoomDatabase): AsyncTask<Void, Void, Void>() {

        private val mDao = db.wordDao()
        private val words = arrayOf("perro", "gato", "cocodrilo")

        override fun doInBackground(vararg params: Void?): Void? {

            mDao.deleteAll()

            for (word in words) {
                mDao.insert(Word(word))
            }

            return null
        }

    }

}