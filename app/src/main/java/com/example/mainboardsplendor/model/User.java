package com.example.mainboardsplendor.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {

    private String username;

    //Objective
    private int CardsPoint;
    private int crowns;
    private int mostSameCardColorValue;

    //Privilege
    private int Scroll;
    private int reserveCard;
    private int tokensStack;

    public User(String username) {
        this.username = username;
        this.CardsPoint = 0;
        this.crowns = 0;
        this.mostSameCardColorValue = 0;
        this.Scroll = 0;
        this.reserveCard = 0;
        this.tokensStack = 0;
    }

    protected User(Parcel in) {
        username = in.readString();
        CardsPoint = in.readInt();
        crowns = in.readInt();
        mostSameCardColorValue = in.readInt();
        Scroll = in.readInt();
        reserveCard = in.readInt();
        tokensStack = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTokensPoint(int tokensPoint) {
        this.CardsPoint = tokensPoint;
    }

    public void setCrowns(int crowns) {
        this.crowns = crowns;
    }

    public void setMostSameCardColorValue(int mostSameCardColorValue) {
        this.mostSameCardColorValue = mostSameCardColorValue;
    }

    public void setScroll(int scroll) {
        Scroll = scroll;
    }

    public void setReserveCard(int reserveCard) {
        this.reserveCard = reserveCard;
    }

    public void setTokensStack(int tokensStack) {
        this.tokensStack = tokensStack;
    }

    public String getUsername() {
        return username;
    }

    public int getCardsPoint() {
        return CardsPoint;
    }

    public int getCrowns() {
        return crowns;
    }

    public int getMostSameCardColorValue() {
        return mostSameCardColorValue;
    }

    public int getScroll() {
        return Scroll;
    }

    public int getReserveCard() {
        return reserveCard;
    }

    public int getTokensStack() {
        return tokensStack;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeInt(CardsPoint);
        parcel.writeInt(crowns);
        parcel.writeInt(mostSameCardColorValue);
        parcel.writeInt(Scroll);
        parcel.writeInt(reserveCard);
        parcel.writeInt(tokensStack);
    }
}