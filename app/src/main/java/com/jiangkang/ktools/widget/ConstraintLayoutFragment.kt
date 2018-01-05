package com.jiangkang.ktools.widget


import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jiangkang.ktools.R
import kotlinx.android.synthetic.main.fragment_constraint_layout.*


/**
 * A simple [Fragment] subclass.
 */
class ConstraintLayoutFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_constraint_layout, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addAnimationOperations()
    }

    private fun addAnimationOperations() {
        var set = false
        val constraint1 = ConstraintSet()
        constraint1.clone(constraint_root)
        val constraint2 = ConstraintSet()
        constraint2.clone(context, R.layout.activity_constraint_layout_img_alt)

        iv_constraint.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraint_root)
            val constraint = if (set) constraint1 else constraint2
            constraint.applyTo(constraint_root)
            set = !set
        }

    }


}
