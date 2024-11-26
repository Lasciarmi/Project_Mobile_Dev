package com.example.mainboardsplendor.controller;

import android.graphics.Color;

import com.example.mainboardsplendor.R;
import com.example.mainboardsplendor.databinding.LayoutCardBoardBinding;
import com.example.mainboardsplendor.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardController {

    LayoutCardBoardBinding cardStoreTop;
    LayoutCardBoardBinding cardStoreMid;
    LayoutCardBoardBinding cardStoreBot;

    private List<Card> listCardLevel3 = new ArrayList<>();

    public CardController(LayoutCardBoardBinding cardStoreTop, LayoutCardBoardBinding cardStoreMid, LayoutCardBoardBinding cardStoreBot) {
        this.cardStoreTop = cardStoreTop;
        this.cardStoreMid = cardStoreMid;
        this.cardStoreBot = cardStoreBot;
    }

//    public void InitCardTopDeck(List<Card> listCardLevel3) {
//        ArrayList<Integer> arrayList_price_card_black_level_3_1 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_black_level_3_1 = {0, 2, 0, 6, 2, 0};
//        AddPriceList(arrayList_price_card_black_level_3_1, list_price_card_black_level_3_1);
//        Card card_black_level_3_1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.black)), 3, 1, 0, arrayList_price_card_black_level_3_1, R.drawable.card_black_level_3_1);
//
//        ArrayList<Integer> arrayList_price_card_black_level_3_2 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_black_level_3_2 = {0, 3, 5, 0, 3, 1};
//        AddPriceList(arrayList_price_card_black_level_3_2, list_price_card_black_level_3_2);
//        Card card_black_level_3_2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.black)), 3, 1, 2, arrayList_price_card_black_level_3_2, R.drawable.card_black_level_3_2);
//
//        ArrayList<Integer> arrayList_price_card_blue_level_3_1 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_blue_level_3_1 = {6, 2, 2, 0, 0, 0};
//        AddPriceList(arrayList_price_card_blue_level_3_1, list_price_card_blue_level_3_1);
//        Card card_blue_level_3_1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 3, 1, 0, arrayList_price_card_blue_level_3_1, R.drawable.card_blue_level_3_1);
//
//        ArrayList<Integer> arrayList_price_card_blue_level_3_2 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_blue_level_3_2 = {0, 3, 3, 5, 0, 1};
//        AddPriceList(arrayList_price_card_blue_level_3_2, list_price_card_blue_level_3_2);
//        Card card_blue_level3_2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 3, 1, 2, arrayList_price_card_blue_level_3_2, R.drawable.card_blue_level_3_2);
//
//        ArrayList<Integer> arrayList_price_card_green_level_3_1 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_green_level_3_1 = {2, 0, 6, 0, 2, 0};
//        AddPriceList(arrayList_price_card_green_level_3_1, list_price_card_green_level_3_1);
//        Card card_green_level_3_1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 3, 1, 0, arrayList_price_card_green_level_3_1, R.drawable.card_green_level_3_1);
//
//        ArrayList<Integer> arrayList_price_card_green_level_3_2 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_green_level_3_2 = {3, 5, 0, 0, 3, 1};
//        AddPriceList(arrayList_price_card_green_level_3_2, list_price_card_green_level_3_2);
//        Card card_green_level_3_2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 3, 1, 2, arrayList_price_card_green_level_3_2, R.drawable.card_green_level_3_2);
//
//        ArrayList<Integer> arrayList_price_card_normal_level_3 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_normal_level_3 = {0, 8, 0, 0, 0, 0};
//        AddPriceList(arrayList_price_card_normal_level_3, list_price_card_normal_level_3);
//        Card card_normal_level_3 = AddCard(6, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 3, 0, 0, arrayList_price_card_normal_level_3, R.drawable.card_normal_level_3);
//
//        ArrayList<Integer> arrayList_price_card_red_level_3_1 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_red_level_3_1 = {0, 0, 2, 2, 6, 0};
//        AddPriceList(arrayList_price_card_red_level_3_1, list_price_card_red_level_3_1);
//        Card card_red_level_3_1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.color4redToken)), 3, 1, 0, arrayList_price_card_red_level_3_1, R.drawable.card_red_level_3_1);
//
//        ArrayList<Integer> arrayList_price_card_red_level_3_2 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_red_level_3_2 = {5, 0, 3, 3, 0, 1};
//        AddPriceList(arrayList_price_card_red_level_3_2, list_price_card_red_level_3_2);
//        Card card_red_level_3_2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4redToken)), 3, 1, 2, arrayList_price_card_red_level_3_2, R.drawable.card_red_level_3_2);
//
//        ArrayList<Integer> arrayList_price_card_ultra_level_3_1 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_ultra_level_3_1 = {0, 0, 0, 8, 0, 0};
//        AddPriceList(arrayList_price_card_ultra_level_3_1, list_price_card_ultra_level_3_1);
//        Card card_ultra_level_3_1 = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 3, 1, 0, arrayList_price_card_ultra_level_3_1, R.drawable.card_ultra_level_3_1);
//
//        ArrayList<Integer> arrayList_price_card_ultra_level_3_2 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_ultra_level_3_2 = {0, 0, 0, 8, 0, 0};
//        AddPriceList(arrayList_price_card_ultra_level_3_2, list_price_card_ultra_level_3_2);
//        Card card_ultra_level_3_2 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 3, 1, 3, arrayList_price_card_ultra_level_3_1, R.drawable.card_ultra_level_3_2);
//
//        ArrayList<Integer> arrayList_price_card_white_level_3_1 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_white_level_3_1 = {2, 6, 0, 2, 0, 0};
//        AddPriceList(arrayList_price_card_white_level_3_1, list_price_card_white_level_3_1);
//        Card card_white_level_3_1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.white)), 3, 1, 0, arrayList_price_card_white_level_3_1, R.drawable.card_white_level_3_1);
//
//        ArrayList<Integer> arrayList_price_card_white_level_3_2 = new ArrayList<>();
//        // seq: [blue, white, green, black, red, pearl]
//        int[] list_price_card_white_level_3_2 = {3, 0, 0, 3, 5, 1};
//        AddPriceList(arrayList_price_card_white_level_3_2, list_price_card_white_level_3_2);
//        Card card_white_level_3_2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.white)), 3, 1, 2, arrayList_price_card_white_level_3_2, R.drawable.card_white_level_3_2);
//
//        // Total 13 Cards
//        listCardLevel3.add(card_black_level_3_1);
//        listCardLevel3.add(card_black_level_3_2);
//        listCardLevel3.add(card_blue_level_3_1);
//        listCardLevel3.add(card_blue_level3_2);
//        listCardLevel3.add(card_green_level_3_1);
//        listCardLevel3.add(card_green_level_3_2);
//        listCardLevel3.add(card_normal_level_3);
//        listCardLevel3.add(card_red_level_3_1);
//        listCardLevel3.add(card_red_level_3_2);
//        listCardLevel3.add(card_ultra_level_3_1);
//        listCardLevel3.add(card_ultra_level_3_2);
//        listCardLevel3.add(card_white_level_3_1);
//        listCardLevel3.add(card_white_level_3_2);
//    }
}
