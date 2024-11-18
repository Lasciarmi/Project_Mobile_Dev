package com.example.mainboardsplendor.model;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Token extends androidx.appcompat.widget.AppCompatImageView {

    private Color color;

    public Token(@NonNull Context context) {
        super(context);
    }

    public Token(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Token(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
