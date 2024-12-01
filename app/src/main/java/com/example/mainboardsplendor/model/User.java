package com.example.mainboardsplendor.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class User implements Parcelable {

    private String username;

    private HashMap<Token, Integer> ownedTokens;

    //Objective
    private int CardsPoint;
    private int crowns;
    private int mostSameCardColorValue;

    //Privilege
    private int Scroll;
    private int reserveCard;
    private int tokensStack;

    private boolean isCurrent;

    private static final int bagCapacity = 10;

    public boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public User(String username) {
        this.username = username;
        this.CardsPoint = 0;
        this.crowns = 0;
        this.mostSameCardColorValue = 0;
        this.Scroll = 0;
        this.reserveCard = 0;
        this.tokensStack = 0;
        this.ownedTokens = new HashMap<>();
    }

    protected User(Parcel in) {
        username = in.readString();
        CardsPoint = in.readInt();
        crowns = in.readInt();
        mostSameCardColorValue = in.readInt();
        Scroll = in.readInt();
        reserveCard = in.readInt();
        tokensStack = in.readInt();
        ownedTokens = in.readHashMap(Token.class.getClassLoader());
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

    public boolean addToken2Bag(TokenColor token) {
        if (getTotalTokens() >= bagCapacity) {
            return false;
        }
        incrementToken(token);
        return true;
    }

    private void incrementToken(TokenColor token) {
        ownedTokens.put(token, ownedTokens.getOrDefault(token, 0) + 1);
    }

    public int getTotalTokens() {
        return getOwnedTokens().values().stream().mapToInt(Integer::intValue).sum();
    }

    public HashMap<TokenColor, Integer> getOwnedTokens() {
        return ownedTokens;
    }

    public void InitOwnedTokens(HashMap<Token, Integer> ownedTokens, Token token) {
        ownedTokens.put(token, 0);
    }

    public void addToken2Bag(Token token) {
            ownedTokens.put(token, ownedTokens.get(token) + 1);
    }

    public void setCardsPoint(int cardsPoint) {
        CardsPoint = cardsPoint;
    }

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
        int totalTokens = 0;
        for (Integer count : ownedTokens.values()) {
            totalTokens += count;
        }
        return totalTokens;
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
        parcel.writeMap(ownedTokens);
    }
}
