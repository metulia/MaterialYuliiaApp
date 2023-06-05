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

    private var isNewList = false

    private val list = arrayListOf(
        Pair(NoteData(0, NoteData.TYPE_HEADER, "HEADER"), false),
        Pair(NoteData(1, NoteData.TYPE_NOTE_TODAY, "For Today", ""), false),
        Pair(NoteData(2, NoteData.TYPE_NOTE_TODAY, "For Today", "To make supper"), false),
        Pair(NoteData(3, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To water flowers"), false),
        Pair(NoteData(4, NoteData.TYPE_NOTE_TODAY, "For Today", "To make dinner"), false),
        Pair(NoteData(5, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To clean my room"), false),
        Pair(NoteData(6, NoteData.TYPE_NOTE_TODAY, "For Today", ""), false),
        Pair(NoteData(7, NoteData.TYPE_NOTE_TODAY, "For Today", ""), false),
        Pair(NoteData(8, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To make homework"), false),
        Pair(NoteData(9, NoteData.TYPE_NOTE_TODAY, "For Today", ""), false),
        Pair(NoteData(10, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", ""), false),
        Pair(NoteData(11, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To make homework"), false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecyclerActivityAdapter(list, this)

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

        binding.recyclerActivityDiffUtilFAB.setOnClickListener {
            changeAdapterData()
        }
    }

    private fun changeAdapterData() {
        adapter.setList(createItemList(isNewList).map { it })
        isNewList = !isNewList
    }

    private fun createItemList(instanceNumber: Boolean): List<Pair<NoteData, Boolean>> {
        return when (instanceNumber) {
            false -> listOf(
                Pair(NoteData(0, NoteData.TYPE_HEADER, "HEADER"), false),
                Pair(
                    NoteData(1, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To water flowers"),
                    false
                ),
                Pair(
                    NoteData(2, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To water flowers"),
                    false
                ),
                Pair(
                    NoteData(3, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To water flowers"),
                    false
                ),
                Pair(
                    NoteData(4, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To water flowers"),
                    false
                ),
                Pair(
                    NoteData(5, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To clean my room"),
                    false
                ),
                Pair(
                    NoteData(6, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To water flowers"),
                    false
                ),
            )
            true -> listOf(
                Pair(NoteData(0, NoteData.TYPE_HEADER, "HEADER"), false),
                Pair(NoteData(1, NoteData.TYPE_NOTE_TODAY, "For Today", ""), false),
                Pair(NoteData(2, NoteData.TYPE_NOTE_TODAY, "For Today", "To make supper"), false),
                Pair(
                    NoteData(3, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To water flowers"),
                    false
                ),
                Pair(NoteData(4, NoteData.TYPE_NOTE_TODAY, "For Today", "To make dinner"), false),
                Pair(
                    NoteData(5, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", "To clean my room"),
                    false
                ),
                Pair(NoteData(6, NoteData.TYPE_NOTE_TODAY, "For Today", ""), false),
            )
        }
    }

    override fun onItemClick(listItem: NoteData) {

    }

    override fun onAddBtnClick(position: Int) {
        list.add(
            position,
            Pair(NoteData(0, NoteData.TYPE_NOTE_TOMORROW, "For Tomorrow", ""), false)
        )
        adapter.setAddToList(list, position)
    }

    override fun onRemoveBtnClick(position: Int) {
        list.removeAt(position)
        adapter.setRemoveToList(list, position)
    }

}