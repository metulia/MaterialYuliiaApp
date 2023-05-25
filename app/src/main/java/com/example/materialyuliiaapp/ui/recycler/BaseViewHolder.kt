package com.example.materialyuliiaapp.ui.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view), ItemTouchHelperViewHolder {
    abstract fun bind(listItem: Pair<NoteData, Boolean>)
}