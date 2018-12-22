package com.jiangkang.ktools.effect.fragment

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.jiangkang.container.fragment.ViewDataBinder
import com.jiangkang.container.loadFragment
import com.jiangkang.design.lottie.LottieDemoActivity
import com.jiangkang.ktools.R
import com.jiangkang.tools.device.screenSize
import com.jiangkang.tools.device.screenWidth
import com.jiangkang.tools.utils.ToastUtils
import com.jiangkang.tools.widget.KDialog
import com.jiangkang.widget.view.TaiChiView
import kotlinx.android.synthetic.main.fragment_effect.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.startActivity


val colors = arrayOf(
        Color.RED,
        Color.BLUE,
        Color.GREEN,
        Color.CYAN,
        Color.DKGRAY,
        Color.GRAY,
        Color.LTGRAY,
        Color.MAGENTA,
        Color.YELLOW
)

/**
 * 动效相关Demo
 */
class EffectFragment : Fragment() {

    private lateinit var mContext: Context

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

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

        effect_shape.setOnClickListener {
            handleClick(ShapeViewFragment())
        }

        tai_chi.setOnClickListener {
            val taiChiView = TaiChiView(this@EffectFragment.activity)
            KDialog.showCustomViewDialog(this@EffectFragment.activity, "太极图", taiChiView, { dialog, which -> dialog.dismiss() }) { dialog, which -> dialog.dismiss() }
            taiChiView.startRotate()
        }

        animated_shape_view.setOnClickListener {
            handleClick(AnimatedShapeViewFragment())
        }

        shape_path_view.setOnClickListener {
            handleClick(ShapePathViewFragment())
            val (x, y) = mContext.screenSize
        }

        btnAutoLayoutAnimation.setOnClickListener {
            activity?.loadFragment(
                    R.layout.fragment_auto_animation_layout,
                    "Auto Animation Layout Updates",
                    object : ViewDataBinder {
                        @SuppressLint("ObjectAnimatorBinding")
                        override fun bindView(view: View) {
                            if (view is ViewGroup) {
                                view.layoutTransition = LayoutTransition().apply {
                                    val screenWidth = mContext.screenWidth.toFloat()
                                    setAnimator(
                                            LayoutTransition.APPEARING,
                                            ObjectAnimator.ofFloat(null, "translationX", screenWidth, 0f))
                                    setDuration(LayoutTransition.APPEARING, 1000)

                                    setAnimator(
                                            LayoutTransition.DISAPPEARING,
                                            ObjectAnimator.ofFloat(null, "translationX", 0f, -screenWidth))
                                    setDuration(LayoutTransition.DISAPPEARING, 1000)

                                }
                                val btnAdd = view.findViewById<Button>(R.id.btn_add_view)
                                val btnRemove = view.findViewById<Button>(R.id.btn_remove_view)
                                val layoutParams = LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                ).apply {
                                    gravity = Gravity.CENTER_HORIZONTAL
                                    topMargin = 6
                                }
                                btnAdd.setOnClickListener {
                                    view.addView(Button(view.context).apply {
                                        text = view.childCount.toString()
                                        gravity = Gravity.CENTER
                                        backgroundColor = colors[IntRange(0, colors.size - 1).random()]
                                        setOnClickListener { ToastUtils.showShortToast(this@apply.text.toString()) }
                                    }, layoutParams)
                                }
                                btnRemove.setOnClickListener {
                                    if (view.childCount > 1) {
                                        view.removeViewAt(view.childCount - 1)
                                    } else {
                                        ToastUtils.showShortToast("没有View可以移除咯！")
                                    }
                                }
                            }
                        }

                    }
            )
        }

        btnLottie.setOnClickListener {
            this@EffectFragment.activity?.startActivity<LottieDemoActivity>()
        }

        btnVectorDrawable.setOnClickListener {
            activity?.loadFragment(
                    R.layout.fragment_vector_drawable,
                    "Vector Drawable",
                    object : ViewDataBinder {
                        override fun bindView(view: View) {
                            val drawable = view.findViewById<ImageView>(R.id.iv_animated_vector_drawable).drawable as AnimatedVectorDrawable
                            drawable.start()
                        }
                    })
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
