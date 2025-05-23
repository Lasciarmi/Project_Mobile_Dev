package com.example.mainboardsplendor.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.mainboardsplendor.enumeration.TokenColor;

import java.util.HashMap;

public class User implements Parcelable {

    private String username;

    private HashMap<TokenColor, Integer> ownedTokens;
    private HashMap<TokenColor, Integer> ownedDiscount;

    private HashMap<TokenColor, Integer> mostSameCardValuePerColor;

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

    private int sumRoyalCard = 0;

    public int getSumRoyalCard() {
        return sumRoyalCard;
    }

    public void setSumRoyalCard(int sumRoyalCard) {
        this.sumRoyalCard = sumRoyalCard;
    }

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
        this.isCurrent = false;
        this.ownedTokens = new HashMap<>();
        this.ownedDiscount = new HashMap<>();
        this.mostSameCardValuePerColor = new HashMap<>();

        ownedTokens.put(TokenColor.BLUE, 0);
        ownedTokens.put(TokenColor.WHITE, 0);
        ownedTokens.put(TokenColor.GREEN, 0);
        ownedTokens.put(TokenColor.BLACK, 0);
        ownedTokens.put(TokenColor.RED, 0);
        ownedTokens.put(TokenColor.PEARL, 0);
        ownedTokens.put(TokenColor.GOLD, 0);

        ownedDiscount.put(TokenColor.BLUE, 0);
        ownedDiscount.put(TokenColor.WHITE, 0);
        ownedDiscount.put(TokenColor.GREEN, 0);
        ownedDiscount.put(TokenColor.BLACK, 0);
        ownedDiscount.put(TokenColor.RED, 0);

        mostSameCardValuePerColor.put(TokenColor.BLUE, 0);
        mostSameCardValuePerColor.put(TokenColor.WHITE, 0);
        mostSameCardValuePerColor.put(TokenColor.GREEN, 0);
        mostSameCardValuePerColor.put(TokenColor.BLACK, 0);
        mostSameCardValuePerColor.put(TokenColor.RED, 0);
    }

    protected User(Parcel in) {
        username = in.readString();
        CardsPoint = in.readInt();
        crowns = in.readInt();
        mostSameCardColorValue = in.readInt();
        Scroll = in.readInt();
        reserveCard = in.readInt();
        tokensStack = in.readInt();
        ownedTokens = in.readHashMap(HashMap.class.getClassLoader());
        ownedDiscount = in.readHashMap(HashMap.class.getClassLoader());
        mostSameCardValuePerColor = in.readHashMap(HashMap.class.getClassLoader());
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

    public void addToken2Bag(TokenColor token) {
        if (getTotalTokens() >= bagCapacity) {
            return;
        }
        incrementToken(token);
    }

    private void incrementToken(TokenColor token) {
        ownedTokens.put(token, ownedTokens.getOrDefault(token, 0) + 1);
    }

    public HashMap<TokenColor, Integer> getMostSameCardValuePerColor() {
        return mostSameCardValuePerColor;
    }

    public int getTotalTokens() {
        return getOwnedTokens().values().stream().mapToInt(Integer::intValue).sum();
    }

    public HashMap<TokenColor, Integer> getOwnedTokens() {
        return ownedTokens;
    }

    public HashMap<TokenColor, Integer> getOwnedDiscount() {
        return ownedDiscount;
    }

    public void setReserveCard(int reserveCard) {
        this.reserveCard = reserveCard;
    }

    public void setCardsPoint(int cardsPoint) {
        CardsPoint = cardsPoint;
    }


    public void setCrowns(int crowns) {
        this.crowns = crowns;
    }

    public void setScroll(int scroll) {
        Scroll = scroll;
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
