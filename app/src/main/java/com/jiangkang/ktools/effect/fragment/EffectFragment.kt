package com.jiangkang.ktools.effect.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jiangkang.design.lottie.LottieDemoActivity
import com.jiangkang.ktools.R
import com.jiangkang.tools.widget.KDialog
import com.jiangkang.widget.ui.SlideDemosFragment
import com.jiangkang.widget.view.TaiChiView
import kotlinx.android.synthetic.main.fragment_effect.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

/**
 * 自定义View相关的Demo
 */
class EffectFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_effect, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleViewClicked()
    }

    private fun handleViewClicked() {

        effect_shape.onClick {
            handleClick(ShapeViewFragment())
        }

        tai_chi.onClick {
            val taiChiView = TaiChiView(this@EffectFragment.activity)
            KDialog.showCustomViewDialog(this@EffectFragment.activity, "太极图", taiChiView, { dialog, which -> dialog.dismiss() }) { dialog, which -> dialog.dismiss() }
            taiChiView.startRotate()
        }

        animated_shape_view.onClick {
            handleClick(AnimatedShapeViewFragment())
        }

        shape_path_view.onClick {
            handleClick(ShapePathViewFragment())
        }

        btnSlideDemos.onClick {
            handleClick(SlideDemosFragment())
        }

        btnLottie.onClick {
            this@EffectFragment.activity?.startActivity<LottieDemoActivity>()
        }

    }

    private fun handleClick(fragment: Fragment) {
        val tag = fragment.javaClass.toString()

        this.activity?.apply {
            supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(tag)
                    .replace(android.R.id.content, fragment, tag)
                    .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
