package com.jaco.architecturecomponents

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class WordListAdapter(context: Context): RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mWords: List<Word> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): WordViewHolder {

        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)

    }

    fun setWords(words: List<Word>?) {
        if (words == null)
            mWords = ArrayList()
        else
            mWords = words

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mWords.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        holder.wordItemView.text = mWords[position].mWord

    }

    class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val wordItemView = itemView.findViewById<TextView>(R.id.textView)

    }
}
