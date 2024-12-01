package com.example.mainboardsplendor.controller;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.example.mainboardsplendor.enumeration.ActiveTaskBar;
import com.example.mainboardsplendor.view.MainActivity;
import com.example.mainboardsplendor.R;
import com.example.mainboardsplendor.enumeration.TokenColor;
import com.example.mainboardsplendor.model.Card;
import com.example.mainboardsplendor.model.RoyalCard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CardController {

    GridLayout cardStoreTop;
    GridLayout cardStoreMid;
    GridLayout cardStoreBot;
    GridLayout reservedCardBoard;

    private List<Card> listCardLevel3;
    private List<Card> listCardLevel2;
    private List<Card> listCardLevel1;
    private List<RoyalCard> listRoyalCard;
    private Card selectedCard;

    private Context context;
    private MainActivity mainActivity;

    public CardController(GridLayout cardStoreTop, GridLayout cardStoreMid, GridLayout cardStoreBot,
                          GridLayout reservedCardBoard,List<Card> listCardLevel1, List<Card> listCardLevel2,
                          List<Card> listCardLevel3, List<RoyalCard> listRoyalCard,Context context, MainActivity mainActivity, Card selectedCard) {
        this.cardStoreTop = cardStoreTop;
        this.cardStoreMid = cardStoreMid;
        this.cardStoreBot = cardStoreBot;
        this.reservedCardBoard = reservedCardBoard;
        this.listCardLevel1 = listCardLevel1;
        this.listCardLevel2 = listCardLevel2;
        this.listCardLevel3 = listCardLevel3;
        this.listRoyalCard = listRoyalCard;
        this.context = context;
        this.mainActivity = mainActivity;
        this.selectedCard = selectedCard;
    }

    public void refreshValidCard(UserController userController, TokenColor tokenColor) {
//      for each listcard in allListLevelCard
        try {
            for (GridLayout cardBoard : Arrays.asList(cardStoreTop, cardStoreMid, cardStoreBot)) {

                if (tokenColor != null) {
                    if (tokenColor.equals(TokenColor.GOLD)) {
                        for (int i = 0; i < cardBoard.getChildCount(); i++) {
                            View view = cardBoard.getChildAt(i);
                            if (view instanceof Card) {
                                Card card = (Card) view;
                                if (selectedCard == null || selectedCard == card) {
                                    card.setClickable(true);
                                    card.setBackgroundResource(R.drawable.image_border_card_clickable);
                                    card.setOnClickListener(v -> {
                                        cardClickedJoker(userController, card, tokenColor);
                                    });
                                } else {
                                    card.setClickable(false);
                                    card.setBackgroundResource(0);
                                }
                            }
                        }
                    }
                }
                else {
                    //          for each card in listcard
                    for (int i=0; i < cardBoard.getChildCount(); i++) {
                        View view = cardBoard.getChildAt(i);
                        if (view instanceof Card) {
                            Card card = (Card) view;
                            card.setClickable(false);
                            card.setBackgroundResource(0);
                            HashMap<TokenColor, Integer> ownedToken = userController.getOwnedToken();
                            HashMap<TokenColor, Integer> ownedDiscount = userController.getOwnedDiscount();
                            HashMap<TokenColor, Integer> cardPrice = card.getPrice();
                            int blue = (cardPrice.get(TokenColor.BLUE) <= ownedToken.get(TokenColor.BLUE) + ownedDiscount.get(TokenColor.BLUE)) ? 0 : (ownedToken.get(TokenColor.BLUE) + ownedDiscount.get(TokenColor.BLUE)-cardPrice.get(TokenColor.BLUE));
                            int white = (cardPrice.get(TokenColor.WHITE) <= ownedToken.get(TokenColor.WHITE) + ownedDiscount.get(TokenColor.WHITE)) ? 0 : (ownedToken.get(TokenColor.WHITE) + ownedDiscount.get(TokenColor.WHITE)-cardPrice.get(TokenColor.WHITE));
                            int green = (cardPrice.get(TokenColor.GREEN) <= ownedToken.get(TokenColor.GREEN) + ownedDiscount.get(TokenColor.GREEN)) ? 0 : (ownedToken.get(TokenColor.GREEN) + ownedDiscount.get(TokenColor.GREEN)-cardPrice.get(TokenColor.GREEN));
                            int black = (cardPrice.get(TokenColor.BLACK) <= ownedToken.get(TokenColor.BLACK) + ownedDiscount.get(TokenColor.BLACK)) ? 0 : (ownedToken.get(TokenColor.BLACK) + ownedDiscount.get(TokenColor.BLACK)-cardPrice.get(TokenColor.BLACK));
                            int red = (cardPrice.get(TokenColor.RED) <= ownedToken.get(TokenColor.RED) + ownedDiscount.get(TokenColor.RED)) ? 0 : (ownedToken.get(TokenColor.RED) + ownedDiscount.get(TokenColor.RED)-cardPrice.get(TokenColor.RED));
                            int pearl = (cardPrice.get(TokenColor.PEARL) <= ownedToken.get(TokenColor.PEARL) + ownedDiscount.get(TokenColor.PEARL)) ? 0 : (ownedToken.get(TokenColor.PEARL) + ownedDiscount.get(TokenColor.PEARL)-cardPrice.get(TokenColor.PEARL));
                            int sum = blue + white + green + black + red + pearl + ownedToken.get(TokenColor.GOLD);

                            if (sum >= 0 && (selectedCard == null || selectedCard == card)){
                                card.setClickable(true);
                                card.setBackgroundResource(R.drawable.image_border_card_clickable);
                                card.setOnClickListener(v -> {
                                    cardClicked(userController, card);
                                });
                            }
                            else {
                                card.setClickable(false);
                                card.setBackgroundResource(0);
                            }
                        }

                    }
                    mainActivity.setTaskBar(ActiveTaskBar.CARD);
                }
            }
        }
        catch (NullPointerException e){
            Log.d("NullPointerException", "refreshValidCard: "+e.getMessage());
        }
    }

    private void cardClickedJoker(UserController userController, Card card, TokenColor tokenColor) {
        if (selectedCard == null){
            this.selectedCard = card;
        }
        else{
            this.selectedCard = null;
        }
        refreshValidCard(userController, tokenColor);
    }

    private void cardClicked(UserController userController, Card card) {
        if (selectedCard == null){
            mainActivity.setTaskBar(ActiveTaskBar.CARD);
            this.selectedCard = card;
        }
        else{
            mainActivity.setTaskBar(ActiveTaskBar.NONE);
            this.selectedCard = null;
        }
        refreshValidCard(userController, null);
    }

    public void InitCardTopDeck() {
        HashMap<TokenColor, Integer> hashMap_price_card_black_level_3_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_3_1 = {0, 2, 0, 6, 2, 0};
        AddPriceHashMap(hashMap_price_card_black_level_3_1, list_price_card_black_level_3_1);
        Card card_black_level_3_1 = AddCard(4, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 3, 1, 0, hashMap_price_card_black_level_3_1, R.drawable.card_black_level_3_1);

        HashMap<TokenColor, Integer> hashMap_price_card_black_level_3_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_3_2 = {0, 3, 5, 0, 3, 1};
        AddPriceHashMap(hashMap_price_card_black_level_3_2, list_price_card_black_level_3_2);
        Card card_black_level_3_2 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 3, 1, 2, hashMap_price_card_black_level_3_2, R.drawable.card_black_level_3_2);

        HashMap<TokenColor, Integer> hashMap_price_card_blue_level_3_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_3_1 = {6, 2, 2, 0, 0, 0};
        AddPriceHashMap(hashMap_price_card_blue_level_3_1, list_price_card_blue_level_3_1);
        Card card_blue_level_3_1 = AddCard(4, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 3, 1, 0, hashMap_price_card_blue_level_3_1, R.drawable.card_blue_level_3_1);

        HashMap<TokenColor, Integer> hashMap_price_card_blue_level_3_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_3_2 = {0, 3, 3, 5, 0, 1};
        AddPriceHashMap(hashMap_price_card_blue_level_3_2, list_price_card_blue_level_3_2);
        Card card_blue_level3_2 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 3, 1, 2, hashMap_price_card_blue_level_3_2, R.drawable.card_blue_level_3_2);

        HashMap<TokenColor, Integer> hashMap_price_card_green_level_3_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_3_1 = {2, 0, 6, 0, 2, 0};
        AddPriceHashMap(hashMap_price_card_green_level_3_1, list_price_card_green_level_3_1);
        Card card_green_level_3_1 = AddCard(4, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 3, 1, 0, hashMap_price_card_green_level_3_1, R.drawable.card_green_level_3_1);

        HashMap<TokenColor, Integer> hashMap_price_card_green_level_3_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_3_2 = {3, 5, 0, 0, 3, 1};
        AddPriceHashMap(hashMap_price_card_green_level_3_2, list_price_card_green_level_3_2);
        Card card_green_level_3_2 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 3, 1, 2, hashMap_price_card_green_level_3_2, R.drawable.card_green_level_3_2);

        HashMap<TokenColor, Integer> hashMap_price_card_normal_level_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_normal_level_3 = {0, 8, 0, 0, 0, 0};
        AddPriceHashMap(hashMap_price_card_normal_level_3, list_price_card_normal_level_3);
        Card card_normal_level_3 = AddCard(6, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 3, 0, 0, hashMap_price_card_normal_level_3, R.drawable.card_normal_level_3);

        HashMap<TokenColor, Integer> hashMap_price_card_red_level_3_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_3_1 = {0, 0, 2, 2, 6, 0};
        AddPriceHashMap(hashMap_price_card_red_level_3_1, list_price_card_red_level_3_1);
        Card card_red_level_3_1 = AddCard(4, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 3, 1, 0, hashMap_price_card_red_level_3_1, R.drawable.card_red_level_3_1);

        HashMap<TokenColor, Integer> hashMap_price_card_red_level_3_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_3_2 = {5, 0, 3, 3, 0, 1};
        AddPriceHashMap(hashMap_price_card_red_level_3_2, list_price_card_red_level_3_2);
        Card card_red_level_3_2 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 3, 1, 2, hashMap_price_card_red_level_3_2, R.drawable.card_red_level_3_2);

        HashMap<TokenColor, Integer> hashMap_price_card_ultra_level_3_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_3_1 = {0, 0, 0, 8, 0, 0};
        AddPriceHashMap(hashMap_price_card_ultra_level_3_1, list_price_card_ultra_level_3_1);
        Card card_ultra_level_3_1 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 3, 1, 0, hashMap_price_card_ultra_level_3_1, R.drawable.card_ultra_level_3_1);

        HashMap<TokenColor, Integer> hashMap_price_card_ultra_level_3_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_3_2 = {0, 0, 0, 8, 0, 0};
        AddPriceHashMap(hashMap_price_card_ultra_level_3_2, list_price_card_ultra_level_3_2);
        Card card_ultra_level_3_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 3, 1, 3, hashMap_price_card_ultra_level_3_1, R.drawable.card_ultra_level_3_2);

        HashMap<TokenColor, Integer> hashMap_price_card_white_level_3_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_3_1 = {2, 6, 0, 2, 0, 0};
        AddPriceHashMap(hashMap_price_card_white_level_3_1, list_price_card_white_level_3_1);
        Card card_white_level_3_1 = AddCard(4, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 3, 1, 0, hashMap_price_card_white_level_3_1, R.drawable.card_white_level_3_1);

        HashMap<TokenColor, Integer> hashMap_price_card_white_level_3_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_3_2 = {3, 0, 0, 3, 5, 1};
        AddPriceHashMap(hashMap_price_card_white_level_3_2, list_price_card_white_level_3_2);
        Card card_white_level_3_2 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 3, 1, 2, hashMap_price_card_white_level_3_2, R.drawable.card_white_level_3_2);

        // Total 13 Cards
        listCardLevel3.add(card_black_level_3_1);
        listCardLevel3.add(card_black_level_3_2);
        listCardLevel3.add(card_blue_level_3_1);
        listCardLevel3.add(card_blue_level3_2);
        listCardLevel3.add(card_green_level_3_1);
        listCardLevel3.add(card_green_level_3_2);
        listCardLevel3.add(card_normal_level_3);
        listCardLevel3.add(card_red_level_3_1);
        listCardLevel3.add(card_red_level_3_2);
        listCardLevel3.add(card_ultra_level_3_1);
        listCardLevel3.add(card_ultra_level_3_2);
        listCardLevel3.add(card_white_level_3_1);
        listCardLevel3.add(card_white_level_3_2);

    }

    public void InitCardMidDeck() {
        HashMap<TokenColor, Integer> hashMap_price_card_blue_level_2_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_2_1 = {0, 0, 4, 3, 0, 0};
        AddPriceHashMap(hashMap_price_card_blue_level_2_1, list_price_card_blue_level_2_1);
        Card card_blue_level_2_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 2, 1, 0, hashMap_price_card_blue_level_2_1, R.drawable.card_blue_level_2_1);

        HashMap<TokenColor, Integer> hashMap_price_card_blue_level_2_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_2_2 = {4, 2, 0, 0, 0, 1};
        AddPriceHashMap(hashMap_price_card_blue_level_2_2, list_price_card_blue_level_2_2);
        Card card_blue_level_2_2 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 2, 1, 0, hashMap_price_card_blue_level_2_2, R.drawable.card_blue_level_2_2);

        HashMap<TokenColor, Integer> hashMap_price_card_blue_level_2_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_2_3 = {0, 2, 0, 2, 2, 1};
        AddPriceHashMap(hashMap_price_card_blue_level_2_3, list_price_card_blue_level_2_3);
        Card card_blue_level_2_3 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 2, 1, 1, hashMap_price_card_blue_level_2_3, R.drawable.card_blue_level_2_3);

        HashMap<TokenColor, Integer> hashMap_price_card_blue_level_2_4 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_2_4 = {0, 0, 5, 0, 2, 0};
        AddPriceHashMap(hashMap_price_card_blue_level_2_4, list_price_card_blue_level_2_4);
        Card card_blue_level_2_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 2, 2, 0, hashMap_price_card_blue_level_2_4, R.drawable.card_blue_level_2_4);

        HashMap<TokenColor, Integer> hashMap_price_card_white_level_2_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_2_1 = {4, 0, 0, 0, 3, 0};
        AddPriceHashMap(hashMap_price_card_white_level_2_1, list_price_card_white_level_2_1);
        Card card_white_level_2_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 2, 1, 0, hashMap_price_card_white_level_2_1, R.drawable.card_white_level_2_1);

        HashMap<TokenColor, Integer> hashMap_price_card_white_level_2_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_2_2 = {0, 4, 0, 2, 0, 1};
        AddPriceHashMap(hashMap_price_card_white_level_2_2, list_price_card_white_level_2_2);
        Card card_white_level_2_2 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 2, 1, 0, hashMap_price_card_white_level_2_2, R.drawable.card_white_level_2_2);

        HashMap<TokenColor, Integer> hashMap_price_card_white_level_2_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_2_3 = {0, 0, 2, 2, 2, 1};
        AddPriceHashMap(hashMap_price_card_white_level_2_3, list_price_card_white_level_2_3);
        Card card_white_level_2_3 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 2, 1, 1, hashMap_price_card_white_level_2_3, R.drawable.card_white_level_2_3);

        HashMap<TokenColor, Integer> hashMap_price_card_white_level_2_4 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_2_4 = {5, 0, 2, 0, 0, 0};
        AddPriceHashMap(hashMap_price_card_white_level_2_4, list_price_card_white_level_2_4);
        Card card_white_level_2_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 2, 2, 0, hashMap_price_card_white_level_2_4, R.drawable.card_white_level_2_4);

        HashMap<TokenColor, Integer> hashMap_price_card_green_level_2_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_2_1 = {0, 3, 0, 0, 4, 0};
        AddPriceHashMap(hashMap_price_card_green_level_2_1, list_price_card_green_level_2_1);
        Card card_green_level_2_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 2, 1, 0, hashMap_price_card_green_level_2_1, R.drawable.card_green_level_2_1);

        HashMap<TokenColor, Integer> hashMap_price_card_green_level_2_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_2_2 = {2, 0, 4, 0, 0, 1};
        AddPriceHashMap(hashMap_price_card_green_level_2_2, list_price_card_green_level_2_2);
        Card card_green_level_2_2 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 2, 1, 0, hashMap_price_card_green_level_2_2, R.drawable.card_green_level_2_2);

        HashMap<TokenColor, Integer> hashMap_price_card_green_level_2_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_2_3 = {2, 2, 0, 2, 0, 1};
        AddPriceHashMap(hashMap_price_card_green_level_2_3, list_price_card_green_level_2_3);
        Card card_green_level_2_3 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 2, 1, 1, hashMap_price_card_green_level_2_3, R.drawable.card_green_level_2_3);

        HashMap<TokenColor, Integer> hashMap_price_card_green_level_2_4 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_2_4 = {0, 0, 0, 2, 5, 0};
        AddPriceHashMap(hashMap_price_card_green_level_2_4, list_price_card_green_level_2_4);
        Card card_green_level_2_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 2, 2, 0, hashMap_price_card_green_level_2_4, R.drawable.card_green_level_2_4);

        HashMap<TokenColor, Integer> hashMap_price_card_black_level_2_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_2_1 = {0, 4, 3, 0, 0, 0};
        AddPriceHashMap(hashMap_price_card_black_level_2_1, list_price_card_black_level_2_1);
        Card card_black_level_2_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 2, 1, 0, hashMap_price_card_black_level_2_1, R.drawable.card_black_level_2_1);

        HashMap<TokenColor, Integer> hashMap_price_card_black_level_2_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_2_2 = {0, 0, 0, 4, 2, 1};
        AddPriceHashMap(hashMap_price_card_black_level_2_2, list_price_card_black_level_2_2);
        Card card_black_level_2_2 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 2, 1, 0, hashMap_price_card_black_level_2_2, R.drawable.card_black_level_2_2);

        HashMap<TokenColor, Integer> hashMap_price_card_black_level_2_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_2_3 = {2, 0, 2, 0, 2, 1};
        AddPriceHashMap(hashMap_price_card_black_level_2_3, list_price_card_black_level_2_3);
        Card card_black_level_2_3 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 2, 1, 1, hashMap_price_card_black_level_2_3, R.drawable.card_black_level_2_3);

        HashMap<TokenColor, Integer> hashMap_price_card_black_level_2_4 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_2_4 = {2, 5, 0, 0, 0, 0};
        AddPriceHashMap(hashMap_price_card_black_level_2_4, list_price_card_black_level_2_4);
        Card card_black_level_2_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 2, 2, 0, hashMap_price_card_black_level_2_4, R.drawable.card_black_level_2_4);

        HashMap<TokenColor, Integer> hashMap_price_card_red_level_2_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_2_1 = {3, 0, 0, 4, 0, 0};
        AddPriceHashMap(hashMap_price_card_red_level_2_1, list_price_card_red_level_2_1);
        Card card_red_level_2_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 2, 1, 0, hashMap_price_card_red_level_2_1, R.drawable.card_red_level_2_1);

        HashMap<TokenColor, Integer> hashMap_price_card_red_level_2_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_2_2 = {0, 0, 2, 0, 4, 1};
        AddPriceHashMap(hashMap_price_card_red_level_2_2, list_price_card_red_level_2_2);
        Card card_red_level_2_2 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 2, 1, 0, hashMap_price_card_red_level_2_2, R.drawable.card_red_level_2_2);

        HashMap<TokenColor, Integer> hashMap_price_card_red_level_2_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_2_3 = {2, 2, 2, 0, 0, 1};
        AddPriceHashMap(hashMap_price_card_red_level_2_3, list_price_card_red_level_2_3);
        Card card_red_level_2_3 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 2, 1, 1, hashMap_price_card_red_level_2_3, R.drawable.card_red_level_2_3);

        HashMap<TokenColor, Integer> hashMap_price_card_red_level_2_4 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_2_4 = {0, 0, 0, 5, 2, 0};
        AddPriceHashMap(hashMap_price_card_red_level_2_4, list_price_card_red_level_2_4);
        Card card_red_level_2_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 2, 2, 0, hashMap_price_card_red_level_2_4, R.drawable.card_red_level_2_4);

        HashMap<TokenColor, Integer> hashMap_price_card_normal_level_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_normal_level_2 = {6, 0, 0, 0, 0, 1};
        AddPriceHashMap(hashMap_price_card_normal_level_2, list_price_card_normal_level_2);
        Card card_normal_level_2 = AddCard(5, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 2, 0, 0, hashMap_price_card_normal_level_2, R.drawable.card_normal_level_2);

        HashMap<TokenColor, Integer> hashMap_price_card_ultra_level_2_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_2_1 = {6, 0, 0, 0, 0, 1};
        AddPriceHashMap(hashMap_price_card_ultra_level_2_1, list_price_card_ultra_level_2_1);
        Card card_ultra_level_2_1 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 2, 1, 2, hashMap_price_card_ultra_level_2_1, R.drawable.card_ultra_level_2_1);

        HashMap<TokenColor, Integer> hashMap_price_card_ultra_level_2_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_2_2 = {0, 0, 6, 0, 0, 1};
        AddPriceHashMap(hashMap_price_card_ultra_level_2_2, list_price_card_ultra_level_2_2);
        Card card_ultra_level_2_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 2, 1, 2, hashMap_price_card_ultra_level_2_2, R.drawable.card_ultra_level_2_2);

        HashMap<TokenColor, Integer> hashMap_price_card_ultra_level_2_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_2_3 = {0, 0, 6, 0, 0, 1};
        AddPriceHashMap(hashMap_price_card_ultra_level_2_3, list_price_card_ultra_level_2_3);
        Card card_ultra_level_2_3 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 2, 1, 0, hashMap_price_card_ultra_level_2_3, R.drawable.card_ultra_level_2_3);

        // Total 24 Cards
        listCardLevel2.add(card_blue_level_2_1);
        listCardLevel2.add(card_blue_level_2_2);
        listCardLevel2.add(card_blue_level_2_3);
        listCardLevel2.add(card_blue_level_2_4);
        listCardLevel2.add(card_white_level_2_1);
        listCardLevel2.add(card_white_level_2_2);
        listCardLevel2.add(card_white_level_2_3);
        listCardLevel2.add(card_white_level_2_4);
        listCardLevel2.add(card_green_level_2_1);
        listCardLevel2.add(card_green_level_2_2);
        listCardLevel2.add(card_green_level_2_3);
        listCardLevel2.add(card_green_level_2_4);
        listCardLevel2.add(card_black_level_2_1);
        listCardLevel2.add(card_black_level_2_2);
        listCardLevel2.add(card_black_level_2_3);
        listCardLevel2.add(card_black_level_2_4);
        listCardLevel2.add(card_red_level_2_1);
        listCardLevel2.add(card_red_level_2_2);
        listCardLevel2.add(card_red_level_2_3);
        listCardLevel2.add(card_red_level_2_4);
        listCardLevel2.add(card_normal_level_2);
        listCardLevel2.add(card_ultra_level_2_1);
        listCardLevel2.add(card_ultra_level_2_2);
        listCardLevel2.add(card_ultra_level_2_3);
    }

    public void InitCardBotDeck() {
        HashMap<TokenColor, Integer> hashMap_price_card_blue_level_1_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_1 = {0, 0, 0, 3, 2, 0};
        AddPriceHashMap(hashMap_price_card_blue_level_1_1, list_price_card_blue_level_1_1);
        Card card_blue_level_1_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 1, 1, 0, hashMap_price_card_blue_level_1_1, R.drawable.card_blue_level_1_1);

        HashMap<TokenColor, Integer> hashMap_price_card_blue_level_1_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_2 = {0, 1, 1, 1, 1, 0};
        AddPriceHashMap(hashMap_price_card_blue_level_1_2, list_price_card_blue_level_1_2);
        Card card_blue_level_1_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 1, 1, 0, hashMap_price_card_blue_level_1_2, R.drawable.card_blue_level_1_2);

        HashMap<TokenColor, Integer> hashMap_price_card_blue_level_1_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_3 = {0, 2, 0, 2, 0, 0};
        AddPriceHashMap(hashMap_price_card_blue_level_1_3, list_price_card_blue_level_1_3);
        Card card_blue_level_1_3 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 1, 1, 0, hashMap_price_card_blue_level_1_3, R.drawable.card_blue_level_1_3);

        HashMap<TokenColor, Integer> hashMap_price_card_blue_level_1_4 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_4 = {0, 0, 2, 0, 2, 1};
        AddPriceHashMap(hashMap_price_card_blue_level_1_4, list_price_card_blue_level_1_4);
        Card card_blue_level_1_4 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 1, 1, 0, hashMap_price_card_blue_level_1_4, R.drawable.card_blue_level_1_4);

        HashMap<TokenColor, Integer> hashMap_price_card_blue_level_1_5 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_5 = {0, 0, 3, 0, 0, 0};
        AddPriceHashMap(hashMap_price_card_blue_level_1_5, list_price_card_blue_level_1_5);
        Card card_blue_level_1_5 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 1, 1, 1, hashMap_price_card_blue_level_1_5, R.drawable.card_blue_level_1_5);

        HashMap<TokenColor, Integer> hashMap_price_card_white_level_1_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_1 = {0, 0, 0, 2, 2, 0};
        AddPriceHashMap(hashMap_price_card_white_level_1_1, list_price_card_white_level_1_1);
        Card card_white_level_1_1 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 1, 1, 0, hashMap_price_card_white_level_1_1, R.drawable.card_white_level_1_1);

        HashMap<TokenColor, Integer> hashMap_price_card_white_level_1_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_2 = {2, 0, 2, 0, 0, 1};
        AddPriceHashMap(hashMap_price_card_white_level_1_2, list_price_card_white_level_1_2);
        Card card_white_level_1_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 1, 1, 0, hashMap_price_card_white_level_1_2, R.drawable.card_white_level_1_2);

        HashMap<TokenColor, Integer> hashMap_price_card_white_level_1_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_3 = {1, 0, 1, 1, 1, 0};
        AddPriceHashMap(hashMap_price_card_white_level_1_3, list_price_card_white_level_1_3);
        Card card_white_level_1_3 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 1, 1, 0, hashMap_price_card_white_level_1_3, R.drawable.card_white_level_1_3);

        HashMap<TokenColor, Integer> hashMap_price_card_white_level_1_4 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_4 = {0, 0, 2, 0, 3, 0};
        AddPriceHashMap(hashMap_price_card_white_level_1_4, list_price_card_white_level_1_4);
        Card card_white_level_1_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 1, 1, 0, hashMap_price_card_white_level_1_4, R.drawable.card_white_level_1_4);

        HashMap<TokenColor, Integer> hashMap_price_card_white_level_1_5 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_5 = {3, 0, 0, 0, 0, 0};
        AddPriceHashMap(hashMap_price_card_white_level_1_5, list_price_card_white_level_1_5);
        Card card_white_level_1_5 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 1, 1, 1, hashMap_price_card_white_level_1_5, R.drawable.card_white_level_1_5);

        HashMap<TokenColor, Integer> hashMap_price_card_green_level_1_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_1 = {2, 2, 0, 0, 0, 0};
        AddPriceHashMap(hashMap_price_card_green_level_1_1, list_price_card_green_level_1_1);
        Card card_green_level_1_1 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 1, 1, 0, hashMap_price_card_green_level_1_1, R.drawable.card_green_level_1_1);

        HashMap<TokenColor, Integer> hashMap_price_card_green_level_1_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_2 = {0, 0, 0, 2, 2, 1};
        AddPriceHashMap(hashMap_price_card_green_level_1_2, list_price_card_green_level_1_2);
        Card card_green_level_1_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 1, 1, 0, hashMap_price_card_green_level_1_2, R.drawable.card_green_level_1_2);

        HashMap<TokenColor, Integer> hashMap_price_card_green_level_1_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_3 = {1, 1, 0, 1, 1, 0};
        AddPriceHashMap(hashMap_price_card_green_level_1_3, list_price_card_green_level_1_3);
        Card card_green_level_1_3 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 1, 1, 0, hashMap_price_card_green_level_1_3, R.drawable.card_green_level_1_3);

        HashMap<TokenColor, Integer> hashMap_price_card_green_level_1_4 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_4 = {0, 3, 0, 2, 0, 0};
        AddPriceHashMap(hashMap_price_card_green_level_1_4, list_price_card_green_level_1_4);
        Card card_green_level_1_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 1, 1, 0, hashMap_price_card_green_level_1_4, R.drawable.card_green_level_1_4);

        HashMap<TokenColor, Integer> hashMap_price_card_green_level_1_5 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_5 = {0, 0, 0, 0, 3, 0};
        AddPriceHashMap(hashMap_price_card_green_level_1_5, list_price_card_green_level_1_5);
        Card card_green_level_1_5 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 1, 1, 1, hashMap_price_card_green_level_1_5, R.drawable.card_green_level_1_5);

        HashMap<TokenColor, Integer> hashMap_price_card_black_level_1_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_1 = {0, 0, 2, 0, 2, 0};
        AddPriceHashMap(hashMap_price_card_black_level_1_1, list_price_card_black_level_1_1);
        Card card_black_level_1_1 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 1, 1, 0, hashMap_price_card_black_level_1_1, R.drawable.card_black_level_1_1);

        HashMap<TokenColor, Integer> hashMap_price_card_black_level_1_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_2 = {2, 2, 0, 0, 0, 1};
        AddPriceHashMap(hashMap_price_card_black_level_1_2, list_price_card_black_level_1_2);
        Card card_black_level_1_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 1, 1, 0, hashMap_price_card_black_level_1_2, R.drawable.card_black_level_1_2);

        HashMap<TokenColor, Integer> hashMap_price_card_black_level_1_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_3 = {1, 1, 1, 0, 1, 0};
        AddPriceHashMap(hashMap_price_card_black_level_1_3, list_price_card_black_level_1_3);
        Card card_black_level_1_3 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 1, 1, 0, hashMap_price_card_black_level_1_3, R.drawable.card_black_level_1_3);

        HashMap<TokenColor, Integer> hashMap_price_card_black_level_1_4 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_4 = {2, 0, 3, 0, 0, 0};
        AddPriceHashMap(hashMap_price_card_black_level_1_4, list_price_card_black_level_1_4);
        Card card_black_level_1_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 1, 1, 0, hashMap_price_card_black_level_1_4, R.drawable.card_black_level_1_4);

        HashMap<TokenColor, Integer> hashMap_price_card_black_level_1_5 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_5 = {0, 3, 0, 0, 0, 0};
        AddPriceHashMap(hashMap_price_card_black_level_1_5, list_price_card_black_level_1_5);
        Card card_black_level_1_5 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 1, 1, 1, hashMap_price_card_black_level_1_5, R.drawable.card_black_level_1_5);

        HashMap<TokenColor, Integer> hashMap_price_card_red_level_1_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_1 = {2, 0, 2, 0, 0, 0};
        AddPriceHashMap(hashMap_price_card_red_level_1_1, list_price_card_red_level_1_1);
        Card card_red_level_1_1 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 1, 1, 0, hashMap_price_card_red_level_1_1, R.drawable.card_red_level_1_1);

        HashMap<TokenColor, Integer> hashMap_price_card_red_level_1_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_2 = {0, 2, 0, 2, 0, 1};
        AddPriceHashMap(hashMap_price_card_red_level_1_2, list_price_card_red_level_1_2);
        Card card_red_level_1_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 1, 1, 0, hashMap_price_card_red_level_1_2, R.drawable.card_red_level_1_2);

        HashMap<TokenColor, Integer> hashMap_price_card_red_level_1_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_3 = {1, 1, 1, 1, 0, 0};
        AddPriceHashMap(hashMap_price_card_red_level_1_3, list_price_card_red_level_1_3);
        Card card_red_level_1_3 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 1, 1,0, hashMap_price_card_red_level_1_3, R.drawable.card_red_level_1_3);

        HashMap<TokenColor, Integer> hashMap_price_card_red_level_1_4 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_4 = {3, 2, 0, 0, 0, 0};
        AddPriceHashMap(hashMap_price_card_red_level_1_4, list_price_card_red_level_1_4);
        Card card_red_level_1_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 1, 1, 0, hashMap_price_card_red_level_1_4, R.drawable.card_red_level_1_4);

        HashMap<TokenColor, Integer> hashMap_price_card_red_level_1_5 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_5 = {0, 0, 0, 3, 0, 0};
        AddPriceHashMap(hashMap_price_card_red_level_1_5, list_price_card_red_level_1_5);
        Card card_red_level_1_5 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 1, 1, 1, hashMap_price_card_red_level_1_5, R.drawable.card_red_level_1_5);

        HashMap<TokenColor, Integer> hashMap_price_card_normal_level_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_normal_level_1 = {0, 0, 0, 0, 4, 1};
        AddPriceHashMap(hashMap_price_card_normal_level_1, list_price_card_normal_level_1);
        Card card_normal_level_1 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 1, 0, 0, hashMap_price_card_normal_level_1, R.drawable.card_normal_level_1);

        HashMap<TokenColor, Integer> hashMap_price_card_ultra_level_1_1 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_1_1 = {0, 0, 0, 4, 0, 1};
        AddPriceHashMap(hashMap_price_card_ultra_level_1_1, list_price_card_ultra_level_1_1);
        Card card_ultra_level_1_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 1, 1, 0, hashMap_price_card_ultra_level_1_1, R.drawable.card_ultra_level_1_1);

        HashMap<TokenColor, Integer> hashMap_price_card_ultra_level_1_2 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_1_2 = {0, 2, 2, 1, 0, 1};
        AddPriceHashMap(hashMap_price_card_ultra_level_1_2, list_price_card_ultra_level_1_2);
        Card card_ultra_level_1_2 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 1, 1, 0, hashMap_price_card_ultra_level_1_2, R.drawable.card_ultra_level_1_2);

        HashMap<TokenColor, Integer> hashMap_price_card_ultra_level_1_3 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_1_3 = {2, 0, 0, 1, 2, 1};
        AddPriceHashMap(hashMap_price_card_ultra_level_1_3, list_price_card_ultra_level_1_3);
        Card card_ultra_level_1_3 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 1,1, 0, hashMap_price_card_ultra_level_1_3, R.drawable.card_ultra_level_1_3);

        HashMap<TokenColor, Integer> hashMap_price_card_ultra_level_1_4 = new HashMap<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_1_4 = {0, 4, 0, 0, 0, 1};
        AddPriceHashMap(hashMap_price_card_ultra_level_1_4, list_price_card_ultra_level_1_4);
        Card card_ultra_level_1_4 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 1, 1, 1, hashMap_price_card_ultra_level_1_4, R.drawable.card_ultra_level_1_4);

        // Total 30 cards
        listCardLevel1.add(card_blue_level_1_1);
        listCardLevel1.add(card_blue_level_1_2);
        listCardLevel1.add(card_blue_level_1_3);
        listCardLevel1.add(card_blue_level_1_4);
        listCardLevel1.add(card_blue_level_1_5);
        listCardLevel1.add(card_white_level_1_1);
        listCardLevel1.add(card_white_level_1_2);
        listCardLevel1.add(card_white_level_1_3);
        listCardLevel1.add(card_white_level_1_4);
        listCardLevel1.add(card_white_level_1_5);
        listCardLevel1.add(card_green_level_1_1);
        listCardLevel1.add(card_green_level_1_2);
        listCardLevel1.add(card_green_level_1_3);
        listCardLevel1.add(card_green_level_1_4);
        listCardLevel1.add(card_green_level_1_5);
        listCardLevel1.add(card_black_level_1_1);
        listCardLevel1.add(card_black_level_1_2);
        listCardLevel1.add(card_black_level_1_3);
        listCardLevel1.add(card_black_level_1_4);
        listCardLevel1.add(card_black_level_1_5);
        listCardLevel1.add(card_red_level_1_1);
        listCardLevel1.add(card_red_level_1_2);
        listCardLevel1.add(card_red_level_1_3);
        listCardLevel1.add(card_red_level_1_4);
        listCardLevel1.add(card_red_level_1_5);
        listCardLevel1.add(card_normal_level_1);
        listCardLevel1.add(card_ultra_level_1_1);
        listCardLevel1.add(card_ultra_level_1_2);
        listCardLevel1.add(card_ultra_level_1_3);
        listCardLevel1.add(card_ultra_level_1_4);
    }

    public void InitCardBoard(){
        InitCard(listCardLevel3, cardStoreTop, 3);
        InitCard(listCardLevel2, cardStoreMid, 4);
        InitCard(listCardLevel1, cardStoreBot, 5);
    }

    private void InitCard(List<Card> cards, GridLayout cardBoard, int rowCount) {
        for (int i=0; i < rowCount; i++) {
            Card card = PickRandomCard(cards);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(2, 0, 2, 0);
            params.width = (int) (60 * mainActivity.getResources().getDisplayMetrics().density);
            params.height = (int) (100 * mainActivity.getResources().getDisplayMetrics().density);

            card.setImageResource(card.getImage());
            card.setLayoutParams(params);

            cardBoard.addView(card);
        }
    }

    private Card PickRandomCard(List<Card> listCard){
        Random random = new Random();
        int randomIndex = random.nextInt(listCard.size());
        return listCard.remove(randomIndex);
    }

    private Card AddCard(int cardValue, Color color, int level, int discount, int crowns, HashMap<TokenColor, Integer> price, int image){
        Card card = new Card(context);
        card.setCardValue(cardValue);
        card.setColor(color);
        card.setLevel(level);
        card.setDiscount(discount);
        card.setCrowns(crowns);
        card.setPrice(price);
        card.setImage(image);
        return card;
    }

    public void AddPriceHashMap(HashMap<TokenColor, Integer> myHashPrice, int[] listPrice){
        myHashPrice.put(TokenColor.BLUE, listPrice[0]);
        myHashPrice.put(TokenColor.WHITE, listPrice[1]);
        myHashPrice.put(TokenColor.GREEN, listPrice[2]);
        myHashPrice.put(TokenColor.BLACK, listPrice[3]);
        myHashPrice.put(TokenColor.RED, listPrice[4]);
        myHashPrice.put(TokenColor.PEARL, listPrice[5]);
    }

    public void InitReservedCard(){
        RoyalCard royalCard_1 = AddRoyalCard(2, 3, R.drawable.royal_card_1);
        RoyalCard royalCard_2 = AddRoyalCard(3, 3, R.drawable.royal_card_2);
        RoyalCard royalCard_3 = AddRoyalCard(2, 3, R.drawable.royal_card_3);
        RoyalCard royalCard_4 = AddRoyalCard(2, 3, R.drawable.royal_card_4);

        listRoyalCard.add(royalCard_1);
        listRoyalCard.add(royalCard_2);
        listRoyalCard.add(royalCard_3);
        listRoyalCard.add(royalCard_4);
    }

    private RoyalCard AddRoyalCard(int points, int crowns, int image) {
        RoyalCard royalCard = new RoyalCard(context);
        royalCard.setPoints(points);
        royalCard.setCrowns(crowns);
        royalCard.setImage(image);
        return royalCard;
    }

    public void InitReservedCardBoard() {
        for (int i = 0; i<4; i++){
            RoyalCard royalCard = PickRandomRoyalCard(listRoyalCard);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(5, 20, 5, 0);
            params.width = (int) (60 * mainActivity.getResources().getDisplayMetrics().density);
            params.height = (int) (100 * mainActivity.getResources().getDisplayMetrics().density);

            ImageView imageView = new ImageView(context);
            imageView.setImageResource(royalCard.getImage());
            imageView.setLayoutParams(params);

            reservedCardBoard.addView(imageView);
        }
    }

    private RoyalCard PickRandomRoyalCard(List<RoyalCard> royalCards) {
        Random random = new Random();
        int randomIndex = random.nextInt(royalCards.size());
        return royalCards.remove(randomIndex);
    }
}
