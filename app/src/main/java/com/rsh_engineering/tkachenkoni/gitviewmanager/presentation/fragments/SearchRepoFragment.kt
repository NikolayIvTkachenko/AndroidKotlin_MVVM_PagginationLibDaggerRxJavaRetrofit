package com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsh_engineering.tkachenkoni.gitviewmanager.App
import com.rsh_engineering.tkachenkoni.gitviewmanager.R
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.ItemResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.domain.model_entity.SearchResponse
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.adapters.RepoListAdapter
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.GeneralViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.SearcViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_search_repo.*
import javax.inject.Inject


class SearchRepoFragment : BaseFragment() {

    private lateinit var generalViewModel: GeneralViewModel
    private lateinit var searcViewModel: SearcViewModel

    val repoadapter = RepoListAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun getLayoutById(): Int {
        return R.layout.fragment_search_repo
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        App.getAppComponent().inject(this)
        generalViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)[GeneralViewModel::class.java]
        searcViewModel = ViewModelProviders.of(this, viewModelFactory)[SearcViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    fun initUI() {
        setupView()
        setupObservable()
    }

    fun setupView() {
        val linearLayoutManager = LinearLayoutManager(context)
        rv_result_search.apply {
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = repoadapter

        }

        btn_search.setOnClickListener {
            if(et_input_search.text != null && et_input_search.text.length > 0){
                updateAfterClickButtonFind()
            }
        }
    }

    fun updateAfterClickButtonFind(){
        Log.d("TESTNETWORK", "updateAfterClickButtonFind()")
        //repoadapter.clearList()
        generalViewModel.getSearchRepositoryPageList(et_input_search.text.toString())
    }

    fun setupObservable(){
        generalViewModel.getLivesearchRepository().observe(viewLifecycleOwner, Observer { items ->
            setDataItems(items)
        })
        generalViewModel.getLivesearchRepositoryCount().observe(viewLifecycleOwner, Observer {count ->
            setDataCount(count)
        })

        generalViewModel.itemsResponsePagedList.observe(viewLifecycleOwner, Observer {pageList ->
            Log.d("TESTNETWORK","pageList")
            repoadapter.submitList(pageList)
        })
    }

    fun setDataCount(count : Int){
        Log.d("TESTNETWORK", "setDataCount(count) count = ${count}")
        tv_message.text = "Count Repositories: ${count?.toString()}"
    }


    fun setDataItems(items : List<ItemResponse>) {
        Log.d("TESTNETWORK", "setDataItems()")
        items?.let {
            items?.let {
                    items ->
                Log.d("TESTNETWORK", "items.count = ${items.count()}")
                //repoadapter.addRepo(items as ArrayList<ItemResponse>)
            }
        }
    }
}