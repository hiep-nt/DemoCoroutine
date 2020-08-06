package com.example.democoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val viewModel = SearchRepoViewModel(Repository())
    val adapter = ReposAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)
        list.adapter = adapter
        viewModel.repoResult.observe(this, Observer {
            adapter.data = it
            adapter.notifyDataSetChanged()
        })

        initSearch()
    }

    fun initSearch() {
        search_repo.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                if (!search_repo.text.trim().isNullOrEmpty()) {
                    viewModel.updateSearch(search_repo.text.trim().toString())
                }
                true
            } else {
                false
            }
        }

        search_repo.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                if (!search_repo.text.trim().isNullOrEmpty()) {
                    viewModel.updateSearch(search_repo.text.trim().toString())
                }
                true
            } else {
                false
            }
        }
    }
}