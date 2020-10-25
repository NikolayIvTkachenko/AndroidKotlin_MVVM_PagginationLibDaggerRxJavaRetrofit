package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse

/**
 *
 * Created by Nikolay Tkachenko on 25, October, 2020
 *
 */
class RepoDiffUtil (
    private val oldList: List<ItemResponse>,
    private val newList: List<ItemResponse>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].idItem == newList[newItemPosition].idItem
                && oldList[oldItemPosition].description == newList[newItemPosition].description
                && oldList[oldItemPosition].name == newList[newItemPosition].name
    }

}