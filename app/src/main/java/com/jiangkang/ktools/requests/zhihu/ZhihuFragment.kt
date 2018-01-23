package com.jiangkang.ktools.requests.zhihu


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.gson.Gson
import com.jiangkang.ktools.R
import com.jiangkang.requests.KRequests
import com.jiangkang.requests.zhihu.ZhihuApi
import com.jiangkang.requests.zhihu.bean.Story
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_zhihu.*


/**
 * A simple [Fragment] subclass.
 */
class ZhihuFragment : Fragment() {

    lateinit var adapter: ZhiHuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.title = "知乎"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_zhihu, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rcZhiHu.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        val viewModel = ViewModelProviders.of(this).get(ZhihuListViewModel::class.java)

        observeViewModel(viewModel)

//        loadData()
    }

    private fun observeViewModel(viewModle: ZhihuListViewModel) {

        viewModle.storyListObservable.observe(this, Observer<ArrayList<Story>> {
            adapter = ZhiHuAdapter(it!!)
            rcZhiHu.adapter = adapter
        })
    }

    private fun loadData() {
        getLatestDailyNews()
    }

    private fun getLatestDailyNews() {
        KRequests.request(ZhihuApi::class.java)
                .latestNews
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { latestNews ->
                    run {
                        Log.d("zhihu", Gson().toJson(latestNews))
                        adapter = ZhiHuAdapter(latestNews.stories)
                        rcZhiHu.adapter = adapter
                    }
                }
    }


}
