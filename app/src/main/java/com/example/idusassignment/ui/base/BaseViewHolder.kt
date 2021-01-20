package com.example.idusassignment.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T, B : ViewDataBinding>(
        private val itemId: Int,
        parent: ViewGroup,
        layoutRes: Int
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {

    protected val itemBinding: B = DataBindingUtil.bind<B>(itemView)!!

    fun bind(item: T) {
        itemBinding.setVariable(itemId, item)
        itemBinding.executePendingBindings()
    }
}