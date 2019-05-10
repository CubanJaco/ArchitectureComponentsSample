package com.jaco.architecturecomponents

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NEW_WORD_ACTIVITY_REQUEST_CODE = 12
    }

    private lateinit var mWordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = WordListAdapter(this)
        recyclerview.adapter = adapter

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        mWordViewModel.mAllWords?.observe(this, Observer<List<Word>> { words ->
            adapter.setWords(words)
        })

        fab.setOnClickListener {
            val intent = Intent(this, AddWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val word = Word(data!!.getStringExtra(AddWordActivity.EXTRA_REPLY))
            mWordViewModel.insert(word)
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }

    }
}
