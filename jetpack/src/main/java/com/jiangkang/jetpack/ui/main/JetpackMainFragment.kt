package com.jiangkang.jetpack.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jiangkang.jetpack.R

class JetpackMainFragment : Fragment() {

    companion object {
        fun newInstance() = JetpackMainFragment()
    }

    private lateinit var viewModel: JetpackMainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.jetpack_main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(JetpackMainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
