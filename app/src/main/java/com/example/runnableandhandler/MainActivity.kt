package com.example.runnableandhandler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.runnableandhandler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var number = 0
    private var runnable : Runnable = Runnable {  }
    private var handler : Handler = Handler(Looper.getMainLooper()) // Handler helps Runnable to run.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun start(view: View) {
        number = 0

        runnable = object : Runnable {
            override fun run() {
                number = number + 1
                binding.textView.text = "Time: $number"

                handler.postDelayed(this,1000)
            }
        }

        handler.post(runnable)
        binding.startBtn.isEnabled = false
    }

    fun stop(view: View) {
        handler.removeCallbacks(runnable)
        number = 0
        binding.textView.text = "Time: 0"
        binding.startBtn.isEnabled = true
    }
}
