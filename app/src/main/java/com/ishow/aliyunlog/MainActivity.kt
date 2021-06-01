package com.ishow.aliyunlog

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.ishow.library_aliyunlog.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 根据https://github.com/aliyun/aliyun-log-android-sdk/issues/60
        // 确实存在anr问题，暂时只能放弃部分日志
        Log.init(this,
            "ai-class",
            "ai-class",
            "AliyunLogTest",
            {
                //it.logProducerDebug()
                // 设置tag信息，此tag会附加在每条日志上，默认只有一条 __client_ip__:36.27.84.11
                it.addTag("Version", BuildConfig.VERSION_NAME)
                it.addTag("Env", BuildConfig.BUILD_TYPE)
                it.addTag("VersionCode", "${BuildConfig.VERSION_CODE}")
            },
            {
                it.putContent("ABC", "DEF")
            },
            {
                try {
                    Util.getSTS()
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            })

        // ---
        initView()
    }

    private fun initView() {
        var i = 1
        findViewById<AppCompatButton>(R.id.b_print).setOnClickListener {
            Log.w("print: ${i++}")
        }
        findViewById<AppCompatButton>(R.id.b_print2).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.i("object : View.OnClickListener")
            }
        })
        findViewById<AppCompatButton>(R.id.b_init).setOnClickListener {
            //Log.updateToken()
        }
    }
}