package me.mattak.autumn.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import me.mattak.autumn.AnimatorPreset
import me.mattak.autumn.InterpolatorPreset

class MainActivity : AppCompatActivity() {
    val squareView: View by lazy { findViewById(R.id.square) }
    val animatorSpinner: Spinner by lazy { findViewById(R.id.spinner_animator) as Spinner }
    val interpolatorSpinner: Spinner by lazy { findViewById(R.id.spinner_interpolator) as Spinner }
    val forceSeekBar: SeekBar by lazy { findViewById(R.id.seekbar_force) as SeekBar }
    val durationSeekBar: SeekBar by lazy { findViewById(R.id.seekbar_duration) as SeekBar }
    val delaySeekBar: SeekBar by lazy { findViewById(R.id.seekbar_delay) as SeekBar }
    val forceTextView: TextView by lazy { findViewById(R.id.value_force) as TextView }
    val durationTextView: TextView by lazy { findViewById(R.id.value_duration) as TextView }
    val delayTextView: TextView by lazy { findViewById(R.id.value_delay) as TextView }
    var force: Float = 1.0f
    var duration: Long = 1000L
    var delay: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: refactoring
        animatorSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, AnimatorPreset.values().map { it.name })
        interpolatorSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, InterpolatorPreset.values().map { it.name })

        squareView.setOnClickListener { animate() }
        animatorSpinner.onItemSelectedListener = createOnItemSelectedListener()
        interpolatorSpinner.onItemSelectedListener = createOnItemSelectedListener()
        val forceAdjust: (SeekBar) -> Unit = {
            force = it.progress.toFloat() / 100.0f
            forceTextView.text = force.toString()
        }
        val durationAdjust: (SeekBar) -> Unit = {
            duration = it.progress.toLong()
            durationTextView.text = duration.toString()
        }
        val delayAdjust: (SeekBar) -> Unit = {
            delay = it.progress.toLong()
            delayTextView.text = delay.toString()
        }
        forceSeekBar.setOnSeekBarChangeListener(this.createOnSeekBarChangeListener(forceAdjust))
        durationSeekBar.setOnSeekBarChangeListener(this.createOnSeekBarChangeListener(durationAdjust))
        delaySeekBar.setOnSeekBarChangeListener(this.createOnSeekBarChangeListener(delayAdjust))
        forceAdjust(forceSeekBar)
        durationAdjust(durationSeekBar)
        delayAdjust(delaySeekBar)
    }

    private fun createOnSeekBarChangeListener(runner: (SeekBar) -> Unit): SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBar?.let { runner(it) }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.let { runner(it) }
                animate()
            }
        }
    }

    private fun createOnItemSelectedListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                animate()
            }
        }
    }

    private fun animate() {
        val animatorName: String = animatorSpinner.selectedItem.toString()
        val interpolatorName: String = interpolatorSpinner.selectedItem.toString()
        val animatorPreset = AnimatorPreset.valueOf(animatorName)
        val interpolatorPreset = InterpolatorPreset.valueOf(interpolatorName)
        val interpolator = interpolatorPreset.create(force)
        val animator = animatorPreset.createAnimator(squareView, force, duration, delay, interpolator).create()
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