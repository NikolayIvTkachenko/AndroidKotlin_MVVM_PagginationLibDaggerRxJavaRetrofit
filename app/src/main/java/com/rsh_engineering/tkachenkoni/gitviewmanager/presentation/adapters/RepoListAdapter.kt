package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsh_engineering.tkachenkoni.gitviewmanager.R
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import kotlinx.android.synthetic.main.item_list_layout.view.*

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

//1. название репозитория (items.name)
//2. описание репозитория (items.description)
//3. аватар владельца (items.owner.avatar_url)

class RepoListAdapter : PagedListAdapter<ItemResponse, RepoListAdapter.ItemViewHolder>(ItemResponseDiffUtil()) {

    val itemsList = ArrayList<ItemResponse>()

    class  ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var ivAvatar = view.iv_avatar
        var tvName = view.tv_name_repo
        var tvDescr = view.tv_descr_repo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_list_layout,null)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val moviePosterUrl: String = itemsList.get(position).owner?.avatarUrl!!
        Glide.with(holder.itemView.context)
            .load(moviePosterUrl)
            .into(holder.ivAvatar)

        holder.tvName.text = itemsList.get(position).name
        holder.tvDescr.text = itemsList.get(position).description
        holder.itemView.setOnClickListener {
            itemsList.get(position).let { idRepo ->

            }
        }
    }

    override fun getItemCount(): Int = itemsList.size

//    fun addRepo(list: ArrayList<ItemResponse>){
//        Log.d("TESTNETWORK", "addRepo()")
//        Log.d("TESTNETWORK", "list.count = ${list}")
//        val repoDiffUtil = RepoDiffUtil(this.itemsList, list)
//        val repoDiffResult = DiffUtil.calculateDiff(repoDiffUtil)
//        itemsList.addAll(list)
//        repoDiffResult.dispatchUpdatesTo(this)
//
//    }
//
//    fun clearList() {
//        Log.d("TESTNETWORK", "clearList()")
//        itemsList.clear()
//        notifyDataSetChanged()
//    }

    class ItemResponseDiffUtil : DiffUtil.ItemCallback<ItemResponse>(){
        override fun areItemsTheSame(oldItem: ItemResponse, newItem: ItemResponse): Boolean {
            return oldItem.idItem == newItem.idItem
        }

        override fun areContentsTheSame(oldItem: ItemResponse, newItem: ItemResponse): Boolean {
            return oldItem.idItem == newItem.idItem
                    && oldItem.description == newItem.description
                    && oldItem.name == newItem.name
        }
    }
}