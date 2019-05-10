package com.jaco.architecturecomponents

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

class WordViewModel(application: Application): AndroidViewModel(application) {

    private val mRepository = WordRepository(application)
    val mAllWords = mRepository.getAllWords()

    fun insert(word: Word) {
        mRepository.insert(word)
    }
}