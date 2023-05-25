package com.example.materialyuliiaapp.ui.recycler

data class NoteData(
    val id: Int = 0,
    val type: Int,
    val noteTitle: String = "Text",
    val noteDescription: String? = "SomeDescription"
) {

    companion object {
        const val TYPE_NOTE_TODAY = 1
        const val TYPE_NOTE_TOMORROW = 2
        const val TYPE_HEADER = 3
    }
}
