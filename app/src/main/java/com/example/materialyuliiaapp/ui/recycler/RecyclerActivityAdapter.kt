package com.example.materialyuliiaapp.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialyuliiaapp.databinding.ActivityRecyclerItemNoteTodayBinding
import com.example.materialyuliiaapp.databinding.ActivityRecyclerItemNoteTomorrowBinding

class RecyclerActivityAdapter(private var list: List<NoteData>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            NoteData.TYPE_NOTE_TODAY -> {
                val view =
                    ActivityRecyclerItemNoteTodayBinding.inflate(LayoutInflater.from(parent.context))
                NoteTodayViewHolder(view.root)
            }
            NoteData.TYPE_NOTE_TOMORROW -> {
                val view =
                    ActivityRecyclerItemNoteTomorrowBinding.inflate(LayoutInflater.from(parent.context))
                NoteTomorrowViewHolder(view.root)
            }
            else -> {
                val view =
                    ActivityRecyclerItemNoteTomorrowBinding.inflate(LayoutInflater.from(parent.context))
                NoteTomorrowViewHolder(view.root)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class NoteTodayViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(listItem: NoteData) {

            (ActivityRecyclerItemNoteTodayBinding.bind(itemView)).apply {
                noteTodayTitle.text = listItem.noteTitle
                noteTodayDescription.text = listItem.noteDescription
            }
        }
    }

    inner class NoteTomorrowViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(listItem: NoteData) {

            (ActivityRecyclerItemNoteTomorrowBinding.bind(itemView)).apply {
                noteTomorrowTitle.text = listItem.noteTitle
                noteTomorrowDescription.text = listItem.noteDescription
            }
        }
    }
}