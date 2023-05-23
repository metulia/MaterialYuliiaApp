package com.example.materialyuliiaapp.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialyuliiaapp.databinding.ActivityRecyclerItemHeaderBinding
import com.example.materialyuliiaapp.databinding.ActivityRecyclerItemNoteTodayBinding
import com.example.materialyuliiaapp.databinding.ActivityRecyclerItemNoteTomorrowBinding

class RecyclerActivityAdapter(
    private var onListItemClickListener: OnListItemClickListener
) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private lateinit var list: List<NoteData>

    fun setList(newList: List<NoteData>) {
        this.list = newList
    }

    fun setAddToList(newList: List<NoteData>, position: Int) {
        this.list = newList
        notifyItemChanged(position)
    }

    fun setRemoveToList(newList: List<NoteData>, position: Int) {
        this.list = newList
        notifyItemRemoved(position)
    }

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
            NoteData.TYPE_HEADER -> {
                val view =
                    ActivityRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                HeaderViewHolder(view.root)
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
                addItemImageView.setOnClickListener {
                    onListItemClickListener.onAddBtnClick(layoutPosition)
                }
                removeItemImageView.setOnClickListener {
                    onListItemClickListener.onRemoveBtnClick(layoutPosition)
                }
            }
        }
    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(listItem: NoteData) {
            (ActivityRecyclerItemHeaderBinding.bind(itemView)).apply {
                header.text = listItem.noteTitle
            }
        }

    }
}