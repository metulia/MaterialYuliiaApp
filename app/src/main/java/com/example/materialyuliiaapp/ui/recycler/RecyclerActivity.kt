package com.example.materialyuliiaapp.ui.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.materialyuliiaapp.databinding.ActivityRecyclerBinding

class RecyclerActivity : AppCompatActivity(), OnListItemClickListener {

    private lateinit var binding: ActivityRecyclerBinding
    private lateinit var adapter: RecyclerActivityAdapter

    private val list = arrayListOf(
        Pair(NoteData(NoteData.TYPE_HEADER, "HEADER"), false),
        Pair(NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", ""), false),
        Pair(NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", "To make supper"), false),
        Pair(NoteData(NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To water flowers"), false),
        Pair(NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", "To make dinner"), false),
        Pair(NoteData(NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To clean my room"), false),
        Pair(NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", ""), false),
        Pair(NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", ""), false),
        Pair(NoteData(NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To make homework"), false),
        Pair(NoteData(NoteData.TYPE_NOTE_TODAY, "For Today", ""), false),
        Pair(NoteData(NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", ""), false),
        Pair(NoteData(NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To make homework"), false)
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

        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView((binding.recyclerView))
    }

    override fun onItemClick(listItem: NoteData) {

    }

    override fun onAddBtnClick(position: Int) {
        list.add(
            position,
            Pair(NoteData(NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", ""), false)
        )
        adapter.setAddToList(list, position)
    }

    override fun onRemoveBtnClick(position: Int) {
        list.removeAt(position)
        adapter.setRemoveToList(list, position)
    }

}