package com.yondikavl.aritmateka

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.yondikavl.aritmateka.util.setupDialog
import kotlin.system.exitProcess

class WelcomeActivity : AppCompatActivity() {
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressedMethod()
        }
    }

    private val exitDialog : Dialog by lazy {
        Dialog(this, R.style.DialogCustomTheme).apply {
            setupDialog(R.layout.game_exit_dialog)
        }
    }
    private val tickMusic: MediaPlayer by lazy {
        MediaPlayer.create(this, R.raw.tick)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)

        splashScreen.setKeepOnScreenCondition { false }

        val startBtn = findViewById<Button>(R.id.startBtn)
        startBtn.setOnClickListener {
            tickMusic.start()
            startActivity(Intent(this, LevelActivity::class.java))
        }


        val yesBtn : Button = exitDialog.findViewById(R.id.yesBtn)
        val noBtn : Button = exitDialog.findViewById(R.id.noBtn)
        yesBtn.setOnClickListener {
            tickMusic.start()
            moveTaskToBack(true)
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(1)
        }
        noBtn.setOnClickListener {
            tickMusic.start()
            exitDialog.dismiss()
        }

    }
    private fun onBackPressedMethod() {
        exitDialog.show()
    }
}