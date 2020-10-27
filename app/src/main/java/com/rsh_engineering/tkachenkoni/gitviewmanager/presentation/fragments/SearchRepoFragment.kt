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
import com.rsh_engineering.tkachenkoni.gitviewmanager.data.repository_impl.NetworkState
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.adapters.RepoListAdapter
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.utils.hideKeyboard
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.GeneralViewModel
import com.rsh_engineering.tkachenkoni.gitviewmanager.presentation.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_search_repo.*
import javax.inject.Inject


class SearchRepoFragment : BaseFragment() {

    private lateinit var generalViewModel: GeneralViewModel
    private lateinit var searcViewModel: SearcViewModel

    lateinit var repoadapter : RepoListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var bntPressedCallBack = false

    override fun getLayoutById(): Int {
        return R.layout.fragment_search_repo
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        repoadapter = RepoListAdapter(requireActivity())
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
            setHasFixedSize(true)
            adapter = repoadapter
        }

        btn_search.setOnClickListener {
            if (!bntPressedCallBack){
                requestDataRepositories()
            }
        }

//        RxTextView.textChanges(et_input_search)
//            .debounce(700, TimeUnit.MILLISECONDS)
//            .subscribe(object : Action1<CharSequence> {
//                override fun call(t: CharSequence?) {
//                    requestDataRepositories()
//                }
//            })
    }

    fun requestDataRepositories(){
        if(et_input_search.text != null && et_input_search.text.length > 0){
            updateAfterClickButtonFind()
            bntPressedCallBack = true
            btn_search.isEnabled = false
        }
    }

    fun updateAfterClickButtonFind(){
        Log.d("TESTNETWORK", "updateAfterClickButtonFind()")
        generalViewModel.getSearchRepositoryPageList(this, et_input_search.text.toString())
        searchText()
        networkStatus()
        hideKeyboard(requireActivity())
    }

    fun setupObservable(){
        networkStatus()
        searchText()
    }

    fun networkStatus(){
        generalViewModel.getNetworkState().observe(viewLifecycleOwner, Observer {

            bntPressedCallBack = false
            btn_search.isEnabled = true

            sf_progress_bar.visibility =
                if (generalViewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            sf_txt_error.visibility =
                if (generalViewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if (!generalViewModel.listIsEmpty()) {
                repoadapter.setNetworkState(it)
                repoadapter.setSearchText(et_input_search.text.toString())
            }

            when (it) {
                NetworkState.LOADING -> {
                    tv_message.text = NetworkState.LOADING.msg
                }
                NetworkState.ERROR -> {
                    tv_message.text = NetworkState.ERROR.msg
                }
                NetworkState.LOADED -> {
                    tv_message.text = NetworkState.LOADED.msg
                }
                NetworkState.ENDOFLIST -> {
                    tv_message.text = NetworkState.ENDOFLIST.msg
                }
            }
        })
    }

    fun searchText(){
        generalViewModel.itemResponsePageList.observe(viewLifecycleOwner, Observer { pageList ->
            bntPressedCallBack = false
            btn_search.isEnabled = true
            if (pageList != null) {
                sf_progress_bar.visibility = View.GONE
                sf_txt_error.visibility = View.GONE

                repoadapter.submitList(pageList)
            }
        })
    }
}