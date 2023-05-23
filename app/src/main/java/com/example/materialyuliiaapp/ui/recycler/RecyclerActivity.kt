package com.example.materialyuliiaapp.ui.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.materialyuliiaapp.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity(), OnListItemClickListener {

    private lateinit var binding: ActivityRecyclerBinding
    private lateinit var adapter: RecyclerActivityAdapter

    private val list = arrayListOf(
        NoteData(NoteData.TYPE_HEADER, "HEADER"),
        NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", ""),
        NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", "To make supper"),
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecyclerActivityAdapter(this)

        adapter.setList(list)

        binding.recyclerView.adapter = adapter

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        binding.recyclerActivityFAB.setOnClickListener {
            onAddBtnClick(list.size)
        }
    }

    override fun onItemClick(listItem: NoteData) {

    }

    override fun onAddBtnClick(position: Int) {
        list.add(
            position,
            NoteData(NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "")
        )
        adapter.setAddToList(list, position)
    }

    override fun onRemoveBtnClick(position: Int) {
        list.removeAt(position)
        adapter.setRemoveToList(list, position)
    }

}