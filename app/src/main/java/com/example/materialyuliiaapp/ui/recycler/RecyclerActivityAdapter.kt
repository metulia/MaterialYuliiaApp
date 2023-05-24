package com.example.materialyuliiaapp.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.materialyuliiaapp.databinding.ActivityRecyclerItemHeaderBinding
import com.example.materialyuliiaapp.databinding.ActivityRecyclerItemNoteTodayBinding
import com.example.materialyuliiaapp.databinding.ActivityRecyclerItemNoteTomorrowBinding

class RecyclerActivityAdapter(
    private var onListItemClickListener: OnListItemClickListener
) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private lateinit var list: MutableList<Pair<NoteData, Boolean>>

    fun setList(newList: List<Pair<NoteData, Boolean>>) {
        this.list = newList.toMutableList()
    }

    fun setAddToList(newList: List<Pair<NoteData, Boolean>>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemInserted(position)
    }

    fun setRemoveToList(newList: List<Pair<NoteData, Boolean>>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemRemoved(position)
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].first.type
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
        override fun bind(listItem: Pair<NoteData, Boolean>) {

            (ActivityRecyclerItemNoteTodayBinding.bind(itemView)).apply {

                noteTodayTitle.text = listItem.first.noteTitle

                noteTodayDescription.text = listItem.first.noteDescription
            }
        }
    }

    inner class NoteTomorrowViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(listItem: Pair<NoteData, Boolean>) {

            (ActivityRecyclerItemNoteTomorrowBinding.bind(itemView)).apply {

                noteTomorrowTitle.text = listItem.first.noteTitle

                noteTomorrowDescription.text = listItem.first.noteDescription

                addItemImageView.setOnClickListener {
                    onListItemClickListener.onAddBtnClick(layoutPosition)
                }

                removeItemImageView.setOnClickListener {
                    onListItemClickListener.onRemoveBtnClick(layoutPosition)
                }

                moveItemUp.setOnClickListener {

                    layoutPosition.takeIf { it > 1 }?.also {
                        list.removeAt(layoutPosition).apply {
                            list.add(layoutPosition - 1, this)
                        }
                        notifyItemMoved(layoutPosition, layoutPosition - 1)
                    }
                }

                moveItemDown.setOnClickListener {

                    layoutPosition.takeIf { it < list.size - 1 }?.also {

                        list.removeAt(layoutPosition).apply {
                            list.add(layoutPosition + 1, this)
                        }
                        notifyItemMoved(layoutPosition, layoutPosition + 1)
                    }
                }

                noteTomorrowImageView.setOnClickListener {
                    list[layoutPosition] = list[layoutPosition].let {
                        it.first to !it.second
                    }
                    marsDescriptionTextView.isVisible =
                        list[layoutPosition].second
                }
            }
        }
    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(listItem: Pair<NoteData, Boolean>) {
            (ActivityRecyclerItemHeaderBinding.bind(itemView)).apply {
                header.text = listItem.first.noteTitle
            }
        }
    }
}