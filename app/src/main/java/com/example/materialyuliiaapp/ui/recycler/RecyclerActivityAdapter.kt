package com.example.materialyuliiaapp.ui.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.materialyuliiaapp.R
import com.example.materialyuliiaapp.databinding.ActivityRecyclerItemHeaderBinding
import com.example.materialyuliiaapp.databinding.ActivityRecyclerItemNoteTodayBinding
import com.example.materialyuliiaapp.databinding.ActivityRecyclerItemNoteTomorrowBinding

class RecyclerActivityAdapter(private var list: MutableList<Pair<NoteData, Boolean>>,
    private var onListItemClickListener: OnListItemClickListener
) :
    RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    fun setList(newList: List<Pair<NoteData, Boolean>>) {

        val result = DiffUtil.calculateDiff(DiffUtilCallback(list, newList))
        result.dispatchUpdatesTo(this)

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

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            when (getItemViewType(position)) {
                NoteData.TYPE_NOTE_TOMORROW -> {
                    val res =
                        createCombinedPayload(payloads as List<Change<Pair<NoteData, Boolean>>>)
                    val oldData = res.oldData
                    val newData = res.newData

                    if (newData.first.noteTitle != oldData.first.noteTitle) {
                        (holder as NoteTomorrowViewHolder).itemView.findViewById<TextView>(R.id.note_tomorrow_title).text =
                            newData.first.noteTitle
                    }
                }
            }
        }
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

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
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

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(listItem: Pair<NoteData, Boolean>) {
            (ActivityRecyclerItemHeaderBinding.bind(itemView)).apply {
                header.text = listItem.first.noteTitle
            }
        }

        override fun onItemSelected() {

        }

        override fun onItemClear() {

        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {

        toPosition.takeIf { it > 0 && it < list.size }?.also {
            list.removeAt(fromPosition).apply {
                list.add(toPosition, this)
            }
            notifyItemMoved(fromPosition, toPosition)
        }
    }

    override fun onItemDismiss(position: Int) {

        list.removeAt(position)
        notifyItemRemoved(position)
    }
}