package com.lakshyagupta7089.androidstopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.lakshyagupta7089.androidstopwatch.databinding.ActivityStopBinding

class StopWatchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStopBinding
    private lateinit var roundingalone: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityStopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone)

        binding.btnStart.setOnClickListener {
            binding.icAcnhor.startAnimation(roundingalone)

            binding.btnStart.animate()
                .alpha(0F)
                .setDuration(300)
                .start()
            binding.btnStop.animate()
                .alpha(1F)
                .translationY(-80f)
                .setDuration(300)
                .start()
            binding.timeHere.base = SystemClock.elapsedRealtime()
            binding.timeHere.start()

            Handler(Looper.myLooper()!!).postDelayed({
                binding.btnStop.visibility = View.VISIBLE
                binding.btnStart.visibility = View.INVISIBLE
            }, 300)
        }

        binding.btnStop.setOnClickListener {
            binding.icAcnhor.animation = null

            binding.btnStart.animate()
                .alpha(1F)
                .setDuration(300)
                .start()
            binding.btnStop.animate()
                .alpha(0f)
                .translationY(+80f)
                .setDuration(300)
                .start()

            binding.timeHere.stop()

            Handler(Looper.myLooper()!!).postDelayed({
                binding.btnStop.visibility = View.INVISIBLE
                binding.btnStart.visibility = View.VISIBLE
            }, 300)
        }

        binding.btnStop.alpha = 0f
    }
}