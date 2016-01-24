package me.mattak.autumn.interpolator;

import android.view.animation.Interpolator;

/**
 * KeyframeInterpolator
 * Created by mattak on 2016/01/24.
 */
public class KeyframeInterpolator implements Interpolator {
    private float[] mFrames;
    private float[] mValues;
    private int previousIndex = 0;

    public KeyframeInterpolator(float[] frames, float[] values) {
        if (frames.length != values.length) {
            throw new IllegalArgumentException("frames and values should be same");
        }

        if (frames.length < 2) {
            throw new IllegalArgumentException("frame size should be greater than 1");
        }

        if (frames[0] != 0.0f) {
            throw new IllegalArgumentException("frame should start 0.0f");
        }

        if (frames[frames.length - 1] != 1.0f) {
            throw new IllegalArgumentException("frame should be end with 1.0f");
        }

        for (int i = 0; i < frames.length - 1; i++) {
            if (frames[i] >= frames[i + 1]) {
                throw new IllegalArgumentException("frames should increment order.");
            }
        }

        this.mFrames = frames;
        this.mValues = values;
        this.previousIndex = 0;
    }

    @Override
    public float getInterpolation(float input) {
        android.util.Log.d("tag", "input: " + input);
        final int index = searchIndex(input); // XXX: may fail
        if (index == -1) {
            throw new IllegalStateException("input should be between 0.0f to 1.0f");
        }

        float sx = this.mFrames[index];
        float ex = this.mFrames[index + 1];
        float sy = this.mValues[index];
        float ey = this.mValues[index + 1];
        float height = ey - sy;
        float width = ex - sx;
        float diff = input - sx;

        return sy + height * diff / width;
    }

    private int incrementalSearchIndex(float input) {
        if (this.previousIndex >= this.mFrames.length - 1) {
            this.previousIndex = 0;
            return searchIndex(input);
        }

        if (this.mFrames[this.previousIndex] <= input && input <= this.mFrames[this.previousIndex + 1]) {
            return this.previousIndex;
        }

        this.previousIndex++;
        return incrementalSearchIndex(input);
    }

    private int searchIndex(float input) {
        for (int i = 0; i < this.mFrames.length - 1; i++) {
            if (this.mFrames[i] <= input && input <= this.mFrames[i + 1]) {
                return i;
            }
        }

        return -1;
    }
}