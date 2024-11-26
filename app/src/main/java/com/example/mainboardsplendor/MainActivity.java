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

import com.example.mainboardsplendor.databinding.ActivityMainBinding;
import com.example.mainboardsplendor.databinding.LayoutScorePlayerBoardBinding;
import com.example.mainboardsplendor.model.Card;
import com.example.mainboardsplendor.model.RoyalCard;
import com.example.mainboardsplendor.model.Token;
import com.example.mainboardsplendor.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int quantityBlueToken = 4;
    private int quantityWhiteToken = 4;
    private int quantityGreenToken = 4;
    private int quantityBlackToken = 4;
    private int quantityRedToken = 4;
    private int quantityPearlToken = 2;
    private int quantityGoldToken = 3;

//    private int quantityCardLevel3 = 10;
//    private int quantityCardLevel2 = 20;
//    private int quantityCardLevel1 = 25;

    private List<Token> tokenBag = new ArrayList<>();
    private List<Card> listCardLevel3 = new ArrayList<>();
    private List<Card> listCardLevel2 = new ArrayList<>();
    private List<Card> listCardLevel1 = new ArrayList<>();
    private List<RoyalCard> listRoyalCard = new ArrayList<>();
    private User user1;
    private User user2;

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

        // Init tokenBag
        initTokenBag();

        // Init TokenBoard in Spiral
        int rowCount = binding.tokenBoard.splendorDuelBoard.getRowCount();
        int colCount = binding.tokenBoard.splendorDuelBoard.getColumnCount();
        InitTokenBoard(rowCount, colCount, binding.tokenBoard.splendorDuelBoard);
        // End Init TokenBoard in Spiral

        // get GridLayout for each cardBoard level
        GridLayout cardBoard_level3 = binding.cardBoard.cardStoreTop;
        GridLayout cardBoard_level2 = binding.cardBoard.cardStoreMid;
        GridLayout cardBoard_level1 = binding.cardBoard.cardStoreBot;
        // Init Card on Deck
        InitCardTopDeck(listCardLevel3);
        InitCardMidDeck(listCardLevel2);
        InitCardBotDeck(listCardLevel1);
        // Init Card on Board
        InitCardBoard(listCardLevel3, cardBoard_level3, 3);
        InitCardBoard(listCardLevel2, cardBoard_level2, 4);
        InitCardBoard(listCardLevel1, cardBoard_level1, 5);
        //Init Card Reserved on Board
        InitReservedCard(listRoyalCard);
        InitReservedCardBoard(listRoyalCard, binding.cardBoard.reservedCard, 4);

        // Init User
        // TODO: 11/19/2024  init user with class and method : last edited by theo
        initUser(binding.scoreBoardPlayer1, binding.scoreBoardPlayer2);

        binding.scoreBoardPlayer1.totalPrivilegePlayer.setText("1");

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

        // INIT CARD_TOP
        for (int i = 0; i < 3; i++){
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(5, 0, 5, 0);
            params.width = (int) (65 * getResources().getDisplayMetrics().density);
            params.height = (int) (105 * getResources().getDisplayMetrics().density);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.blank_card);
            imageView.setLayoutParams(params);

            binding.cardBoard.cardStoreTop.addView(imageView);
        }

        // INIT CARD_MID
        for (int i = 0; i < 4; i++){
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(5, 0, 5, 0);
            params.width = (int) (65 * getResources().getDisplayMetrics().density);
            params.height = (int) (105 * getResources().getDisplayMetrics().density);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.blank_card);
            imageView.setLayoutParams(params);

            binding.cardBoard.cardStoreMid.addView(imageView);
        }

        // INIT CARD_BOT
        for (int i = 0; i < 5; i++){
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(5, 0, 5, 0);
            params.width = (int) (65 * getResources().getDisplayMetrics().density);
            params.height = (int) (105 * getResources().getDisplayMetrics().density);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.blank_card);
            imageView.setLayoutParams(params);

            binding.cardBoard.cardStoreBot.addView(imageView);
        }

        // INIT RESERVED_CARD
        for (int i = 0; i<4; i++){
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(5, 20, 5, 0);
            params.width = (int) (65 * getResources().getDisplayMetrics().density);
            params.height = (int) (105 * getResources().getDisplayMetrics().density);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.blank_card);
            imageView.setLayoutParams(params);

            binding.cardBoard.reservedCard.addView(imageView);
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

    private void initUser(LayoutScorePlayerBoardBinding scoreBoardPlayer1, LayoutScorePlayerBoardBinding scoreBoardPlayer2) {
        // TODO: 11/19/2024 : last edited by theo
        user1 = new User("MC");
        user2 = new User("Theo");
        updateScoreBoard(user1, scoreBoardPlayer1);
        updateScoreBoard(user2, scoreBoardPlayer2);
    }

    private void updateScoreBoard(User player, LayoutScorePlayerBoardBinding scoreBoardPlayer) {
        // TODO: 11/19/2024 : last edited by theo
        scoreBoardPlayer.playerName.setText(player.getUsername());
        scoreBoardPlayer.poinPlayer.setText(String.valueOf(player.getCardsPoint()));
        scoreBoardPlayer.crownPlayer.setText(String.valueOf(player.getCrowns()));
        scoreBoardPlayer.cardPoinPlayer.setText(String.valueOf(player.getMostSameCardColorValue()));
        scoreBoardPlayer.totalPrivilegePlayer.setText(String.valueOf(player.getScroll()));
        scoreBoardPlayer.totalReservedCardPlayer.setText(String.valueOf(player.getReserveCard()));
        scoreBoardPlayer.totalTokenPlayer.setText(String.valueOf(player.getTokensStack()));
    }

    private void InitCardTopDeck(List<Card> listCardLevel3) {
        ArrayList<Integer> arrayList_price_card_black_level_3_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_3_1 = {0, 2, 0, 6, 2, 0};
        AddPriceList(arrayList_price_card_black_level_3_1, list_price_card_black_level_3_1);
        Card card_black_level_3_1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.black)), 3, 1, 0, arrayList_price_card_black_level_3_1, R.drawable.card_black_level_3_1);

        ArrayList<Integer> arrayList_price_card_black_level_3_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_black_level_3_2 = {0, 3, 5, 0, 3, 1};
        AddPriceList(arrayList_price_card_black_level_3_2, list_price_card_black_level_3_2);
        Card card_black_level_3_2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.black)), 3, 1, 2, arrayList_price_card_black_level_3_2, R.drawable.card_black_level_3_2);

        ArrayList<Integer> arrayList_price_card_blue_level_3_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_3_1 = {6, 2, 2, 0, 0, 0};
        AddPriceList(arrayList_price_card_blue_level_3_1, list_price_card_blue_level_3_1);
        Card card_blue_level_3_1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 3, 1, 0, arrayList_price_card_blue_level_3_1, R.drawable.card_blue_level_3_1);

        ArrayList<Integer> arrayList_price_card_blue_level_3_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_blue_level_3_2 = {0, 3, 3, 5, 0, 1};
        AddPriceList(arrayList_price_card_blue_level_3_2, list_price_card_blue_level_3_2);
        Card card_blue_level3_2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 3, 1, 2, arrayList_price_card_blue_level_3_2, R.drawable.card_blue_level_3_2);

        ArrayList<Integer> arrayList_price_card_green_level_3_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_3_1 = {2, 0, 6, 0, 2, 0};
        AddPriceList(arrayList_price_card_green_level_3_1, list_price_card_green_level_3_1);
        Card card_green_level_3_1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 3, 1, 0, arrayList_price_card_green_level_3_1, R.drawable.card_green_level_3_1);

        ArrayList<Integer> arrayList_price_card_green_level_3_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_green_level_3_2 = {3, 5, 0, 0, 3, 1};
        AddPriceList(arrayList_price_card_green_level_3_2, list_price_card_green_level_3_2);
        Card card_green_level_3_2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 3, 1, 2, arrayList_price_card_green_level_3_2, R.drawable.card_green_level_3_2);

        ArrayList<Integer> arrayList_price_card_normal_level_3 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_normal_level_3 = {0, 8, 0, 0, 0, 0};
        AddPriceList(arrayList_price_card_normal_level_3, list_price_card_normal_level_3);
        Card card_normal_level_3 = AddCard(6, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 3, 0, 0, arrayList_price_card_normal_level_3, R.drawable.card_normal_level_3);

        ArrayList<Integer> arrayList_price_card_red_level_3_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_3_1 = {0, 0, 2, 2, 6, 0};
        AddPriceList(arrayList_price_card_red_level_3_1, list_price_card_red_level_3_1);
        Card card_red_level_3_1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.color4redToken)), 3, 1, 0, arrayList_price_card_red_level_3_1, R.drawable.card_red_level_3_1);

        ArrayList<Integer> arrayList_price_card_red_level_3_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_red_level_3_2 = {5, 0, 3, 3, 0, 1};
        AddPriceList(arrayList_price_card_red_level_3_2, list_price_card_red_level_3_2);
        Card card_red_level_3_2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4redToken)), 3, 1, 2, arrayList_price_card_red_level_3_2, R.drawable.card_red_level_3_2);

        ArrayList<Integer> arrayList_price_card_ultra_level_3_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_3_1 = {0, 0, 0, 8, 0, 0};
        AddPriceList(arrayList_price_card_ultra_level_3_1, list_price_card_ultra_level_3_1);
        Card card_ultra_level_3_1 = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 3, 1, 0, arrayList_price_card_ultra_level_3_1, R.drawable.card_ultra_level_3_1);

        ArrayList<Integer> arrayList_price_card_ultra_level_3_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_ultra_level_3_2 = {0, 0, 0, 8, 0, 0};
        AddPriceList(arrayList_price_card_ultra_level_3_2, list_price_card_ultra_level_3_2);
        Card card_ultra_level_3_2 = AddCard(0, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 3, 1, 3, arrayList_price_card_ultra_level_3_1, R.drawable.card_ultra_level_3_2);

        ArrayList<Integer> arrayList_price_card_white_level_3_1 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_3_1 = {2, 6, 0, 2, 0, 0};
        AddPriceList(arrayList_price_card_white_level_3_1, list_price_card_white_level_3_1);
        Card card_white_level_3_1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.white)), 3, 1, 0, arrayList_price_card_white_level_3_1, R.drawable.card_white_level_3_1);

        ArrayList<Integer> arrayList_price_card_white_level_3_2 = new ArrayList<>();
        // seq: [blue, white, green, black, red, pearl]
        int[] list_price_card_white_level_3_2 = {3, 0, 0, 3, 5, 1};
        AddPriceList(arrayList_price_card_white_level_3_2, list_price_card_white_level_3_2);
        Card card_white_level_3_2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.white)), 3, 1, 2, arrayList_price_card_white_level_3_2, R.drawable.card_white_level_3_2);

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

    private void InitTokenBoard(int rowCount, int colCount, GridLayout tokenBoard) {
        boolean[][] isFilled = new boolean[rowCount][colCount];

        int[][] movementPattern = {
                {2, 2}, {3, 2}, {3, 1}, {2, 1}, {1, 1},
                {1, 2}, {1, 3}, {2, 3}, {3, 3}, {4, 3},
                {4, 2}, {4, 1}, {4, 0}, {3, 0}, {2, 0},
                {1, 0}, {0, 0}, {0, 1}, {0, 2}, {0, 3},
                {0, 4}, {1, 4}, {2, 4}, {3, 4}, {4, 4}
        };

        for (int i = 0; i < movementPattern.length; i++) {
            Token token = pickRandomToken();
            if (token == null) {
                break;
            }
            int row = movementPattern[i][0];
            int col = movementPattern[i][1];
            Log.d("MainActivity", "Row: " + row + ", Col: " + col);

            // Create View for token
            View view = LayoutInflater.from(this).inflate(
                    R.layout.custom_token, tokenBoard, false);

            CardView cardView = view.findViewById(R.id.cardView_token);
            ImageView tokenView = view.findViewById(R.id.token_view);

            cardView.setCardBackgroundColor(token.getColor().toArgb());

            Color color = token.getColor();
            if (color.equals(Color.valueOf(getResources().getColor(R.color.color4blueToken)))) {
                tokenView.setImageResource(R.drawable.blue_token);
            } else if (color.equals(Color.valueOf(getResources().getColor(R.color.white)))) {
                tokenView.setImageResource(R.drawable.white_token);
            } else if (color.equals(Color.valueOf(getResources().getColor(R.color.color4greenToken)))) {
                tokenView.setImageResource(R.drawable.green_token);
            } else if (color.equals(Color.valueOf(getResources().getColor(R.color.black)))) {
                tokenView.setImageResource(R.drawable.black_token);
            } else if (color.equals(Color.valueOf(getResources().getColor(R.color.color4redToken)))) {
                tokenView.setImageResource(R.drawable.red_token);
            } else if (color.equals(Color.valueOf(getResources().getColor(R.color.color4pearlToken)))) {
                tokenView.setImageResource(R.drawable.pearl_token);
            } else if (color.equals(Color.valueOf(getResources().getColor(R.color.color4goldToken)))) {
                tokenView.setImageResource(R.drawable.gold_token);
            }

            // Define row & col gridLayout
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(row);
            params.columnSpec = GridLayout.spec(col);
            params.setGravity(Gravity.FILL);
            view.setLayoutParams(params);

            // Adding View to GridLayout
            tokenBoard.addView(view);

            // Mark Slot
            isFilled[row][col] = true;
        }
    }

    private void initTokenBag() {
        Token blueToken = new Token(this);
        blueToken.setColor(Color.valueOf(getResources().getColor(R.color.color4blueToken)));
        Token whiteToken = new Token(this);
        whiteToken.setColor(Color.valueOf(getResources().getColor(R.color.white)));
        Token greenToken = new Token(this);
        greenToken.setColor(Color.valueOf(getResources().getColor(R.color.color4greenToken)));
        Token blackToken = new Token(this);
        blackToken.setColor(Color.valueOf(getResources().getColor(R.color.black)));
        Token redToken = new Token(this);
        redToken.setColor(Color.valueOf(getResources().getColor(R.color.color4redToken)));
        Token pearlToken = new Token(this);
        pearlToken.setColor(Color.valueOf(getResources().getColor(R.color.color4pearlToken)));
        Token goldToken = new Token(this);
        goldToken.setColor(Color.valueOf(getResources().getColor(R.color.color4goldToken)));

        addTokens(blueToken, quantityBlueToken);
        addTokens(whiteToken, quantityWhiteToken);
        addTokens(greenToken, quantityGreenToken);
        addTokens(blackToken, quantityBlackToken);
        addTokens(redToken, quantityRedToken);
        addTokens(pearlToken, quantityPearlToken);
        addTokens(goldToken, quantityGoldToken);
    }

    // Method for add Token in Array TokenBag
    private void addTokens(Token token, int quantity) {
        for (int i=0; i<quantity; i++) {
            tokenBag.add(token);
        }
    }

    public Token pickRandomToken() {
        if (tokenBag.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(tokenBag.size());

        // Pick a Random Token
        return tokenBag.remove(randomIndex);
    }

    // Method for add Card Stack
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