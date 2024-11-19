package com.example.mainboardsplendor.model;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RoyalCard extends androidx.appcompat.widget.AppCompatImageView {

    private int points;
    private int crowns;
    private Image imageRoyalCard;

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

    public Image getImageRoyalCard() {return imageRoyalCard;}

    public void setImageRoyalCard(Image imageRoyalCard) {this.imageRoyalCard = imageRoyalCard;}
}
