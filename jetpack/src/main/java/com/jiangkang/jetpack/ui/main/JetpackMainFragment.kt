package com.jiangkang.jetpack.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jiangkang.jetpack.R
import kotlinx.android.synthetic.main.jetpack_main_fragment.*
import org.jetbrains.anko.sdk25.coroutines.onClick

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

        btn_work_manager?.onClick {
            Navigation.findNavController(it!!).navigate(R.id.action_jetpack_main_to_work_manager)
        }

        btn_paging_demo?.onClick {
            Navigation.findNavController(it!!).navigate(R.id.action_jetpack_fragment_main_to_paging_demo_fragment)
        }

        btn_github_trend.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_jetpack_fragment_main_to_githubTrendFragment))

    }

}
