package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.rsh_engineering.tkachenkoni.gitviewmanager.App
import com.rsh_engineering.tkachenkoni.gitviewmanager.R
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.adapters.LanguageAdapter
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.DetailViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.GeneralViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.ViewModelFactory
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.fragment_detail_repo.*
import javax.inject.Inject

class DetailRepoFragment : BaseFragment() {

    private lateinit var generalViewModel : GeneralViewModel
    private lateinit var detailViewModel : DetailViewModel

    lateinit var itemResponse : ItemResponse

    private var adapterLang = LanguageAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun getLayoutById(): Int {
        return R.layout.fragment_detail_repo
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.getAppComponent().inject(this)
        generalViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)[GeneralViewModel::class.java]
        detailViewModel = ViewModelProviders.of(this, viewModelFactory)[DetailViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemResponse = arguments?.getSerializable("ItemResponse") as ItemResponse
        detailViewModel.setLvItemResponse(itemResponse)
        initUI()
        downloadData()
    }

    fun initUI() {
        setupView()
        setupObservables()
    }

    fun downloadData(){
        detailViewModel.getLanguages()
        detailViewModel.getReadMe()
    }

    fun setupView() {
        val moviePosterUrl: String = itemResponse.owner?.avatarUrl!!
        Glide.with(requireActivity())
            .load(moviePosterUrl)
            .into(iv_deatil_avatar)
        tv_name_item.text = itemResponse.name
        tv_descr_item.text = itemResponse.description


        rc_language.adapter = adapterLang
        rc_language.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        rc_language.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 300
        }
    }

    fun setupObservables(){
        detailViewModel.getLvLanguages().observe(viewLifecycleOwner, Observer { fields ->
            var listLang = ArrayList<String>()
            for((s,i) in fields){
                listLang.add(s)
            }
            adapterLang.setData(listLang, fields)
            adapterLang.notifyDataSetChanged()
        })

        detailViewModel.getLvReadMe().observe(viewLifecycleOwner, Observer {readme ->
            tv_readme.text = readme
        })
    }
}