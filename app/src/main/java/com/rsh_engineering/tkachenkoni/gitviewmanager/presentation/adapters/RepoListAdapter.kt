package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rsh_engineering.tkachenkoni.gitviewmanager.R
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.NetworkState
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.fragments.DetailRepoFragmentDirections
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.fragments.SearchRepoFragmentDirections
import kotlinx.android.synthetic.main.item_list_layout.view.*
import kotlinx.android.synthetic.main.network_state_item.view.*
import java.lang.Exception

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

//1. название репозитория (items.name)
//2. описание репозитория (items.description)
//3. аватар владельца (items.owner.avatar_url)

class RepoListAdapter(val context: Context) : PagedListAdapter<ItemResponse, RecyclerView.ViewHolder>(USER_COMPARATOR) {

    val ITEM_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2

    private var networkState : NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view : View
        if(viewType == ITEM_VIEW_TYPE){
            view = layoutInflater.inflate(R.layout.item_list_layout, parent, false)
            return ItemRespondViewHolder(view)
        }else{
            view = layoutInflater.inflate(R.layout.network_state_item, parent, false)
            return NetworkStateViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder , position: Int) {
        if(getItemViewType(position) == ITEM_VIEW_TYPE){
            (holder as ItemRespondViewHolder).bind(getItem(position), context)
        }else{
            (holder as NetworkStateViewHolder).bind(networkState)
        }
    }

    override fun getItemViewType(position: Int):Int{
        return if (hasExtraRow() && position == itemCount - 1){
            NETWORK_VIEW_TYPE
        }else{
            ITEM_VIEW_TYPE
        }
    }

    private fun hasExtraRow(): Boolean{
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemCount():Int{
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<ItemResponse>(){
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

    class ItemRespondViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(item: ItemResponse?, context: Context){
            val moviePosterUrl: String = item?.owner?.avatarUrl!!
            Glide.with(context)
                .load(moviePosterUrl)
                .into(itemView.iv_avatar)
            itemView.tv_name_repo.text = item?.name
            itemView.tv_descr_repo.text = item?.description
            itemView.setOnClickListener {
                item?.let {
                    val action
                            = SearchRepoFragmentDirections.actionSearchRepoFragmentToDetailRepoFragment(it)
                    itemView.findNavController().navigate(action)
                }
            }
        }
    }

    class NetworkStateViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(networkState: NetworkState?){
            if(networkState != null && networkState == NetworkState.LOADING){
                itemView.progress_bar.visibility = View.VISIBLE
            }else{
                itemView.progress_bar.visibility = View.GONE
            }

            if(networkState != null && networkState == NetworkState.ERROR){
                itemView.txt_error.visibility = View.VISIBLE
                itemView.txt_error.text = networkState.msg
            }else if(networkState != null && networkState == NetworkState.ENDOFLIST){
                itemView.txt_error.visibility = View.VISIBLE
                itemView.txt_error.text = networkState.msg
            }else{
                itemView.txt_error.visibility = View.GONE
            }

        }
    }


    fun setNetworkState(newNetworkState: NetworkState){
        val previousState : NetworkState? = this.networkState
        val hadExtraRow : Boolean = hasExtraRow()

        this.networkState = newNetworkState
        val hasExtraRow: Boolean = hasExtraRow()

        if(hadExtraRow != hasExtraRow){
            if(hadExtraRow){
                notifyItemRemoved(super.getItemCount())
            }else{
                notifyItemInserted(super.getItemCount())
            }
        }else if (hasExtraRow && previousState != newNetworkState){
            notifyItemChanged(itemCount - 1)
        }
    }
}