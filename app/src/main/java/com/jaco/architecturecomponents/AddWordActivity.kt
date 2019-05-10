package com.jaco.architecturecomponents

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_add_word.*

class AddWordActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY = "com.jaco.architecturecomponents.REPLY"
    }

    private lateinit var mEditWordView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_word)
        setSupportActionBar(toolbar)

        mEditWordView = findViewById(R.id.edit_word)

        button_save.setOnClickListener {

            val replyIntent = Intent()

            if (TextUtils.isEmpty(mEditWordView.text)) {
                setResult(RESULT_CANCELED, replyIntent)
            } else {
                val word = mEditWordView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(RESULT_OK, replyIntent)
            }

            finish()
        }
    }

}
