package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.adapters

import android.content.Context
import android.text.Html
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
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.fragments.SearchRepoFragmentDirections
import kotlinx.android.synthetic.main.item_list_layout.view.*
import kotlinx.android.synthetic.main.network_state_item.view.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 *
 * Created by Nikolay Tkachenko on 22, October, 2020
 *
 */

class RepoListAdapter(val context: Context) : PagedListAdapter<ItemResponse, RecyclerView.ViewHolder>(
    USER_COMPARATOR
) {

    val ITEM_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2

    private var searchText  = ""

    private var networkState : NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view : View
        if(viewType == ITEM_VIEW_TYPE){
            view = layoutInflater.inflate(R.layout.item_list_layout, parent, false)
            return ItemRespondViewHolder(view, searchText)
        }else{
            view = layoutInflater.inflate(R.layout.network_state_item, parent, false)
            return NetworkStateViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
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

    class ItemRespondViewHolder(view: View, var searchText: String) : RecyclerView.ViewHolder(view){
        fun bind(item: ItemResponse?, context: Context){
            val moviePosterUrl: String = item?.owner?.avatarUrl!!
            Glide.with(context)
                .load(moviePosterUrl)
                .into(itemView.iv_avatar)
            var nameRepoTxt = item?.name

            val matcherNameDescr = ("(?i)"+searchText).toRegex()
            var newNameRepoTxt = nameRepoTxt?.replace( matcherNameDescr , "<font color='red'>"+ matcherNameDescr.find(nameRepoTxt)?.groupValues?.getOrNull(0) +"</font>")
            itemView.tv_name_repo.text = Html.fromHtml(newNameRepoTxt)

            item?.description?.let {descr ->
                var descrRepoTxt = descr
                var newDescrRepoTxt = descrRepoTxt?.replace( matcherNameDescr , "<font color='red'>"+ matcherNameDescr.find(descrRepoTxt)?.groupValues?.getOrNull(0) +"</font>")
                itemView.tv_descr_repo.text = Html.fromHtml(newDescrRepoTxt)
            }


            itemView.setOnClickListener {
                item?.let {
                    val action
                            = SearchRepoFragmentDirections.actionSearchRepoFragmentToDetailRepoFragment(
                        it
                    )
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

    fun setSearchText(seacrh: String){
        this.searchText = seacrh
    }

}