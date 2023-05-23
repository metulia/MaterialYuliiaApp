package com.example.materialyuliiaapp.ui.recycler

interface OnListItemClickListener {

    fun onItemClick(listItem: NoteData)
    fun onAddBtnClick(position: Int)
    fun onRemoveBtnClick(position: Int)
}