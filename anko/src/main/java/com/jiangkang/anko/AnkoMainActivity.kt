package com.jiangkang.anko

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import org.jetbrains.anko.verticalLayout

class AnkoMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.anko_main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, AnkoMainFragment.newInstance())
//                    .commitNow()
//        }

        verticalLayout {

            button {
                text = "测试"
                onClick {
                    toast("clicked!")
                }
            }


        }

    }

}
