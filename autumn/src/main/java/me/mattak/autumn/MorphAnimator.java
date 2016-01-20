package me.mattak.autumn;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * MorphAnimator
 * Created by mattak on 2016/01/20.
 */
public class MorphAnimator implements AnimatorFactory {
    private final View view;
    private final float force;
    private final long duration;
    private final long delay;
    private final Interpolator interpolator;

    public MorphAnimator(
            View view,
            float force,
            long duration,
            long delay,
            Interpolator interpolator
    ) {
        this.view = view;
        this.force = force;
        this.duration = duration;
        this.delay = delay;
        this.interpolator = interpolator;
    }

    @Override
    public Animator create() {
        float maxScale = 1.5f * this.force;
        float minScale = 0.5f;
        long stepInterval = this.duration / 6;

        final ObjectAnimator biggerX = ObjectAnimator.ofFloat(this.view, "scaleX", 1.0f, maxScale);
        biggerX.setStartDelay(this.delay);
        biggerX.setInterpolator(new LinearInterpolator());
        biggerX.setDuration(stepInterval);

        final ObjectAnimator repeatX = ObjectAnimator.ofFloat(this.view, "scaleX", maxScale, minScale);
        repeatX.setInterpolator(new AccelerateDecelerateInterpolator());
        repeatX.setDuration(stepInterval * 4);
        repeatX.setRepeatCount(1);
        repeatX.setRepeatMode(ValueAnimator.REVERSE);

        final ObjectAnimator smallerX = ObjectAnimator.ofFloat(this.view, "scaleX", maxScale, 1.0f);
        smallerX.setInterpolator(new AccelerateDecelerateInterpolator());
        smallerX.setDuration(stepInterval);

        final ObjectAnimator smallerY = ObjectAnimator.ofFloat(this.view, "scaleY", 1.0f, minScale);
        smallerY.setInterpolator(new DecelerateInterpolator());
        smallerY.setDuration(stepInterval);

        final ObjectAnimator repeatY  = ObjectAnimator.ofFloat(this.view, "scaleY", minScale, 1.0f);
        repeatY.setInterpolator(new AccelerateDecelerateInterpolator());
        repeatY.setDuration(stepInterval * 4);
        repeatY.setRepeatCount(1);
        repeatY.setRepeatMode(ValueAnimator.REVERSE);

        final ObjectAnimator biggerY = ObjectAnimator.ofFloat(this.view, "scaleY", minScale, 1.0f);
        biggerY.setInterpolator(new AccelerateDecelerateInterpolator());
        biggerY.setDuration(stepInterval);

        final AnimatorSet set = new AnimatorSet();
        set.play(biggerX).before(repeatX);
        set.play(repeatX).before(smallerX);
        set.play(biggerX).with(smallerY);
        set.play(repeatX).with(repeatY);
        set.play(smallerX).with(biggerY);
        set.setInterpolator(this.interpolator);

        return set;
    }
}
