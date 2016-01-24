package me.mattak.autumn.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.LinearInterpolator
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import me.mattak.autumn.AnimatorPreset

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val square = findViewById(R.id.square)
        val spinner = findViewById(R.id.spinner_animator) as Spinner
        val buttonAnimator = findViewById(R.id.button_animator) as Button

        spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, AnimatorPreset.values().map { it.name })

        buttonAnimator.setOnClickListener {
            val itemName = spinner.selectedItem.toString()
            val animatorPreset = AnimatorPreset.valueOf(itemName)
            val animator = animatorPreset.createAnimator(square, 1.0f, 1000L, 0L, LinearInterpolator()).create()
            animator.start()
        }
    }

    class CustomAnimatorListener : Runnable {
        var start: Long = 0

        init {
            start = System.currentTimeMillis()
        }

        override fun run() {
            android.util.Log.d("tag", "animator end: ${System.currentTimeMillis() - start}")
        }
    }

    class ViewListener : Runnable {
        val start: Long
        var ended: Boolean

        init {
            start = System.currentTimeMillis()
            ended = false
        }

        override fun run() {
            if (!ended) {
                this.ended = true
                android.util.Log.d("tag", "view end: ${System.currentTimeMillis() - start} from: ${start}")
            }
        }
    }
}