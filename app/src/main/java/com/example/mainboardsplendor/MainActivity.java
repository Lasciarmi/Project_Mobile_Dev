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
import android.widget.TextView;

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
        user1Controller.setPlayerBoard(1);
        user2Controller.setPlayerBoard(0);

        // Init tokenBag
        tokenController = new TokenController(tokenBag, this, this);
        tokenController.initTokenBag();

        // Init TokenBoard in Spiral
        tokenGridLayout = binding.tokenBoard.splendorDuelBoard;

        int rowCount =tokenGridLayout.getRowCount();
        int colCount = tokenGridLayout.getColumnCount();
        tokenController.InitTokenBoard(rowCount, colCount, tokenGridLayout);
        binding.tokenBoard.numTokenBag.setText(String.valueOf(tokenBag.size()));

//        How to get Token on GridLayout
//        for (int i = 0; i < 25; i++) {
//            View parentView = tokenGridLayout.getChildAt(i);
//
//            if (parentView instanceof androidx.cardview.widget.CardView) {
//                // Now find the Token view inside the CardView
//                Token token = parentView.findViewById(R.id.token_view);
//
//                if (token != null) {
//                    ArrayList<Integer> location = token.getLocation();
//
//                    // Check if location is not null before logging
//                    if (location != null) {
//                        Log.d("MainActivity", "Token found at index " + i + ": " + location.toString());
//                    } else {
//                        Log.e("MainActivity", "Location is null for token at index " + i);
//                    }
//                } else {
//                    Log.e("MainActivity", "Token view not found at index " + i);
//                }
//            } else {
//                Log.e("MainActivity", "Child at index " + i + " is not a CardView");
//            }
//        }


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
        // Init CardBoard
        cardController = new CardController(binding.cardBoard.cardStoreTop, binding.cardBoard.cardStoreMid, binding.cardBoard.cardStoreBot, binding.cardBoard.reservedCard, listCardLevel1, listCardLevel2, listCardLevel3, listRoyalCard,this, this);
        cardController.InitCardTopDeck();
        cardController.InitCardMidDeck();
        cardController.InitCardBotDeck();
        cardController.InitCardBoard();

        cardController.InitReservedCard();
        cardController.InitReservedCardBoard();

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
            view.setVisibility(View.INVISIBLE);
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
            view.setVisibility(View.INVISIBLE);
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
            view.setVisibility(View.INVISIBLE);
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
            view.setVisibility(View.INVISIBLE);
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
            view.setVisibility(View.INVISIBLE);
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
            view.setVisibility(View.INVISIBLE);
            binding.layoutPlayer1Bag.listPearlToken.listToken.addView(view);
            // TODO making reverse layout for all list token
        }

        // INIT CARD_STACK

        addNewCard(binding.layoutPlayer1Bag.blueCardStack);
        addNewCard(binding.layoutPlayer1Bag.blueCardStack);
        addNewCard(binding.layoutPlayer1Bag.blueCardStack);
        addNewCard(binding.layoutPlayer1Bag.blueCardStack);

        addNewCard(binding.layoutPlayer1Bag.redCardStack);
        addNewCard(binding.layoutPlayer1Bag.redCardStack);
        addNewCard(binding.layoutPlayer1Bag.redCardStack);
        addNewCard(binding.layoutPlayer1Bag.redCardStack);

        binding.taskBar.taskBarTakeGems.setVisibility(View.INVISIBLE);
        binding.taskBar.taskBarUsePrivilege.setVisibility(View.GONE);
    }

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