package me.mattak.autumn.animator;

import android.animation.Animator;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;

/**
 * AnimatorOnEndListener
 * Created by mattak on 2016/01/23.
 */
public class AnimatorOnEndListener implements ViewPropertyAnimatorListener, Animator.AnimatorListener {
    private final Runnable mEndRunner;

    AnimatorOnEndListener(Runnable endRunner) {
        this.mEndRunner = endRunner;
    }

    @Override
    public void onAnimationStart(View view) {
    }

    @Override
    public void onAnimationEnd(View view) {
        if (this.mEndRunner != null) {
            this.mEndRunner.run();
        }
    }

    @Override
    public void onAnimationCancel(View view) {
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (this.mEndRunner != null) {
            this.mEndRunner.run();
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}