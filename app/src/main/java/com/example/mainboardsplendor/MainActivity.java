package com.example.mainboardsplendor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mainboardsplendor.controller.CardController;
import com.example.mainboardsplendor.controller.TokenController;
import com.example.mainboardsplendor.controller.UserController;
import com.example.mainboardsplendor.databinding.ActivityMainBinding;
import com.example.mainboardsplendor.model.Card;
import com.example.mainboardsplendor.model.RoyalCard;
import com.example.mainboardsplendor.model.Token;
import com.example.mainboardsplendor.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

//    private int quantityCardLevel3 = 13;
//    private int quantityCardLevel2 = 24;
//    private int quantityCardLevel1 = 30;

    private List<Token> tokenBag = new ArrayList<>();
    private List<Card> listCardLevel3 = new ArrayList<>();
    private List<Card> listCardLevel2 = new ArrayList<>();
    private List<Card> listCardLevel1 = new ArrayList<>();
    private List<RoyalCard> listRoyalCard = new ArrayList<>();

    private User user1;
    private User user2;

    private TokenController tokenController;
    private UserController user1Controller;
    private UserController user2Controller;
    private CardController cardController;

    private GridLayout tokenGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ActivityMainBinding binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Get Player 1 and 2 Username
        Intent intent = getIntent();
        user1 = new User(intent.getStringExtra(CreateUserActivity.PLAYER_1));
        user2 = new User(intent.getStringExtra(CreateUserActivity.PLAYER_2));

        // Init name player
        user1Controller = new UserController(user1, binding.scoreBoardPlayer1, binding.layoutPlayer1Bag);
        user2Controller = new UserController(user2, binding.scoreBoardPlayer2, binding.layoutPlayer2Bag);
        user1Controller.setPlayerBoard();
        user2Controller.setPlayerBoard();

        // Init tokenBag
        tokenController = new TokenController(tokenBag, this, this);
        tokenController.initTokenBag();

        // Init TokenBoard in Spiral
        tokenGridLayout = binding.tokenBoard.splendorDuelBoard;
        int rowCount =tokenGridLayout.getRowCount();
        int colCount = tokenGridLayout.getColumnCount();
        tokenController.InitTokenBoard(rowCount, colCount, tokenGridLayout);
        for (int i = 0; i < 25; i++) {
            View parentView = tokenGridLayout.getChildAt(i);

            if (parentView instanceof androidx.cardview.widget.CardView) {
                // Now find the Token view inside the CardView
                Token token = parentView.findViewById(R.id.token_view);

                if (token != null) {
                    ArrayList<Integer> location = token.getLocation();

                    // Check if location is not null before logging
                    if (location != null) {
                        Log.d("MainActivity", "Token found at index " + i + ": " + location.toString());
                    } else {
                        Log.e("MainActivity", "Location is null for token at index " + i);
                    }
                } else {
                    Log.e("MainActivity", "Token view not found at index " + i);
                }
            } else {
                Log.e("MainActivity", "Child at index " + i + " is not a CardView");
            }
        }


        // get GridLayout for each cardBoard level
//        GridLayout cardBoard_level3 = binding.cardBoard.cardStoreTop;
//        GridLayout cardBoard_level2 = binding.cardBoard.cardStoreMid;
//        GridLayout cardBoard_level1 = binding.cardBoard.cardStoreBot;
        // Init Card on Deck
//        InitCardTopDeck(listCardLevel3);
//        InitCardMidDeck(listCardLevel2);
//        InitCardBotDeck(listCardLevel1);
//        // Init Card on Board
//        InitCardBoard(listCardLevel3, cardBoard_level3, 3);
//        InitCardBoard(listCardLevel2, cardBoard_level2, 4);
//        InitCardBoard(listCardLevel1, cardBoard_level1, 5);
        //Init Card Reserved on Board
        InitReservedCard(listRoyalCard);
        InitReservedCardBoard(listRoyalCard, binding.cardBoard.reservedCard, 4);

        // Init Grid list_blue_token pada bag player
        for (int i = 0; i< 4; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listBlueToken.listToken, false);
            ImageView tokenView = view.findViewById(R.id.token_view);
            CardView cardView = view.findViewById(R.id.cardView_token);

            // Set Image token
            tokenView.setImageResource(R.drawable.blue_token);
            // Set bg CardView token
            int color4blueToken = ContextCompat.getColor(this, R.color.color4blueToken);
            cardView.setCardBackgroundColor(color4blueToken);
            binding.layoutPlayer1Bag.listBlueToken.listToken.addView(view);
        }

        // Init Grid list_white_token pada bag player
        for (int i = 0; i< 4; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listWhiteToken.listToken, false);
            ImageView tokenView = view.findViewById(R.id.token_view);
            CardView cardView = view.findViewById(R.id.cardView_token);

            // Set Image token
            tokenView.setImageResource(R.drawable.white_token);
            // Set bg CardView token
            int color4whiteToken = ContextCompat.getColor(this, R.color.white);
            cardView.setCardBackgroundColor(color4whiteToken);
            binding.layoutPlayer1Bag.listWhiteToken.listToken.addView(view);
        }

        // Init Grid list_green_token pada bag player
        for (int i = 0; i< 4; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listGreenToken.listToken, false);
            ImageView tokenView = view.findViewById(R.id.token_view);
            CardView cardView = view.findViewById(R.id.cardView_token);

            // Set Image token
            tokenView.setImageResource(R.drawable.green_token);
            // Set bg CardView token
            int color4greenToken = ContextCompat.getColor(this, R.color.color4greenToken);
            cardView.setCardBackgroundColor(color4greenToken);
            binding.layoutPlayer1Bag.listGreenToken.listToken.addView(view);
        }

        // Init Grid list_black_token pada bag player
        for (int i = 0; i< 4; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listBlackToken.listToken, false);
            ImageView tokenView = view.findViewById(R.id.token_view);
            CardView cardView = view.findViewById(R.id.cardView_token);

            // Set Image token
            tokenView.setImageResource(R.drawable.black_token);
            // Set bg CardView token
            int color4blackToken = ContextCompat.getColor(this, R.color.black);
            cardView.setCardBackgroundColor(color4blackToken);
            binding.layoutPlayer1Bag.listBlackToken.listToken.addView(view);
        }

        // Init Grid list_red_token pada bag player
        for (int i = 0; i< 4; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listRedToken.listToken, false);
            ImageView tokenView = view.findViewById(R.id.token_view);
            CardView cardView = view.findViewById(R.id.cardView_token);

            // Set Image token
            tokenView.setImageResource(R.drawable.red_token);
            // Set bg CardView token
            int color4redToken = ContextCompat.getColor(this, R.color.color4redToken);
            cardView.setCardBackgroundColor(color4redToken);
            binding.layoutPlayer1Bag.listRedToken.listToken.addView(view);
        }

        for (int i = 0; i< 2; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listPearlToken.listToken, false);
            ImageView tokenView = view.findViewById(R.id.token_view);
            CardView cardView = view.findViewById(R.id.cardView_token);

            // Set Image token
            tokenView.setImageResource(R.drawable.pearl_token);
            // Set bg CardView token
            int color4pearlToken = ContextCompat.getColor(this, R.color.color4pearlToken);
            cardView.setCardBackgroundColor(color4pearlToken);
            binding.layoutPlayer1Bag.listPearlToken.listToken.addView(view);
            // TODO making reverse layout for all list token
        }

        // INIT CARD_STACK
        for (int i=0; i<5; i++){
            ImageView card = new ImageView(this);
            card.setImageResource(R.drawable.blank_card); // Ganti dengan Kartu Token
            card.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            ));
            card.setTranslationY(i * 70); // Mengatur jarak Y antar Kartu

            binding.layoutPlayer1Bag.blueCardStack.addView(card);
        }

        for (int i=0; i<5; i++){
            ImageView card = new ImageView(this);
            card.setImageResource(R.drawable.blank_card); // Ganti dengan Kartu Token
            card.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            ));
            card.setTranslationY(i * 70); // Mengatur jarak Y antar Kartu

            binding.layoutPlayer1Bag.whiteCardStack.addView(card);
        }

        for (int i=0; i<5; i++){
            ImageView card = new ImageView(this);
            card.setImageResource(R.drawable.blank_card); // Ganti dengan Kartu Token
            card.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            ));
            card.setTranslationY(i * 70); // Mengatur jarak Y antar Kartu

            binding.layoutPlayer1Bag.greenCardStack.addView(card);
        }

        for (int i=0; i<5; i++){
            ImageView card = new ImageView(this);
            card.setImageResource(R.drawable.blank_card); // Ganti dengan Kartu Token
            card.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            ));
            card.setTranslationY(i * 70); // Mengatur jarak Y antar Kartu

            binding.layoutPlayer1Bag.blackCardStack.addView(card);
        }

        for (int i = 0; i < 3; i++){
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(5, 0, 5, 0);

            ImageView card = new ImageView(this);
            card.setImageResource(R.drawable.blank_card);
            card.setLayoutParams(params);

            binding.layoutPlayer1Bag.cardReservedPlayer.addView(card);
        }

        addNewCard(binding.layoutPlayer1Bag.redCardStack);
        addNewCard(binding.layoutPlayer1Bag.redCardStack);
        addNewCard(binding.layoutPlayer1Bag.redCardStack);
        addNewCard(binding.layoutPlayer1Bag.redCardStack);


        binding.layoutPlayer1Bag.playerNameBot.setText("MC");
        binding.layoutPlayer2Bag.playerNameBot.setText("Theo");

        binding.taskBar.taskBarTakeGems.setVisibility(View.INVISIBLE);
        binding.taskBar.taskBarUsePrivilege.setVisibility(View.GONE);
    }

    private void InitCardMidDeck(List<Card> listCardLevel2) {
        ArrayList<Integer> arrayList_price_card_blue_level_2_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_2_1 = {0, 0, 4, 3, 0, 0};
        AddPriceList(arrayList_price_card_blue_level_2_1, list_price_card_blue_level_2_1);
        Card card_blue_level_2_1 = AddCard(1, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 2, 1, 0, arrayList_price_card_blue_level_2_1, R.drawable.card_blue_level_2_1);

        ArrayList<Integer> arrayList_price_card_blue_level_2_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_2_2 = {4, 2, 0, 0, 0, 1};
        AddPriceList(arrayList_price_card_blue_level_2_2, list_price_card_blue_level_2_2);
        Card card_blue_level_2_2 = AddCard(2, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 2, 1, 0, arrayList_price_card_blue_level_2_2, R.drawable.card_blue_level_2_2);

        ArrayList<Integer> arrayList_price_card_blue_level_2_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_2_3 = {0, 2, 0, 2, 2, 1};
        AddPriceList(arrayList_price_card_blue_level_2_3, list_price_card_blue_level_2_3);
        Card card_blue_level_2_3 = AddCard(2, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 2, 1, 1, arrayList_price_card_blue_level_2_3, R.drawable.card_blue_level_2_3);

        ArrayList<Integer> arrayList_price_card_blue_level_2_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_2_4 = {0, 0, 5, 0, 2, 0};
        AddPriceList(arrayList_price_card_blue_level_2_4, list_price_card_blue_level_2_4);
        Card card_blue_level_2_4 = AddCard(1, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 2, 2, 0, arrayList_price_card_blue_level_2_4, R.drawable.card_blue_level_2_4);

        ArrayList<Integer> arrayList_price_card_white_level_2_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_2_1 = {4, 0, 0, 0, 3, 0};
        AddPriceList(arrayList_price_card_white_level_2_1, list_price_card_white_level_2_1);
        Card card_white_level_2_1 = AddCard(1, Color.valueOf(getResources().getColor(R.color.white)), 2, 1, 0, arrayList_price_card_white_level_2_1, R.drawable.card_white_level_2_1);

        ArrayList<Integer> arrayList_price_card_white_level_2_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_2_2 = {0, 4, 0, 2, 0, 1};
        AddPriceList(arrayList_price_card_white_level_2_2, list_price_card_white_level_2_2);
        Card card_white_level_2_2 = AddCard(2, Color.valueOf(getResources().getColor(R.color.white)), 2, 1, 0, arrayList_price_card_white_level_2_2, R.drawable.card_white_level_2_2);

        ArrayList<Integer> arrayList_price_card_white_level_2_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_2_3 = {0, 0, 2, 2, 2, 1};
        AddPriceList(arrayList_price_card_white_level_2_3, list_price_card_white_level_2_3);
        Card card_white_level_2_3 = AddCard(2, Color.valueOf(getResources().getColor(R.color.white)), 2, 1, 1, arrayList_price_card_white_level_2_3, R.drawable.card_white_level_2_3);

        ArrayList<Integer> arrayList_price_card_white_level_2_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_2_4 = {5, 0, 2, 0, 0, 0};
        AddPriceList(arrayList_price_card_white_level_2_4, list_price_card_white_level_2_4);
        Card card_white_level_2_4 = AddCard(1, Color.valueOf(getResources().getColor(R.color.white)), 2, 2, 0, arrayList_price_card_white_level_2_4, R.drawable.card_white_level_2_4);

        ArrayList<Integer> arrayList_price_card_green_level_2_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_2_1 = {0, 3, 0, 0, 4, 0};
        AddPriceList(arrayList_price_card_green_level_2_1, list_price_card_green_level_2_1);
        Card card_green_level_2_1 = AddCard(1, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 2, 1, 0, arrayList_price_card_green_level_2_1, R.drawable.card_green_level_2_1);

        ArrayList<Integer> arrayList_price_card_green_level_2_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_2_2 = {2, 0, 4, 0, 0, 1};
        AddPriceList(arrayList_price_card_green_level_2_2, list_price_card_green_level_2_2);
        Card card_green_level_2_2 = AddCard(2, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 2, 1, 0, arrayList_price_card_green_level_2_2, R.drawable.card_green_level_2_2);

        ArrayList<Integer> arrayList_price_card_green_level_2_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_2_3 = {2, 2, 0, 2, 0, 1};
        AddPriceList(arrayList_price_card_green_level_2_3, list_price_card_green_level_2_3);
        Card card_green_level_2_3 = AddCard(2, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 2, 1, 1, arrayList_price_card_green_level_2_3, R.drawable.card_green_level_2_3);

        ArrayList<Integer> arrayList_price_card_green_level_2_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_2_4 = {0, 0, 0, 2, 5, 0};
        AddPriceList(arrayList_price_card_green_level_2_4, list_price_card_green_level_2_4);
        Card card_green_level_2_4 = AddCard(1, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 2, 2, 0, arrayList_price_card_green_level_2_4, R.drawable.card_green_level_2_4);

        ArrayList<Integer> arrayList_price_card_black_level_2_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_2_1 = {0, 4, 3, 0, 0, 0};
        AddPriceList(arrayList_price_card_black_level_2_1, list_price_card_black_level_2_1);
        Card card_black_level_2_1 = AddCard(1, Color.valueOf(getResources().getColor(R.color.black)), 2, 1, 0, arrayList_price_card_black_level_2_1, R.drawable.card_black_level_2_1);

        ArrayList<Integer> arrayList_price_card_black_level_2_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_2_2 = {0, 0, 0, 4, 2, 1};
        AddPriceList(arrayList_price_card_black_level_2_2, list_price_card_black_level_2_2);
        Card card_black_level_2_2 = AddCard(2, Color.valueOf(getResources().getColor(R.color.black)), 2, 1, 0, arrayList_price_card_black_level_2_2, R.drawable.card_black_level_2_2);

        ArrayList<Integer> arrayList_price_card_black_level_2_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_2_3 = {2, 0, 2, 0, 2, 1};
        AddPriceList(arrayList_price_card_black_level_2_3, list_price_card_black_level_2_3);
        Card card_black_level_2_3 = AddCard(2, Color.valueOf(getResources().getColor(R.color.black)), 2, 1, 1, arrayList_price_card_black_level_2_3, R.drawable.card_black_level_2_3);

        ArrayList<Integer> arrayList_price_card_black_level_2_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_2_4 = {2, 5, 0, 0, 0, 0};
        AddPriceList(arrayList_price_card_black_level_2_4, list_price_card_black_level_2_4);
        Card card_black_level_2_4 = AddCard(1, Color.valueOf(getResources().getColor(R.color.black)), 2, 2, 0, arrayList_price_card_black_level_2_4, R.drawable.card_black_level_2_4);

        ArrayList<Integer> arrayList_price_card_red_level_2_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_2_1 = {3, 0, 0, 4, 0, 0};
        AddPriceList(arrayList_price_card_red_level_2_1, list_price_card_red_level_2_1);
        Card card_red_level_2_1 = AddCard(1, Color.valueOf(getResources().getColor(R.color.color4redToken)), 2, 1, 0, arrayList_price_card_red_level_2_1, R.drawable.card_red_level_2_1);

        ArrayList<Integer> arrayList_price_card_red_level_2_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_2_2 = {0, 0, 2, 0, 4, 1};
        AddPriceList(arrayList_price_card_red_level_2_2, list_price_card_red_level_2_2);
        Card card_red_level_2_2 = AddCard(2, Color.valueOf(getResources().getColor(R.color.color4redToken)), 2, 1, 0, arrayList_price_card_red_level_2_2, R.drawable.card_red_level_2_2);

        ArrayList<Integer> arrayList_price_card_red_level_2_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_2_3 = {2, 2, 2, 0, 0, 1};
        AddPriceList(arrayList_price_card_red_level_2_3, list_price_card_red_level_2_3);
        Card card_red_level_2_3 = AddCard(2, Color.valueOf(getResources().getColor(R.color.color4redToken)), 2, 1, 1, arrayList_price_card_red_level_2_3, R.drawable.card_red_level_2_3);

        ArrayList<Integer> arrayList_price_card_red_level_2_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_2_4 = {0, 0, 0, 5, 2, 0};
        AddPriceList(arrayList_price_card_red_level_2_4, list_price_card_red_level_2_4);
        Card card_red_level_2_4 = AddCard(1, Color.valueOf(getResources().getColor(R.color.color4redToken)), 2, 2, 0, arrayList_price_card_red_level_2_4, R.drawable.card_red_level_2_4);

        ArrayList<Integer> arrayList_price_card_normal_level_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_normal_level_2 = {6, 0, 0, 0, 0, 1};
        AddPriceList(arrayList_price_card_normal_level_2, list_price_card_normal_level_2);
        Card card_normal_level_2 = AddCard(5, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 2, 0, 0, arrayList_price_card_normal_level_2, R.drawable.card_normal_level_2);

        ArrayList<Integer> arrayList_price_card_ultra_level_2_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_2_1 = {6, 0, 0, 0, 0, 1};
        AddPriceList(arrayList_price_card_ultra_level_2_1, list_price_card_ultra_level_2_1);
        Card card_ultra_level_2_1 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 2, 1, 2, arrayList_price_card_ultra_level_2_1, R.drawable.card_ultra_level_2_1);

        ArrayList<Integer> arrayList_price_card_ultra_level_2_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_2_2 = {0, 0, 6, 0, 0, 1};
        AddPriceList(arrayList_price_card_ultra_level_2_2, list_price_card_ultra_level_2_2);
        Card card_ultra_level_2_2 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 2, 1, 2, arrayList_price_card_ultra_level_2_2, R.drawable.card_ultra_level_2_2);

        ArrayList<Integer> arrayList_price_card_ultra_level_2_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_2_3 = {0, 0, 6, 0, 0, 1};
        AddPriceList(arrayList_price_card_ultra_level_2_3, list_price_card_ultra_level_2_3);
        Card card_ultra_level_2_3 = AddCard(2, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 2, 1, 0, arrayList_price_card_ultra_level_2_3, R.drawable.card_ultra_level_2_3);

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

    private void InitCardBotDeck(List<Card> listCardLevel1) {
        ArrayList<Integer> arrayList_price_card_blue_level_1_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_1 = {0, 0, 0, 3, 2, 0};
        AddPriceList(arrayList_price_card_blue_level_1_1, list_price_card_blue_level_1_1);
        Card card_blue_level_1_1 = AddCard(1, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 1, 1, 0, arrayList_price_card_blue_level_1_1, R.drawable.card_blue_level_1_1);

        ArrayList<Integer> arrayList_price_card_blue_level_1_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_2 = {0, 1, 1, 1, 1, 0};
        AddPriceList(arrayList_price_card_blue_level_1_2, list_price_card_blue_level_1_2);
        Card card_blue_level_1_2 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 1, 1, 0, arrayList_price_card_blue_level_1_2, R.drawable.card_blue_level_1_2);

        ArrayList<Integer> arrayList_price_card_blue_level_1_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_3 = {0, 2, 0, 2, 0, 0};
        AddPriceList(arrayList_price_card_blue_level_1_3, list_price_card_blue_level_1_3);
        Card card_blue_level_1_3 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 1, 1, 0, arrayList_price_card_blue_level_1_3, R.drawable.card_blue_level_1_3);

        ArrayList<Integer> arrayList_price_card_blue_level_1_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_4 = {0, 0, 2, 0, 2, 1};
        AddPriceList(arrayList_price_card_blue_level_1_4, list_price_card_blue_level_1_4);
        Card card_blue_level_1_4 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 1, 1, 0, arrayList_price_card_blue_level_1_4, R.drawable.card_blue_level_1_4);

        ArrayList<Integer> arrayList_price_card_blue_level_1_5 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_1_5 = {0, 0, 3, 0, 0, 0};
        AddPriceList(arrayList_price_card_blue_level_1_5, list_price_card_blue_level_1_5);
        Card card_blue_level_1_5 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 1, 1, 1, arrayList_price_card_blue_level_1_5, R.drawable.card_blue_level_1_5);

        ArrayList<Integer> arrayList_price_card_white_level_1_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_1 = {0, 0, 0, 2, 2, 0};
        AddPriceList(arrayList_price_card_white_level_1_1, list_price_card_white_level_1_1);
        Card card_white_level_1_1 = AddCard(0, Color.valueOf(getResources().getColor(R.color.white)), 1, 1, 0, arrayList_price_card_white_level_1_1, R.drawable.card_white_level_1_1);

        ArrayList<Integer> arrayList_price_card_white_level_1_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_2 = {2, 0, 2, 0, 0, 1};
        AddPriceList(arrayList_price_card_white_level_1_2, list_price_card_white_level_1_2);
        Card card_white_level_1_2 = AddCard(0, Color.valueOf(getResources().getColor(R.color.white)), 1, 1, 0, arrayList_price_card_white_level_1_2, R.drawable.card_white_level_1_2);

        ArrayList<Integer> arrayList_price_card_white_level_1_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_3 = {1, 0, 1, 1, 1, 0};
        AddPriceList(arrayList_price_card_white_level_1_3, list_price_card_white_level_1_3);
        Card card_white_level_1_3 = AddCard(0, Color.valueOf(getResources().getColor(R.color.white)), 1, 1, 0, arrayList_price_card_white_level_1_3, R.drawable.card_white_level_1_3);

        ArrayList<Integer> arrayList_price_card_white_level_1_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_4 = {0, 0, 2, 0, 3, 0};
        AddPriceList(arrayList_price_card_white_level_1_4, list_price_card_white_level_1_4);
        Card card_white_level_1_4 = AddCard(1, Color.valueOf(getResources().getColor(R.color.white)), 1, 1, 0, arrayList_price_card_white_level_1_4, R.drawable.card_white_level_1_4);

        ArrayList<Integer> arrayList_price_card_white_level_1_5 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_1_5 = {3, 0, 0, 0, 0, 0};
        AddPriceList(arrayList_price_card_white_level_1_5, list_price_card_white_level_1_5);
        Card card_white_level_1_5 = AddCard(0, Color.valueOf(getResources().getColor(R.color.white)), 1, 1, 1, arrayList_price_card_white_level_1_5, R.drawable.card_white_level_1_5);

        ArrayList<Integer> arrayList_price_card_green_level_1_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_1 = {2, 2, 0, 0, 0, 0};
        AddPriceList(arrayList_price_card_green_level_1_1, list_price_card_green_level_1_1);
        Card card_green_level_1_1 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 1, 1, 0, arrayList_price_card_green_level_1_1, R.drawable.card_green_level_1_1);

        ArrayList<Integer> arrayList_price_card_green_level_1_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_2 = {0, 0, 0, 2, 2, 1};
        AddPriceList(arrayList_price_card_green_level_1_2, list_price_card_green_level_1_2);
        Card card_green_level_1_2 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 1, 1, 0, arrayList_price_card_green_level_1_2, R.drawable.card_green_level_1_2);

        ArrayList<Integer> arrayList_price_card_green_level_1_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_3 = {1, 1, 0, 1, 1, 0};
        AddPriceList(arrayList_price_card_green_level_1_3, list_price_card_green_level_1_3);
        Card card_green_level_1_3 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 1, 1, 0, arrayList_price_card_green_level_1_3, R.drawable.card_green_level_1_3);

        ArrayList<Integer> arrayList_price_card_green_level_1_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_4 = {0, 3, 0, 2, 0, 0};
        AddPriceList(arrayList_price_card_green_level_1_4, list_price_card_green_level_1_4);
        Card card_green_level_1_4 = AddCard(1, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 1, 1, 0, arrayList_price_card_green_level_1_4, R.drawable.card_green_level_1_4);

        ArrayList<Integer> arrayList_price_card_green_level_1_5 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_1_5 = {0, 0, 0, 0, 3, 0};
        AddPriceList(arrayList_price_card_green_level_1_5, list_price_card_green_level_1_5);
        Card card_green_level_1_5 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 1, 1, 1, arrayList_price_card_green_level_1_5, R.drawable.card_green_level_1_5);

        ArrayList<Integer> arrayList_price_card_black_level_1_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_1 = {0, 0, 2, 0, 2, 0};
        AddPriceList(arrayList_price_card_black_level_1_1, list_price_card_black_level_1_1);
        Card card_black_level_1_1 = AddCard(0, Color.valueOf(getResources().getColor(R.color.black)), 1, 1, 0, arrayList_price_card_black_level_1_1, R.drawable.card_black_level_1_1);

        ArrayList<Integer> arrayList_price_card_black_level_1_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_2 = {2, 2, 0, 0, 0, 1};
        AddPriceList(arrayList_price_card_black_level_1_2, list_price_card_black_level_1_2);
        Card card_black_level_1_2 = AddCard(0, Color.valueOf(getResources().getColor(R.color.black)), 1, 1, 0, arrayList_price_card_black_level_1_2, R.drawable.card_black_level_1_2);

        ArrayList<Integer> arrayList_price_card_black_level_1_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_3 = {1, 1, 1, 0, 1, 0};
        AddPriceList(arrayList_price_card_black_level_1_3, list_price_card_black_level_1_3);
        Card card_black_level_1_3 = AddCard(0, Color.valueOf(getResources().getColor(R.color.black)), 1, 1, 0, arrayList_price_card_black_level_1_3, R.drawable.card_black_level_1_3);

        ArrayList<Integer> arrayList_price_card_black_level_1_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_4 = {2, 0, 3, 0, 0, 0};
        AddPriceList(arrayList_price_card_black_level_1_4, list_price_card_black_level_1_4);
        Card card_black_level_1_4 = AddCard(1, Color.valueOf(getResources().getColor(R.color.black)), 1, 1, 0, arrayList_price_card_black_level_1_4, R.drawable.card_black_level_1_4);

        ArrayList<Integer> arrayList_price_card_black_level_1_5 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_1_5 = {0, 3, 0, 0, 0, 0};
        AddPriceList(arrayList_price_card_black_level_1_5, list_price_card_black_level_1_5);
        Card card_black_level_1_5 = AddCard(0, Color.valueOf(getResources().getColor(R.color.black)), 1, 1, 1, arrayList_price_card_black_level_1_5, R.drawable.card_black_level_1_5);

        ArrayList<Integer> arrayList_price_card_red_level_1_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_1 = {2, 0, 2, 0, 0, 0};
        AddPriceList(arrayList_price_card_red_level_1_1, list_price_card_red_level_1_1);
        Card card_red_level_1_1 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4redToken)), 1, 1, 0, arrayList_price_card_red_level_1_1, R.drawable.card_red_level_1_1);

        ArrayList<Integer> arrayList_price_card_red_level_1_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_2 = {0, 2, 0, 2, 0, 1};
        AddPriceList(arrayList_price_card_red_level_1_2, list_price_card_red_level_1_2);
        Card card_red_level_1_2 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4redToken)), 1, 1, 0, arrayList_price_card_red_level_1_2, R.drawable.card_red_level_1_2);

        ArrayList<Integer> arrayList_price_card_red_level_1_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_3 = {1, 1, 1, 1, 0, 0};
        AddPriceList(arrayList_price_card_red_level_1_3, list_price_card_red_level_1_3);
        Card card_red_level_1_3 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4redToken)), 1, 1,0, arrayList_price_card_red_level_1_3, R.drawable.card_red_level_1_3);

        ArrayList<Integer> arrayList_price_card_red_level_1_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_4 = {3, 2, 0, 0, 0, 0};
        AddPriceList(arrayList_price_card_red_level_1_4, list_price_card_red_level_1_4);
        Card card_red_level_1_4 = AddCard(1, Color.valueOf(getResources().getColor(R.color.color4redToken)), 1, 1, 0, arrayList_price_card_red_level_1_4, R.drawable.card_red_level_1_4);

        ArrayList<Integer> arrayList_price_card_red_level_1_5 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_1_5 = {0, 0, 0, 3, 0, 0};
        AddPriceList(arrayList_price_card_red_level_1_5, list_price_card_red_level_1_5);
        Card card_red_level_1_5 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4redToken)), 1, 1, 1, arrayList_price_card_red_level_1_5, R.drawable.card_red_level_1_5);

        ArrayList<Integer> arrayList_price_card_normal_level_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_normal_level_1 = {0, 0, 0, 0, 4, 1};
        AddPriceList(arrayList_price_card_normal_level_1, list_price_card_normal_level_1);
        Card card_normal_level_1 = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 1, 0, 0, arrayList_price_card_normal_level_1, R.drawable.card_normal_level_1);

        ArrayList<Integer> arrayList_price_card_ultra_level_1_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_1_1 = {0, 0, 0, 4, 0, 1};
        AddPriceList(arrayList_price_card_ultra_level_1_1, list_price_card_ultra_level_1_1);
        Card card_ultra_level_1_1 = AddCard(1, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 1, 1, 0, arrayList_price_card_ultra_level_1_1, R.drawable.card_ultra_level_1_1);

        ArrayList<Integer> arrayList_price_card_ultra_level_1_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_1_2 = {0, 2, 2, 1, 0, 1};
        AddPriceList(arrayList_price_card_ultra_level_1_2, list_price_card_ultra_level_1_2);
        Card card_ultra_level_1_2 = AddCard(1, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 1, 1, 0, arrayList_price_card_ultra_level_1_2, R.drawable.card_ultra_level_1_2);

        ArrayList<Integer> arrayList_price_card_ultra_level_1_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_1_3 = {2, 0, 0, 1, 2, 1};
        AddPriceList(arrayList_price_card_ultra_level_1_3, list_price_card_ultra_level_1_3);
        Card card_ultra_level_1_3 = AddCard(1, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 1,1, 0, arrayList_price_card_ultra_level_1_3, R.drawable.card_ultra_level_1_3);

        ArrayList<Integer> arrayList_price_card_ultra_level_1_4 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_1_4 = {0, 4, 0, 0, 0, 1};
        AddPriceList(arrayList_price_card_ultra_level_1_4, list_price_card_ultra_level_1_4);
        Card card_ultra_level_1_4 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 1, 1, 1, arrayList_price_card_ultra_level_1_4, R.drawable.card_ultra_level_1_4);

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

    private void InitReservedCard(List<RoyalCard> listRoyalCard){
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
        RoyalCard royalCard = new RoyalCard(this);
        royalCard.setPoints(points);
        royalCard.setCrowns(crowns);
        royalCard.setImage(image);
        return royalCard;
    }

    private void AddPriceList(ArrayList<Integer> myListPrice, int[] listPrice){
        for (int i=0; i < listPrice.length; i++){
            myListPrice.add(listPrice[i]);
        }
    }

    private Card AddCard(int cardValue, Color color, int level, int discount, int crowns, ArrayList<Integer> price, int image){
        Card card = new Card(this);
        card.setCardValue(cardValue);
        card.setColor(color);
        card.setLevel(level);
        card.setDiscount(discount);
        card.setCrowns(crowns);
        card.setPrice(price);
        card.setImage(image);
        return card;
    }

    private Card PickRandomCard(List<Card> listCard){
        Random random = new Random();
        int randomIndex = random.nextInt(listCard.size());
        return listCard.remove(randomIndex);
    }

    private RoyalCard PickRandomRoyalCard(List<RoyalCard> royalCards) {
        Random random = new Random();
        int randomIndex = random.nextInt(royalCards.size());
        return royalCards.remove(randomIndex);
    }

    private void InitCardBoard(List<Card> cards, GridLayout cardBoard, int rowCount) {
        for (int i=0; i < rowCount; i++) {
            Card card = PickRandomCard(cards);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(2, 0, 2, 0);
            params.width = (int) (60 * getResources().getDisplayMetrics().density);
            params.height = (int) (100 * getResources().getDisplayMetrics().density);

            card.setImageResource(card.getImage());
            card.setLayoutParams(params);

            cardBoard.addView(card);
        }
    }

    private void InitReservedCardBoard(List<RoyalCard> royalCards, GridLayout reservedCardBoard, int rowCount) {
        for (int i = 0; i<rowCount; i++){
            RoyalCard royalCard = PickRandomRoyalCard(royalCards);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(5, 20, 5, 0);
            params.width = (int) (60 * getResources().getDisplayMetrics().density);
            params.height = (int) (100 * getResources().getDisplayMetrics().density);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(royalCard.getImage());
            imageView.setLayoutParams(params);

            reservedCardBoard.addView(imageView);
        }
    }

//    public Token pickRandomToken() {
//        if (tokenBag.isEmpty()) {
//            return null;
//        }
//        Random random = new Random();
//        int randomIndex = random.nextInt(tokenBag.size());
//
//        // Pick a Random Token
//        return tokenBag.remove(randomIndex);
//    }

    // Method for add Card Stack on bag player
    public void addNewCard(FrameLayout redCardStack) {
        ImageView card = new ImageView(this);
        card.setImageResource(R.drawable.blank_card);

        int cardSpacing = 70; // Sesuaikan agar hanya sebagian atas yang terlihat

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );

        // Hitung jumlah kartu yang ada untuk mengatur posisi kartu baru
        params.topMargin = redCardStack.getChildCount() * cardSpacing;
        card.setLayoutParams(params);

        // Tambahkan kartu ke dalam redCardStack
        redCardStack.addView(card);
    }
}