package com.example.mainboardsplendor.controller;

import android.content.Context;
import android.graphics.Color;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.mainboardsplendor.MainActivity;
import com.example.mainboardsplendor.R;
import com.example.mainboardsplendor.databinding.LayoutCardBoardBinding;
import com.example.mainboardsplendor.model.Card;
import com.example.mainboardsplendor.model.RoyalCard;

import java.util.ArrayList;
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

    private Context context;
    private MainActivity mainActivity;

    public CardController(GridLayout cardStoreTop, GridLayout cardStoreMid, GridLayout cardStoreBot, GridLayout reservedCardBoard,List<Card> listCardLevel1, List<Card> listCardLevel2, List<Card> listCardLevel3, List<RoyalCard> listRoyalCard,Context context, MainActivity mainActivity) {
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
    }

    public void InitCardTopDeck() {
        ArrayList<Integer> arrayList_price_card_black_level_3_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_3_1 = {0, 2, 0, 6, 2, 0};
        AddPriceList(arrayList_price_card_black_level_3_1, list_price_card_black_level_3_1);
        Card card_black_level_3_1 = AddCard(4, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 3, 1, 0, arrayList_price_card_black_level_3_1, R.drawable.card_black_level_3_1);

        ArrayList<Integer> arrayList_price_card_black_level_3_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_3_2 = {0, 3, 5, 0, 3, 1};
        AddPriceList(arrayList_price_card_black_level_3_2, list_price_card_black_level_3_2);
        Card card_black_level_3_2 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 3, 1, 2, arrayList_price_card_black_level_3_2, R.drawable.card_black_level_3_2);

        ArrayList<Integer> arrayList_price_card_blue_level_3_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_3_1 = {6, 2, 2, 0, 0, 0};
        AddPriceList(arrayList_price_card_blue_level_3_1, list_price_card_blue_level_3_1);
        Card card_blue_level_3_1 = AddCard(4, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 3, 1, 0, arrayList_price_card_blue_level_3_1, R.drawable.card_blue_level_3_1);

        ArrayList<Integer> arrayList_price_card_blue_level_3_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_3_2 = {0, 3, 3, 5, 0, 1};
        AddPriceList(arrayList_price_card_blue_level_3_2, list_price_card_blue_level_3_2);
        Card card_blue_level3_2 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 3, 1, 2, arrayList_price_card_blue_level_3_2, R.drawable.card_blue_level_3_2);

        ArrayList<Integer> arrayList_price_card_green_level_3_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_3_1 = {2, 0, 6, 0, 2, 0};
        AddPriceList(arrayList_price_card_green_level_3_1, list_price_card_green_level_3_1);
        Card card_green_level_3_1 = AddCard(4, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 3, 1, 0, arrayList_price_card_green_level_3_1, R.drawable.card_green_level_3_1);

        ArrayList<Integer> arrayList_price_card_green_level_3_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_3_2 = {3, 5, 0, 0, 3, 1};
        AddPriceList(arrayList_price_card_green_level_3_2, list_price_card_green_level_3_2);
        Card card_green_level_3_2 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 3, 1, 2, arrayList_price_card_green_level_3_2, R.drawable.card_green_level_3_2);

        ArrayList<Integer> arrayList_price_card_normal_level_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_normal_level_3 = {0, 8, 0, 0, 0, 0};
        AddPriceList(arrayList_price_card_normal_level_3, list_price_card_normal_level_3);
        Card card_normal_level_3 = AddCard(6, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 3, 0, 0, arrayList_price_card_normal_level_3, R.drawable.card_normal_level_3);

        ArrayList<Integer> arrayList_price_card_red_level_3_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_3_1 = {0, 0, 2, 2, 6, 0};
        AddPriceList(arrayList_price_card_red_level_3_1, list_price_card_red_level_3_1);
        Card card_red_level_3_1 = AddCard(4, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 3, 1, 0, arrayList_price_card_red_level_3_1, R.drawable.card_red_level_3_1);

        ArrayList<Integer> arrayList_price_card_red_level_3_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_3_2 = {5, 0, 3, 3, 0, 1};
        AddPriceList(arrayList_price_card_red_level_3_2, list_price_card_red_level_3_2);
        Card card_red_level_3_2 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 3, 1, 2, arrayList_price_card_red_level_3_2, R.drawable.card_red_level_3_2);

        ArrayList<Integer> arrayList_price_card_ultra_level_3_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_3_1 = {0, 0, 0, 8, 0, 0};
        AddPriceList(arrayList_price_card_ultra_level_3_1, list_price_card_ultra_level_3_1);
        Card card_ultra_level_3_1 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 3, 1, 0, arrayList_price_card_ultra_level_3_1, R.drawable.card_ultra_level_3_1);

        ArrayList<Integer> arrayList_price_card_ultra_level_3_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_3_2 = {0, 0, 0, 8, 0, 0};
        AddPriceList(arrayList_price_card_ultra_level_3_2, list_price_card_ultra_level_3_2);
        Card card_ultra_level_3_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 3, 1, 3, arrayList_price_card_ultra_level_3_1, R.drawable.card_ultra_level_3_2);

        ArrayList<Integer> arrayList_price_card_white_level_3_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_3_1 = {2, 6, 0, 2, 0, 0};
        AddPriceList(arrayList_price_card_white_level_3_1, list_price_card_white_level_3_1);
        Card card_white_level_3_1 = AddCard(4, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 3, 1, 0, arrayList_price_card_white_level_3_1, R.drawable.card_white_level_3_1);

        ArrayList<Integer> arrayList_price_card_white_level_3_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_3_2 = {3, 0, 0, 3, 5, 1};
        AddPriceList(arrayList_price_card_white_level_3_2, list_price_card_white_level_3_2);
        Card card_white_level_3_2 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 3, 1, 2, arrayList_price_card_white_level_3_2, R.drawable.card_white_level_3_2);

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
        ArrayList<Integer> arrayList_price_card_blue_level_2_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_2_1 = {0, 0, 4, 3, 0, 0};
        AddPriceList(arrayList_price_card_blue_level_2_1, list_price_card_blue_level_2_1);
        Card card_blue_level_2_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 2, 1, 0, arrayList_price_card_blue_level_2_1, R.drawable.card_blue_level_2_1);

        ArrayList<Integer> arrayList_price_card_blue_level_2_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_2_2 = {4, 2, 0, 0, 0, 1};
        AddPriceList(arrayList_price_card_blue_level_2_2, list_price_card_blue_level_2_2);
        Card card_blue_level_2_2 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 2, 1, 0, arrayList_price_card_blue_level_2_2, R.drawable.card_blue_level_2_2);

        ArrayList<Integer> arrayList_price_card_blue_level_2_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_2_3 = {0, 2, 0, 2, 2, 1};
        AddPriceList(arrayList_price_card_blue_level_2_3, list_price_card_blue_level_2_3);
        Card card_blue_level_2_3 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 2, 1, 1, arrayList_price_card_blue_level_2_3, R.drawable.card_blue_level_2_3);

        ArrayList<Integer> arrayList_price_card_blue_level_2_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_2_4 = {0, 0, 5, 0, 2, 0};
        AddPriceList(arrayList_price_card_blue_level_2_4, list_price_card_blue_level_2_4);
        Card card_blue_level_2_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 2, 2, 0, arrayList_price_card_blue_level_2_4, R.drawable.card_blue_level_2_4);

        ArrayList<Integer> arrayList_price_card_white_level_2_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_2_1 = {4, 0, 0, 0, 3, 0};
        AddPriceList(arrayList_price_card_white_level_2_1, list_price_card_white_level_2_1);
        Card card_white_level_2_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 2, 1, 0, arrayList_price_card_white_level_2_1, R.drawable.card_white_level_2_1);

        ArrayList<Integer> arrayList_price_card_white_level_2_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_2_2 = {0, 4, 0, 2, 0, 1};
        AddPriceList(arrayList_price_card_white_level_2_2, list_price_card_white_level_2_2);
        Card card_white_level_2_2 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 2, 1, 0, arrayList_price_card_white_level_2_2, R.drawable.card_white_level_2_2);

        ArrayList<Integer> arrayList_price_card_white_level_2_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_2_3 = {0, 0, 2, 2, 2, 1};
        AddPriceList(arrayList_price_card_white_level_2_3, list_price_card_white_level_2_3);
        Card card_white_level_2_3 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 2, 1, 1, arrayList_price_card_white_level_2_3, R.drawable.card_white_level_2_3);

        ArrayList<Integer> arrayList_price_card_white_level_2_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_2_4 = {5, 0, 2, 0, 0, 0};
        AddPriceList(arrayList_price_card_white_level_2_4, list_price_card_white_level_2_4);
        Card card_white_level_2_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 2, 2, 0, arrayList_price_card_white_level_2_4, R.drawable.card_white_level_2_4);

        ArrayList<Integer> arrayList_price_card_green_level_2_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_2_1 = {0, 3, 0, 0, 4, 0};
        AddPriceList(arrayList_price_card_green_level_2_1, list_price_card_green_level_2_1);
        Card card_green_level_2_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 2, 1, 0, arrayList_price_card_green_level_2_1, R.drawable.card_green_level_2_1);

        ArrayList<Integer> arrayList_price_card_green_level_2_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_2_2 = {2, 0, 4, 0, 0, 1};
        AddPriceList(arrayList_price_card_green_level_2_2, list_price_card_green_level_2_2);
        Card card_green_level_2_2 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 2, 1, 0, arrayList_price_card_green_level_2_2, R.drawable.card_green_level_2_2);

        ArrayList<Integer> arrayList_price_card_green_level_2_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_2_3 = {2, 2, 0, 2, 0, 1};
        AddPriceList(arrayList_price_card_green_level_2_3, list_price_card_green_level_2_3);
        Card card_green_level_2_3 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 2, 1, 1, arrayList_price_card_green_level_2_3, R.drawable.card_green_level_2_3);

        ArrayList<Integer> arrayList_price_card_green_level_2_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_2_4 = {0, 0, 0, 2, 5, 0};
        AddPriceList(arrayList_price_card_green_level_2_4, list_price_card_green_level_2_4);
        Card card_green_level_2_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 2, 2, 0, arrayList_price_card_green_level_2_4, R.drawable.card_green_level_2_4);

        ArrayList<Integer> arrayList_price_card_black_level_2_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_2_1 = {0, 4, 3, 0, 0, 0};
        AddPriceList(arrayList_price_card_black_level_2_1, list_price_card_black_level_2_1);
        Card card_black_level_2_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 2, 1, 0, arrayList_price_card_black_level_2_1, R.drawable.card_black_level_2_1);

        ArrayList<Integer> arrayList_price_card_black_level_2_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_2_2 = {0, 0, 0, 4, 2, 1};
        AddPriceList(arrayList_price_card_black_level_2_2, list_price_card_black_level_2_2);
        Card card_black_level_2_2 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 2, 1, 0, arrayList_price_card_black_level_2_2, R.drawable.card_black_level_2_2);

        ArrayList<Integer> arrayList_price_card_black_level_2_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_2_3 = {2, 0, 2, 0, 2, 1};
        AddPriceList(arrayList_price_card_black_level_2_3, list_price_card_black_level_2_3);
        Card card_black_level_2_3 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 2, 1, 1, arrayList_price_card_black_level_2_3, R.drawable.card_black_level_2_3);

        ArrayList<Integer> arrayList_price_card_black_level_2_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_2_4 = {2, 5, 0, 0, 0, 0};
        AddPriceList(arrayList_price_card_black_level_2_4, list_price_card_black_level_2_4);
        Card card_black_level_2_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 2, 2, 0, arrayList_price_card_black_level_2_4, R.drawable.card_black_level_2_4);

        ArrayList<Integer> arrayList_price_card_red_level_2_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_2_1 = {3, 0, 0, 4, 0, 0};
        AddPriceList(arrayList_price_card_red_level_2_1, list_price_card_red_level_2_1);
        Card card_red_level_2_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 2, 1, 0, arrayList_price_card_red_level_2_1, R.drawable.card_red_level_2_1);

        ArrayList<Integer> arrayList_price_card_red_level_2_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_2_2 = {0, 0, 2, 0, 4, 1};
        AddPriceList(arrayList_price_card_red_level_2_2, list_price_card_red_level_2_2);
        Card card_red_level_2_2 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 2, 1, 0, arrayList_price_card_red_level_2_2, R.drawable.card_red_level_2_2);

        ArrayList<Integer> arrayList_price_card_red_level_2_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_2_3 = {2, 2, 2, 0, 0, 1};
        AddPriceList(arrayList_price_card_red_level_2_3, list_price_card_red_level_2_3);
        Card card_red_level_2_3 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 2, 1, 1, arrayList_price_card_red_level_2_3, R.drawable.card_red_level_2_3);

        ArrayList<Integer> arrayList_price_card_red_level_2_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_2_4 = {0, 0, 0, 5, 2, 0};
        AddPriceList(arrayList_price_card_red_level_2_4, list_price_card_red_level_2_4);
        Card card_red_level_2_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 2, 2, 0, arrayList_price_card_red_level_2_4, R.drawable.card_red_level_2_4);

        ArrayList<Integer> arrayList_price_card_normal_level_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_normal_level_2 = {6, 0, 0, 0, 0, 1};
        AddPriceList(arrayList_price_card_normal_level_2, list_price_card_normal_level_2);
        Card card_normal_level_2 = AddCard(5, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 2, 0, 0, arrayList_price_card_normal_level_2, R.drawable.card_normal_level_2);

        ArrayList<Integer> arrayList_price_card_ultra_level_2_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_2_1 = {6, 0, 0, 0, 0, 1};
        AddPriceList(arrayList_price_card_ultra_level_2_1, list_price_card_ultra_level_2_1);
        Card card_ultra_level_2_1 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 2, 1, 2, arrayList_price_card_ultra_level_2_1, R.drawable.card_ultra_level_2_1);

        ArrayList<Integer> arrayList_price_card_ultra_level_2_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_2_2 = {0, 0, 6, 0, 0, 1};
        AddPriceList(arrayList_price_card_ultra_level_2_2, list_price_card_ultra_level_2_2);
        Card card_ultra_level_2_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 2, 1, 2, arrayList_price_card_ultra_level_2_2, R.drawable.card_ultra_level_2_2);

        ArrayList<Integer> arrayList_price_card_ultra_level_2_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_2_3 = {0, 0, 6, 0, 0, 1};
        AddPriceList(arrayList_price_card_ultra_level_2_3, list_price_card_ultra_level_2_3);
        Card card_ultra_level_2_3 = AddCard(2, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 2, 1, 0, arrayList_price_card_ultra_level_2_3, R.drawable.card_ultra_level_2_3);

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
        ArrayList<Integer> arrayList_price_card_blue_level_1_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_1 = {0, 0, 0, 3, 2, 0};
        AddPriceList(arrayList_price_card_blue_level_1_1, list_price_card_blue_level_1_1);
        Card card_blue_level_1_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 1, 1, 0, arrayList_price_card_blue_level_1_1, R.drawable.card_blue_level_1_1);

        ArrayList<Integer> arrayList_price_card_blue_level_1_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_2 = {0, 1, 1, 1, 1, 0};
        AddPriceList(arrayList_price_card_blue_level_1_2, list_price_card_blue_level_1_2);
        Card card_blue_level_1_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 1, 1, 0, arrayList_price_card_blue_level_1_2, R.drawable.card_blue_level_1_2);

        ArrayList<Integer> arrayList_price_card_blue_level_1_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_3 = {0, 2, 0, 2, 0, 0};
        AddPriceList(arrayList_price_card_blue_level_1_3, list_price_card_blue_level_1_3);
        Card card_blue_level_1_3 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 1, 1, 0, arrayList_price_card_blue_level_1_3, R.drawable.card_blue_level_1_3);

        ArrayList<Integer> arrayList_price_card_blue_level_1_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_4 = {0, 0, 2, 0, 2, 1};
        AddPriceList(arrayList_price_card_blue_level_1_4, list_price_card_blue_level_1_4);
        Card card_blue_level_1_4 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 1, 1, 0, arrayList_price_card_blue_level_1_4, R.drawable.card_blue_level_1_4);

        ArrayList<Integer> arrayList_price_card_blue_level_1_5 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_5 = {0, 0, 3, 0, 0, 0};
        AddPriceList(arrayList_price_card_blue_level_1_5, list_price_card_blue_level_1_5);
        Card card_blue_level_1_5 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)), 1, 1, 1, arrayList_price_card_blue_level_1_5, R.drawable.card_blue_level_1_5);

        ArrayList<Integer> arrayList_price_card_white_level_1_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_1 = {0, 0, 0, 2, 2, 0};
        AddPriceList(arrayList_price_card_white_level_1_1, list_price_card_white_level_1_1);
        Card card_white_level_1_1 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 1, 1, 0, arrayList_price_card_white_level_1_1, R.drawable.card_white_level_1_1);

        ArrayList<Integer> arrayList_price_card_white_level_1_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_2 = {2, 0, 2, 0, 0, 1};
        AddPriceList(arrayList_price_card_white_level_1_2, list_price_card_white_level_1_2);
        Card card_white_level_1_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 1, 1, 0, arrayList_price_card_white_level_1_2, R.drawable.card_white_level_1_2);

        ArrayList<Integer> arrayList_price_card_white_level_1_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_3 = {1, 0, 1, 1, 1, 0};
        AddPriceList(arrayList_price_card_white_level_1_3, list_price_card_white_level_1_3);
        Card card_white_level_1_3 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 1, 1, 0, arrayList_price_card_white_level_1_3, R.drawable.card_white_level_1_3);

        ArrayList<Integer> arrayList_price_card_white_level_1_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_4 = {0, 0, 2, 0, 3, 0};
        AddPriceList(arrayList_price_card_white_level_1_4, list_price_card_white_level_1_4);
        Card card_white_level_1_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 1, 1, 0, arrayList_price_card_white_level_1_4, R.drawable.card_white_level_1_4);

        ArrayList<Integer> arrayList_price_card_white_level_1_5 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_5 = {3, 0, 0, 0, 0, 0};
        AddPriceList(arrayList_price_card_white_level_1_5, list_price_card_white_level_1_5);
        Card card_white_level_1_5 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.white)), 1, 1, 1, arrayList_price_card_white_level_1_5, R.drawable.card_white_level_1_5);

        ArrayList<Integer> arrayList_price_card_green_level_1_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_1 = {2, 2, 0, 0, 0, 0};
        AddPriceList(arrayList_price_card_green_level_1_1, list_price_card_green_level_1_1);
        Card card_green_level_1_1 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 1, 1, 0, arrayList_price_card_green_level_1_1, R.drawable.card_green_level_1_1);

        ArrayList<Integer> arrayList_price_card_green_level_1_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_2 = {0, 0, 0, 2, 2, 1};
        AddPriceList(arrayList_price_card_green_level_1_2, list_price_card_green_level_1_2);
        Card card_green_level_1_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 1, 1, 0, arrayList_price_card_green_level_1_2, R.drawable.card_green_level_1_2);

        ArrayList<Integer> arrayList_price_card_green_level_1_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_3 = {1, 1, 0, 1, 1, 0};
        AddPriceList(arrayList_price_card_green_level_1_3, list_price_card_green_level_1_3);
        Card card_green_level_1_3 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 1, 1, 0, arrayList_price_card_green_level_1_3, R.drawable.card_green_level_1_3);

        ArrayList<Integer> arrayList_price_card_green_level_1_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_4 = {0, 3, 0, 2, 0, 0};
        AddPriceList(arrayList_price_card_green_level_1_4, list_price_card_green_level_1_4);
        Card card_green_level_1_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 1, 1, 0, arrayList_price_card_green_level_1_4, R.drawable.card_green_level_1_4);

        ArrayList<Integer> arrayList_price_card_green_level_1_5 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_5 = {0, 0, 0, 0, 3, 0};
        AddPriceList(arrayList_price_card_green_level_1_5, list_price_card_green_level_1_5);
        Card card_green_level_1_5 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)), 1, 1, 1, arrayList_price_card_green_level_1_5, R.drawable.card_green_level_1_5);

        ArrayList<Integer> arrayList_price_card_black_level_1_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_1 = {0, 0, 2, 0, 2, 0};
        AddPriceList(arrayList_price_card_black_level_1_1, list_price_card_black_level_1_1);
        Card card_black_level_1_1 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 1, 1, 0, arrayList_price_card_black_level_1_1, R.drawable.card_black_level_1_1);

        ArrayList<Integer> arrayList_price_card_black_level_1_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_2 = {2, 2, 0, 0, 0, 1};
        AddPriceList(arrayList_price_card_black_level_1_2, list_price_card_black_level_1_2);
        Card card_black_level_1_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 1, 1, 0, arrayList_price_card_black_level_1_2, R.drawable.card_black_level_1_2);

        ArrayList<Integer> arrayList_price_card_black_level_1_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_3 = {1, 1, 1, 0, 1, 0};
        AddPriceList(arrayList_price_card_black_level_1_3, list_price_card_black_level_1_3);
        Card card_black_level_1_3 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 1, 1, 0, arrayList_price_card_black_level_1_3, R.drawable.card_black_level_1_3);

        ArrayList<Integer> arrayList_price_card_black_level_1_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_4 = {2, 0, 3, 0, 0, 0};
        AddPriceList(arrayList_price_card_black_level_1_4, list_price_card_black_level_1_4);
        Card card_black_level_1_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 1, 1, 0, arrayList_price_card_black_level_1_4, R.drawable.card_black_level_1_4);

        ArrayList<Integer> arrayList_price_card_black_level_1_5 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_5 = {0, 3, 0, 0, 0, 0};
        AddPriceList(arrayList_price_card_black_level_1_5, list_price_card_black_level_1_5);
        Card card_black_level_1_5 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.black)), 1, 1, 1, arrayList_price_card_black_level_1_5, R.drawable.card_black_level_1_5);

        ArrayList<Integer> arrayList_price_card_red_level_1_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_1 = {2, 0, 2, 0, 0, 0};
        AddPriceList(arrayList_price_card_red_level_1_1, list_price_card_red_level_1_1);
        Card card_red_level_1_1 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 1, 1, 0, arrayList_price_card_red_level_1_1, R.drawable.card_red_level_1_1);

        ArrayList<Integer> arrayList_price_card_red_level_1_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_2 = {0, 2, 0, 2, 0, 1};
        AddPriceList(arrayList_price_card_red_level_1_2, list_price_card_red_level_1_2);
        Card card_red_level_1_2 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 1, 1, 0, arrayList_price_card_red_level_1_2, R.drawable.card_red_level_1_2);

        ArrayList<Integer> arrayList_price_card_red_level_1_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_3 = {1, 1, 1, 1, 0, 0};
        AddPriceList(arrayList_price_card_red_level_1_3, list_price_card_red_level_1_3);
        Card card_red_level_1_3 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 1, 1,0, arrayList_price_card_red_level_1_3, R.drawable.card_red_level_1_3);

        ArrayList<Integer> arrayList_price_card_red_level_1_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_4 = {3, 2, 0, 0, 0, 0};
        AddPriceList(arrayList_price_card_red_level_1_4, list_price_card_red_level_1_4);
        Card card_red_level_1_4 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 1, 1, 0, arrayList_price_card_red_level_1_4, R.drawable.card_red_level_1_4);

        ArrayList<Integer> arrayList_price_card_red_level_1_5 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_5 = {0, 0, 0, 3, 0, 0};
        AddPriceList(arrayList_price_card_red_level_1_5, list_price_card_red_level_1_5);
        Card card_red_level_1_5 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)), 1, 1, 1, arrayList_price_card_red_level_1_5, R.drawable.card_red_level_1_5);

        ArrayList<Integer> arrayList_price_card_normal_level_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_normal_level_1 = {0, 0, 0, 0, 4, 1};
        AddPriceList(arrayList_price_card_normal_level_1, list_price_card_normal_level_1);
        Card card_normal_level_1 = AddCard(3, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 1, 0, 0, arrayList_price_card_normal_level_1, R.drawable.card_normal_level_1);

        ArrayList<Integer> arrayList_price_card_ultra_level_1_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_1_1 = {0, 0, 0, 4, 0, 1};
        AddPriceList(arrayList_price_card_ultra_level_1_1, list_price_card_ultra_level_1_1);
        Card card_ultra_level_1_1 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 1, 1, 0, arrayList_price_card_ultra_level_1_1, R.drawable.card_ultra_level_1_1);

        ArrayList<Integer> arrayList_price_card_ultra_level_1_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_1_2 = {0, 2, 2, 1, 0, 1};
        AddPriceList(arrayList_price_card_ultra_level_1_2, list_price_card_ultra_level_1_2);
        Card card_ultra_level_1_2 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 1, 1, 0, arrayList_price_card_ultra_level_1_2, R.drawable.card_ultra_level_1_2);

        ArrayList<Integer> arrayList_price_card_ultra_level_1_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_1_3 = {2, 0, 0, 1, 2, 1};
        AddPriceList(arrayList_price_card_ultra_level_1_3, list_price_card_ultra_level_1_3);
        Card card_ultra_level_1_3 = AddCard(1, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 1,1, 0, arrayList_price_card_ultra_level_1_3, R.drawable.card_ultra_level_1_3);

        ArrayList<Integer> arrayList_price_card_ultra_level_1_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_1_4 = {0, 4, 0, 0, 0, 1};
        AddPriceList(arrayList_price_card_ultra_level_1_4, list_price_card_ultra_level_1_4);
        Card card_ultra_level_1_4 = AddCard(0, Color.valueOf(mainActivity.getResources().getColor(R.color.color4normalToken)), 1, 1, 1, arrayList_price_card_ultra_level_1_4, R.drawable.card_ultra_level_1_4);

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

    private Card AddCard(int cardValue, Color color, int level, int discount, int crowns, ArrayList<Integer> price, int image){
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

    public void AddPriceList(ArrayList<Integer> myListPrice, int[] listPrice){
        for (int i=0; i < listPrice.length; i++){
            myListPrice.add(listPrice[i]);
        }
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
