package com.jiangkang.jetpack.ui.architecture.paging


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jiangkang.jetpack.R
import com.jiangkang.requests.github.entity.User
import kotlinx.android.synthetic.main.jetpack_fragment_paging.*


class PagingDemoFragment : Fragment() {

    companion object {
        fun newInstance() = PagingDemoFragment()
    }

    private lateinit var viewModel:UsersViewModel

    private lateinit var userAdapter: UserAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.jetpack_fragment_paging, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)

        initAdapter()
        initSwipeToRefresh()

    }



    private fun initAdapter() {
        val linearLayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        userAdapter = UserAdapter {
            viewModel.retry()
        }
        usersRecyclerView.layoutManager = linearLayoutManager
        usersRecyclerView.adapter = userAdapter
        viewModel.userList.observe(this, Observer<PagedList<User>> { userAdapter.submitList(it) })
        viewModel.getNetworkState().observe(this, Observer<NetworkState> { userAdapter.setNetworkState(it) })

    }

    /**
     * Init swipe to refresh and enable pull to refresh only when there are items in the adapter
     */
    private fun initSwipeToRefresh() {
        viewModel.getRefreshState().observe(this, Observer { networkState ->
            if (userAdapter.currentList != null) {
                if (userAdapter.currentList!!.size > 0) {
                    usersSwipeRefreshLayout.isRefreshing = networkState?.status == NetworkState.LOADING.status
                } else {
                    setInitialLoadingState(networkState)
                }
            } else {
                setInitialLoadingState(networkState)
            }
        })
        usersSwipeRefreshLayout.setOnRefreshListener({ viewModel.refresh() })
    }

    /**
     * Show the current network state for the first load when the user list
     * in the adapter is empty and disable swipe to scroll at the first loading
     *
     * @param networkState the new network state
     */
    private fun setInitialLoadingState(networkState: NetworkState?) {
        //error message
        errorMessageTextView.visibility = if (networkState?.message != null) View.VISIBLE else View.GONE
        if (networkState?.message != null) {
            errorMessageTextView.text = networkState.message
        }

        //loading and retry
        retryLoadingButton.visibility = if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        loadingProgressBar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE

        usersSwipeRefreshLayout.isEnabled = networkState?.status == Status.SUCCESS
        retryLoadingButton.setOnClickListener { viewModel.retry() }
    }



}
