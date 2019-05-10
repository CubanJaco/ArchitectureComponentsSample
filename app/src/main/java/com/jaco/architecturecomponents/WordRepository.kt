package com.jaco.architecturecomponents

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class WordRepository(application: Application) {

    private val mWordDao: WordDao?
    private val mAllWords: LiveData<List<Word>>?

    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db?.wordDao()
        mAllWords = mWordDao?.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>>? {
        return mAllWords
    }

    fun insert(word: Word) {
        InsertAsyncTask(mWordDao!!).execute(word)
    }

    class InsertAsyncTask(private val mAsyncTaskDao: WordDao): AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word?): Void? {
            if (!params.isNullOrEmpty() && params[0] != null)
                mAsyncTaskDao.insert(params[0]!!)
            return null
        }

    }

}