package com.example.materialyuliiaapp.ui.recycler

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private var oldList: List<Pair<NoteData, Boolean>>,
    private var newList: List<Pair<NoteData, Boolean>>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =

        oldList[oldItemPosition].first.id == newList[newItemPosition].first.id


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return (oldList[oldItemPosition].first.type == newList[newItemPosition].first.type
                && oldList[oldItemPosition].first.noteTitle == newList[newItemPosition].first.noteTitle
                && oldList[oldItemPosition].first.noteDescription == newList[newItemPosition].first.noteDescription)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {

        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return Change(
            oldItem,
            newItem
        )
    }
}