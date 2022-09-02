package com.github.coaixy.dyparse

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.VideoView
import androidx.annotation.RestrictTo
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.e2)
        val videoView = findViewById<VideoView>(R.id.videoView3)
        button.setOnClickListener(){
            thread(start=true){
                val p = Parse(editText.text.toString())
                val url = p .trueUrl
                runOnUiThread {
                    videoView.setVideoPath(url)
                    videoView.start()//播放视频
                }
                val manager = baseContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                manager.setPrimaryClip(ClipData.newPlainText("url",url))
                Toast.makeText(baseContext, "真实链接已复制", Toast.LENGTH_SHORT).show()
            }

        }
    }
}