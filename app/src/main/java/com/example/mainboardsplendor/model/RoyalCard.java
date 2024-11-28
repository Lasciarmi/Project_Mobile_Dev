package com.example.mainboardsplendor.model;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RoyalCard extends androidx.appcompat.widget.AppCompatImageView {

    private int points;
    private int crowns;

    private int image;



    public RoyalCard(@NonNull Context context) {
        super(context);
    }

    public RoyalCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RoyalCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCrowns() {
        return crowns;
    }

    public void setCrowns(int crowns) {
        this.crowns = crowns;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
