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

//1. пункты 1-3 из результатов поиска
//2. список языков в виде облака тэгов (https://developer.github.com/v3/repos/#list-languages)
//3. readme репозитория (https://developer.github.com/v3/repos/contents/#get-the-readme)
//https://api.github.com/repo/1858708
//iNikolayev
//GET /repos/:owner/:repo/languages
//(owner/login) / name
//languagesUrl=https://api.github.com/repos/drugoi/iNikolayev/languages
//GET /repos/:owner/:repo/readme
//https://api.github.com/repos/drugoi/iNikolayev/readme
//https://api.github.com/repos/drugoi/iNikolayev/readme

//https://docs.github.com/rest/reference/repos#list-repository-languages
//ItemResponse(idItem=28298368, nodeId=MDEwOlJlcG9zaXRvcnkyODI5ODM2OA==, name=iNikolayev, fullName=drugoi/iNikolayev, owner=Owner(login=drugoi, idOwner=1858708, nodeId=MDQ6VXNlcjE4NTg3MDg=,
// avatarUrl=https://avatars2.githubusercontent.com/u/1858708?v=4, gravatarId=,
// url=https://api.github.com/users/drugoi,
// receivedEventsUrl=https://api.github.com/users/drugoi/received_events,
// type=User,
// htmlUrl=https://github.com/drugoi,
// followersUrl=https://api.github.com/users/drugoi/followers,
// followingUrl=https://api.github.com/users/drugoi/following{/other_user},
// gistsUrl=https://api.github.com/users/drugoi/gists{/gist_id},
// starredUrl=https://api.github.com/users/drugoi/starred{/owner}{/repo},
// subscriptionsUrl=https://api.github.com/users/drugoi/subscriptions,
// organizationsUrl=https://api.github.com/users/drugoi/orgs,
// reposUrl=https://api.github.com/users/drugoi/repos,
// eventsUrl=https://api.github.com/users/drugoi/events{/privacy},
// siteAdmin=false),
// private=false,
// htmlUrl=https://github.com/drugoi/iNikolayev,
// description=Исходный код расширения iNikolayev (идейный форк nCage),
// fork=false,
// url=https://api.github.com/repos/drugoi/iNikolayev,
// createdAt=2014-12-21T12:07:36Z,
// updatedAt=2020-10-16T09:03:55Z, pushedAt=2020-09-07T09:31:13Z,
// homepage=https://chrome.google.com/webstore/detail/inikolayev/capkagcahfpeicbnbfepenbmognpecae,
// size=632,
// stargazersCount=24,
// watchersCount=24,
// language=JavaScript,
// forksCount=9,
// openIssuesCount=1,
// masterBranch=null,
// defaultBranch=master,
// score=1,
// archiveUrl=https://api.github.com/repos/drugoi/iNikolayev/{archive_format}{/ref},
// assigneesUrl=https://api.github.com/repos/drugoi/iNikolayev/assignees{/user},
// blobsUrl=https://api.github.com/repos/drugoi/iNikolayev/git/blobs{/sha},
// branchesUrl=https://api.github.com/repos/drugoi/iNikolayev/branches{/branch},
// collaboratorsUrl=https://api.github.com/repos/drugoi/iNikolayev/collaborators{/collaborator},
// commentsUrl=https://api.github.com/repos/drugoi/iNikolayev/comments{/number},
// commitsUrl=https://api.github.com/repos/drugoi/iNikolayev/commits{/sha},
// compareUrl=https://api.github.com/repos/drugoi/iNikolayev/compare/{base}...{head},
// contentsUrl=https://api.github.com/repos/drugoi/iNikolayev/contents/{+path},
// contributorsUrl=https://api.github.com/repos/drugoi/iNikolayev/contributors,
// deploymentsUrl=https://api.github.com/repos/drugoi/iNikolayev/deployments,
// downloadsUrl=https://api.github.com/repos/drugoi/iNikolayev/downloads,
// eventsUrl=https://api.github.com/repos/drugoi/iNikolayev/events,
// forksUrl=https://api.github.com/repos/drugoi/iNikolayev/forks,
// gitCommitsUrl=https://api.github.com/repos/drugoi/iNikolayev/git/commits{/sha},
// gitRefsUrl=https://api.github.com/repos/drugoi/iNikolayev/git/refs{/sha},
// gitTagsUrl=https://api.github.com/repos/drugoi/iNikolayev/git/tags{/sha},
// gitUrl=git://github.com/drugoi/iNikolayev.git,
// issueCommentUrl=https://api.github.com/repos/drugoi/iNikolayev/issues/comments{/number},
// issueEventsUrl=https://api.github.com/repos/drugoi/iNikolayev/issues/events{/number},
// issuesUrl=https://api.github.com/repos/drugoi/iNikolayev/issues{/number},
// keysUrl=https://api.github.com/repos/drugoi/iNikolayev/keys{/key_id},
// labelsUrl=https://api.github.com/repos/drugoi/iNikolayev/labels{/name},
// languagesUrl=https://api.github.com/repos/drugoi/iNikolayev/languages, m
// ergesUrl=https://api.github.com/repos/drugoi/iNikolayev/merges,
// milestonesUrl=https://api.github.com/repos/drugoi/iNikolayev/milestones{/number},
// notificationsUrl=https://api.github.com/repos/drugoi/iNikolayev/notifications{?since,all,participating},
// pullsUrl=https://api.github.com/repos/drugoi/iNikolayev/pulls{/number},
// releasesUrl=https://api.github.com/repos/drugoi/iNikolayev/releases{/id},
// sshUrl=git@github.com:drugoi/iNikolayev.git,
// stargazersUrl=https://api.github.com/repos/drugoi/iNikolayev/stargazers,
// statusesUrl=https://api.github.com/repos/drugoi/iNikolayev/statuses/{sha},
// subscribersUrl=https://api.github.com/repos/drugoi/iNikolayev/subscribers,
// subscriptionUrl=https://api.git
//2020-10-27 04:36:13.663 14797-14797/com.rsh_engineering.tkachenkoni.gitviewmanager D/TESTNETWORK: DetailRepoFragment setupView()
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