package me.mattak.autumn;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * PopAnimator
 * Created by mattak on 2016/01/20.
 */
public class PopAnimator implements AnimatorFactory {
    private final View view;
    private final float force;
    private final long duration;
    private final long delay;

    public PopAnimator(
            View view,
            float force,
            long duration,
            long delay
    ) {
        this.view = view;
        this.force = force;
        this.duration = duration;
        this.delay = delay;
    }

    @Override
    public Animator create() {
        long stepInterval = this.duration / 10;
        float maxScale = 1.2f * force;
        float minScale = 0.8f;

        final ObjectAnimator biggerX = ObjectAnimator.ofFloat(this.view, "scaleX", 1.0f, maxScale);
        biggerX.setStartDelay(this.delay);
        biggerX.setInterpolator(new DecelerateInterpolator());
        biggerX.setDuration(stepInterval * 3);

        final ObjectAnimator biggerY = ObjectAnimator.ofFloat(this.view, "scaleY", 1.0f, minScale);
        biggerY.setStartDelay(this.delay);
        biggerY.setInterpolator(new DecelerateInterpolator());
        biggerY.setDuration(stepInterval * 3);

        final ObjectAnimator repeatX = ObjectAnimator.ofFloat(this.view, "scaleX", maxScale, minScale);
        repeatX.setInterpolator(new AccelerateDecelerateInterpolator());
        repeatX.setDuration(stepInterval * 4);
        repeatX.setRepeatCount(1);
        repeatX.setRepeatMode(ValueAnimator.REVERSE);

        final ObjectAnimator repeatY = ObjectAnimator.ofFloat(this.view, "scaleY", maxScale, minScale);
        repeatY.setInterpolator(new AccelerateDecelerateInterpolator());
        repeatY.setDuration(stepInterval * 4);
        repeatY.setRepeatCount(1);
        repeatY.setRepeatMode(ValueAnimator.REVERSE);

        final ObjectAnimator smallerX = ObjectAnimator.ofFloat(this.view, "scaleX", maxScale, 1.0f);
        smallerX.setInterpolator(new AccelerateDecelerateInterpolator());
        smallerX.setDuration(stepInterval * 3);

        final ObjectAnimator smallerY = ObjectAnimator.ofFloat(this.view, "scaleY", maxScale, 1.0f);
        smallerY.setInterpolator(new AccelerateDecelerateInterpolator());
        smallerY.setDuration(stepInterval * 3);

        final AnimatorSet set = new AnimatorSet();
        set.play(biggerX).with(biggerY);
        set.play(repeatX).with(repeatY);
        set.play(smallerX).with(smallerY);
        set.play(biggerY).before(repeatX);
        set.play(repeatY).before(smallerX);
        return set;
    }
}