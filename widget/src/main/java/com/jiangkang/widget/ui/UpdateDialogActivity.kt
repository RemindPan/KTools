package com.jiangkang.widget.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.jiangkang.tools.utils.ToastUtils
import com.jiangkang.widget.R
import kotlinx.android.synthetic.main.activity_update_dialog.*

class UpdateDialogActivity : AppCompatActivity() {

    companion object {
        fun launch(context:Context){
            val intent = Intent(context, UpdateDialogActivity::class.java)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_update_dialog)
        initViews()

    }

    private fun initViews() {

        btn_confirm.setOnClickListener {
            ToastUtils.showShortToast("确认")
        }

    }


}
