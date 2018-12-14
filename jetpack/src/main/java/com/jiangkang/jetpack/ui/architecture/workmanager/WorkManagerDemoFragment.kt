package com.jiangkang.jetpack.ui.architecture.workmanager

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jiangkang.jetpack.R
import kotlinx.android.synthetic.main.work_manager_demo_fragment.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class WorkManagerDemoFragment : Fragment() {

    companion object {
        fun newInstance() = WorkManagerDemoFragment()
    }

    private lateinit var viewModel: WorkManagerDemoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.work_manager_demo_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WorkManagerDemoViewModel::class.java)
        // TODO: Use the ViewModel

        btn_typical?.onClick {
            viewModel.downloadWhenWifi()
        }
    }

}
