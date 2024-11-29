package com.example.mainboardsplendor.model;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Token extends androidx.appcompat.widget.AppCompatImageView {

    private Color color;
    private ArrayList<Integer> location;
    private Boolean isSelected = Boolean.FALSE;
    private Boolean isValid = Boolean.TRUE;

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

    public ArrayList<Integer> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Integer> location) {
        this.location = location;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean selected) {
        isSelected = selected;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

}
