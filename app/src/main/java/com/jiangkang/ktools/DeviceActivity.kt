package com.jiangkang.ktools

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jiangkang.tools.device.DeviceUtils
import com.jiangkang.tools.utils.AppUtils
import com.jiangkang.tools.utils.NetworkUtils
import com.jiangkang.tools.utils.ShellUtils
import com.jiangkang.tools.widget.KDialog
import kotlinx.android.synthetic.main.activity_device.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onClick

class DeviceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)
        title = "Device"
        handleClick()
    }

    private fun handleClick() {

        btnCheckNetworkInfo.onClick {
            val builder = StringBuilder()
            builder.append(String.format("网络类型: %s\n", NetworkUtils.getNetWorkType()))
                    .append(String.format("Mac地址: %s\n", NetworkUtils.getMacAddress()))
            KDialog.showMsgDialog(this@DeviceActivity, builder.toString())

        }

        btnCheckCurrentActivity.onClick {
            KDialog.showMsgDialog(this@DeviceActivity, AppUtils.currentActivity)
        }


        btnAdbWireless.onClick {
            doAsync {
                val command = "setprop service.adb.tcp.port 5555 && stop adbd && start adbd"
                val result = ShellUtils.execCmd(command, true)
                runOnUiThread {
                    val ip = DeviceUtils.getIPAddress(this@DeviceActivity)
                    KDialog.showMsgDialog(this@DeviceActivity, "adb connect " + ip!!)
                }
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
    }


    companion object {
        private val TAG = DeviceActivity::class.java.simpleName
    }


}
