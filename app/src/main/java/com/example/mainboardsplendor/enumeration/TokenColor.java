package com.example.mainboardsplendor.enumeration;

import android.content.Context;
import android.graphics.Color;

import androidx.core.content.ContextCompat;

import com.example.mainboardsplendor.R;

public enum TokenColor {
    BLUE(R.color.color4blueToken),
    WHITE(R.color.white),
    GREEN(R.color.color4greenToken),
    BLACK(R.color.black),
    RED(R.color.color4redToken),
    PEARL(R.color.color4pearlToken),
    GOLD(R.color.color4goldToken),
    FIRST_PLAYER(R.color.first_player);

    private final int colorResId;

    // Constructor name must match the enum name (TokenColor)
    TokenColor(int colorResId) {
        this.colorResId = colorResId;
    }

    // Corrected method to return a Color object
    public Color getTokenColor(Context context) {
        int colorInt = ContextCompat.getColor(context, colorResId); // Retrieve color as an int
        return Color.valueOf(colorInt); // Convert int to Color object
    }
    public Integer getTokenColorInt(Context context) {
        // Retrieve color as an int
        return ContextCompat.getColor(context, colorResId); // Convert int to Color object
    }
}
