package com.scorp.casestudy.extensions

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.scorp.casestudy.ui.PersonListAdapter

@SuppressLint("NotifyDataSetChanged")
fun RecyclerView.notifyDataWithSaveState(adapter: PersonListAdapter) {
    val recyclerViewState = this.layoutManager?.onSaveInstanceState()
    adapter.notifyDataSetChanged()
    this.layoutManager?.onRestoreInstanceState(recyclerViewState)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}