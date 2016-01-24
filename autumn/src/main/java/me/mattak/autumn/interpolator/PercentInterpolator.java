package me.mattak.autumn.interpolator;

import android.view.animation.Interpolator;

/**
 * PercentInterpolator
 * Created by mattak on 2016/01/23.
 */
public class PercentInterpolator implements Interpolator {
    private final Interpolator mOriginalInterpolator;
    private final int mStartMolecule;
    private final int mEndMolecule;
    private final int mDenominator;
    final float mScale;
    final float mStartOffset;

    public PercentInterpolator(
            Interpolator originalInterpolator,
            int molecule,
            int denominator
    ) {
        this(originalInterpolator, molecule, molecule + 1, denominator);
    }

    public PercentInterpolator(
            Interpolator originalInterpolator,
            int startMolecule,
            int endMolecule,
            int denominator
    ) {
        this.mOriginalInterpolator = originalInterpolator;
        this.mStartMolecule = startMolecule;
        this.mEndMolecule = endMolecule;
        this.mDenominator = denominator;
        this.mScale = (float) (mEndMolecule - mStartMolecule) / mDenominator;
        this.mStartOffset = (float) mStartMolecule / mDenominator;

        if (this.mEndMolecule > denominator || this.mEndMolecule <= 0) {
            throw new IllegalArgumentException("End Molecule is out of range");
        }

        if (this.mStartMolecule >= denominator || this.mStartMolecule < 0) {
            throw new IllegalArgumentException("Start Molecule is out of range");
        }
    }

    @Override
    public float getInterpolation(float input) {
        final float mappedInput = input * mScale + mStartOffset;
        return mOriginalInterpolator.getInterpolation(mappedInput);
    }
}