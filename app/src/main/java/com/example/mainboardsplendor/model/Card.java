package com.example.mainboardsplendor.model;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Card extends androidx.appcompat.widget.AppCompatButton {

    private Color color;
    private int cardValue;
    private int crowns;
    //
    private ArrayList<Integer> price;

    public Card(@NonNull Context context) {
        super(context);
    }
}
