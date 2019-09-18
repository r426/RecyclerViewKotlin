package com.ryeslim.recyclerviewkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: WordListAdapter? = null

    private val mWordList = LinkedList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener(View.OnClickListener {
            val wordListSize = mWordList.size
            // Add a new word to the wordList.
            mWordList.addLast("+ Word $wordListSize")
            // Notify the adapter, that the data has changed.
            mRecyclerView!!.adapter?.notifyItemInserted(wordListSize)
            // Scroll to the bottom.
            mRecyclerView!!.smoothScrollToPosition(wordListSize)
        })

        // Put initial data into the word list.
        for (i in 0..19) {
            mWordList.addLast("Word $i")
        }

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview)
        // Create an adapter and supply the data to be displayed.
        mAdapter = WordListAdapter(this, mWordList)
        // Connect the adapter with the RecyclerView.
        mRecyclerView!!.adapter = mAdapter
        // Give the RecyclerView a default layout manager.
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
