package com.jiangkang.jetpack.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jiangkang.jetpack.R
import com.jiangkang.jetpack.adapter.GithubTrendAdapter
import com.jiangkang.jetpack.repository.GithubRepository
import com.jiangkang.jetpack.viewmodel.GithubTrendViewModel
import com.jiangkang.jetpack.viewmodel.GithubTrendViewModelFactory
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.toast

class GithubTrendFragment : Fragment() {

    companion object {
        fun newInstance() = GithubTrendFragment()
    }

    private lateinit var viewModel: GithubTrendViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.github_trend_fragment, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, GithubTrendViewModelFactory(GithubRepository())).get(GithubTrendViewModel::class.java)

        val adapter = GithubTrendAdapter(context!!)

        rc_trend_list?.layoutManager = LinearLayoutManager(context)
        rc_trend_list.adapter = adapter

        subscribeUi(adapter)


    }

    private fun subscribeUi(adapter: GithubTrendAdapter) {

        viewModel.getGithubTrendList().observe(this,
                Observer { item ->
                    if (item == null) {
                        UI {
                            loadingProgressBar.visibility = View.VISIBLE
                            toast("正在请求数据，请耐心等待！")
                        }
                    } else {
                        loadingProgressBar.visibility = View.GONE
                        adapter.submitList(item)
                    }
                })


    }

}
