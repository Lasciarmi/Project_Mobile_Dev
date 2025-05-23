package com.example.mainboardsplendor.model;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mainboardsplendor.enumeration.TokenColor;

import java.util.HashMap;

public class Card extends androidx.appcompat.widget.AppCompatImageView {

    private Color color;
    private int cardValue;
    private int discount;
    private int crowns;
    private int level;
    private int Image;
    private boolean isUniversal = false;
    private boolean isReserved = false;;
    private HashMap<TokenColor, Integer> price;
    private GridLayout currentGridLayout;
    private int cardIndexOnGridLayout;

    public Card(@NonNull Context context) {
        super(context);
    }

    public Card(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Card(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
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

    public int getCardIndexOnGridLayout() {
        return cardIndexOnGridLayout;
    }

    public void setCardIndexOnGridLayout(int cardIndexOnGridLayout) {
        this.cardIndexOnGridLayout = cardIndexOnGridLayout;
    }

    public int getCrowns() {
        return crowns;
    }

    public void setCrowns(int crowns) {
        this.crowns = crowns;
    }

    public HashMap<TokenColor, Integer> getPrice() {
        return price;
    }

    public void setPrice(HashMap<TokenColor, Integer> price) {
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

    public GridLayout getCurrentGridLayout() {
        return currentGridLayout;
    }

    public void setCurrentGridLayout(GridLayout currentGridLayout) {
        this.currentGridLayout = currentGridLayout;
    }

    public void setUniversal(boolean universal) {
        isUniversal = universal;
    }

    public boolean isUniversal() {
        return isUniversal;
    }
}
