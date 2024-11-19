package com.example.mainboardsplendor.model;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Card extends androidx.appcompat.widget.AppCompatImageView {

    private Color color;
    private int cardValue;
    private int discount;
    private int crowns;
    private int level;
    //
    private ArrayList<Integer> price;

    public Card(@NonNull Context context) {
        super(context);
    }

    public Card(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Card(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCardValue() {
        return cardValue;
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    public int getCrowns() {
        return crowns;
    }

    public void setCrowns(int crowns) {
        this.crowns = crowns;
    }

    public ArrayList<Integer> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<Integer> price) {
        this.price = price;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
