package com.github.coaixy.dyparse

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var url = ""
        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val editText = findViewById<EditText>(R.id.e2)
        val videoView = findViewById<VideoView>(R.id.videoView3)
        button.setOnClickListener(){
            thread(start=true){
                val p = Parse(editText.text.toString())
                url = p .trueUrl
                runOnUiThread {
                    videoView.setVideoPath(url)
                    videoView.start()//播放视频
                    Toast.makeText(baseContext, "真实链接已复制", Toast.LENGTH_SHORT).show()
                }
                val manager = baseContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                manager.setPrimaryClip(ClipData.newPlainText("url",url))
            }
        }
        button2.setOnClickListener(){
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}