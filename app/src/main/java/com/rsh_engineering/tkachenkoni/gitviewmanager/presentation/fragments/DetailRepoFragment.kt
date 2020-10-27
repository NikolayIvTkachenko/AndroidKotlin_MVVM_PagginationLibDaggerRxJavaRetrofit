package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.rsh_engineering.tkachenkoni.gitviewmanager.App
import com.rsh_engineering.tkachenkoni.gitviewmanager.R
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.adapters.RepoListAdapter
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.DetailViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.GeneralViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.SearcViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_detail_repo.*
import kotlinx.android.synthetic.main.item_list_layout.view.*
import javax.inject.Inject


class DetailRepoFragment : BaseFragment() {

    private lateinit var generalViewModel: GeneralViewModel
    private lateinit var detailViewModel: DetailViewModel

    lateinit var itemResponse : ItemResponse

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun getLayoutById(): Int {
        return R.layout.fragment_detail_repo
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TESTNETWORK", "DetailRepoFragment onAttach")
        App.getAppComponent().inject(this)
        generalViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)[GeneralViewModel::class.java]
        detailViewModel = ViewModelProviders.of(this, viewModelFactory)[DetailViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemResponse = arguments?.getSerializable("ItemResponse") as ItemResponse
        Log.d("TESTNETWORK", "DetailRepoFragment onViewCreated")
        Log.d("TESTNETWORK", "DetailRepoFragment itemResponse = ${itemResponse}")
        initUI()
    }

    fun initUI() {
        setupView()
    }

    fun setupView() {
        Log.d("TESTNETWORK", "DetailRepoFragment setupView()")
        val moviePosterUrl: String = itemResponse?.owner?.avatarUrl!!
        Glide.with(requireActivity())
            .load(moviePosterUrl)
            .into(iv_deatil_avatar)
        tv_name_item.text = itemResponse?.name
        tv_descr_item.text = itemResponse?.description

    }
}