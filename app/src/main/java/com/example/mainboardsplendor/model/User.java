package com.example.mainboardsplendor.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.mainboardsplendor.MainActivity;

import java.util.HashMap;

public class User implements Parcelable {

    private String username;

    private HashMap<MainActivity.TokenColor, Integer> ownedTokens;
    private HashMap<MainActivity.TokenColor, Integer> ownedDiscount;

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
        this.ownedTokens = new HashMap<>();
        this.ownedDiscount = new HashMap<>();

        ownedTokens.put(MainActivity.TokenColor.BLUE, 0);
        ownedTokens.put(MainActivity.TokenColor.WHITE, 0);
        ownedTokens.put(MainActivity.TokenColor.GREEN, 0);
        ownedTokens.put(MainActivity.TokenColor.BLACK, 0);
        ownedTokens.put(MainActivity.TokenColor.RED, 0);
        ownedTokens.put(MainActivity.TokenColor.PEARL, 0);
        ownedTokens.put(MainActivity.TokenColor.GOLD, 0);

        ownedDiscount.put(MainActivity.TokenColor.BLUE, 0);
        ownedDiscount.put(MainActivity.TokenColor.WHITE, 0);
        ownedDiscount.put(MainActivity.TokenColor.GREEN, 0);
        ownedDiscount.put(MainActivity.TokenColor.BLACK, 0);
        ownedDiscount.put(MainActivity.TokenColor.RED, 0);
        ownedDiscount.put(MainActivity.TokenColor.PEARL, 0);
        ownedDiscount.put(MainActivity.TokenColor.GOLD, 0);
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

    public void addToken2Bag(MainActivity.TokenColor token, int quantity) {
        if (ownedTokens.containsKey(token)) {
            int currentQuantity = (int) ownedTokens.get(token);
            ownedTokens.put(token, currentQuantity + quantity);
        } else {
            ownedTokens.put(token, quantity);
        }
    }

    public HashMap<MainActivity.TokenColor, Integer> getOwnedTokens() {
        return ownedTokens;
    }
    public HashMap<MainActivity.TokenColor, Integer> getOwnedDiscount() {
        return ownedDiscount;
    }
    public void setOwnedDiscount(HashMap<MainActivity.TokenColor, Integer> ownedDiscount) {
        this.ownedDiscount = ownedDiscount;
    }

    public void setOwnedTokens(HashMap<MainActivity.TokenColor, Integer> ownedTokens) {
        this.ownedTokens = ownedTokens;
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
        parcel.writeMap(ownedTokens);
    }
}
