package me.mattak.autumn.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import me.mattak.autumn.AnimatorPreset
import me.mattak.autumn.InterpolatorPreset

class MainActivity : AppCompatActivity() {
    val squareView: View by lazy { findViewById(R.id.square) }
    val animatorSpinner: Spinner by lazy { findViewById(R.id.spinner_animator) as Spinner }
    val interpolatorSpinner: Spinner by lazy { findViewById(R.id.spinner_interpolator) as Spinner }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animatorSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, AnimatorPreset.values().map { it.name })
        interpolatorSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, InterpolatorPreset.values().map { it.name })

        squareView.setOnClickListener {
            animate(squareView, animatorSpinner.selectedItem.toString(), interpolatorSpinner.selectedItem.toString())
        }
        animatorSpinner.onItemSelectedListener = createOnItemSelectedListener()
        interpolatorSpinner.onItemSelectedListener = createOnItemSelectedListener()
    }

    private fun createOnItemSelectedListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                animate(squareView, animatorSpinner.selectedItem.toString(), interpolatorSpinner.selectedItem.toString())
            }
        }
    }

    private fun animate(square: View, animatorName: String, interpolatorName: String) {
        val force = 1.0f
        val duration = 1000L
        val delay = 0L
        val animatorPreset = AnimatorPreset.valueOf(animatorName)
        val interpolatorPreset = InterpolatorPreset.valueOf(interpolatorName)
        val interpolator = interpolatorPreset.create(force)
        val animator = animatorPreset.createAnimator(square, force, duration, delay, interpolator).create()
        animator.start()
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