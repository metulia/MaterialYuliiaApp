package com.example.materialyuliiaapp.ui.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialyuliiaapp.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = arrayListOf(
            NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", ""),
            NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", ""),
            NoteData(NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To water flowers"),
            NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", "To make dinner"),
            NoteData(NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To clean my room"),
            NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", ""),
            NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", ""),
            NoteData(NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To make homework"),
            NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", ""),
            NoteData(NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", ""),
            NoteData(NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To make homework"),
        )

        binding.recyclerView.adapter = RecyclerActivityAdapter(list)
    }
}