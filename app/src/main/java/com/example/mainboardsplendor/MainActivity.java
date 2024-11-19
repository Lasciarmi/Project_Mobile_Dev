package com.example.mainboardsplendor;

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

    private int quantityCardLevel3 = 10;
    private int quantityCardLevel2 = 20;
    private int quantityCardLevel1 = 25;

    private List<Token> tokenBag = new ArrayList<>();
    private User player1;
    private User player2;
    private List<Card> listCardLevel3 = new ArrayList<>();
    private List<Card> listCardLevel2 = new ArrayList<>();
    private List<Card> listCardLevel1 = new ArrayList<>();

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
        // Init tokenBag
        initTokenBag();

        // Init TokenBoard in Spiral
        int rowCount = binding.tokenBoard.splendorDuelBoard.getRowCount();
        int colCount = binding.tokenBoard.splendorDuelBoard.getColumnCount();
        InitTokenBoard(rowCount, colCount, binding.tokenBoard.splendorDuelBoard);
        // End Init TokenBoard in Spiral

        // Init CardBoard
        GridLayout cardBoard_level3 = binding.cardBoard.cardStoreTop;
        GridLayout cardBoard_level2 = binding.cardBoard.cardStoreMid;
        GridLayout cardBoard_level1 = binding.cardBoard.cardStoreBot;
        InitCardTopDeck(listCardLevel3, quantityCardLevel3);
        InitCardBoard(listCardLevel3, cardBoard_level3);
//        InitCardBoard(listCardLevel2, cardBoard_level2);
//        InitCardBoard(listCardLevel1, cardBoard_level1);
        // End Init CardBoard

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
        player1 = new User("MC");
        player2 = new User("Theo");
        updateScoreBoard(player1, scoreBoardPlayer1);
        updateScoreBoard(player2, scoreBoardPlayer2);
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

    private void InitCardTopDeck(List<Card> listCardLevel3, int quantityCardLevel3) {
        ArrayList<Integer> listPrice1 = new ArrayList<>();
        listPrice1.add(0); //for blue
        listPrice1.add(2); // for white
        listPrice1.add(0); // for green
        listPrice1.add(6); // for black
        listPrice1.add(2); // for red
        listPrice1.add(0); // for pearl
        Card cardBlack1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.black)), 3, 1, 0, listPrice1);

        ArrayList<Integer> listPrice2 = new ArrayList<>();
        listPrice2.add(0); //for blue
        listPrice2.add(3); // for white
        listPrice2.add(5); // for green
        listPrice2.add(0); // for black
        listPrice2.add(3); // for red
        listPrice2.add(1); // for pearl
        Card cardBlack2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.black)), 3, 1, 2, listPrice2);

        ArrayList<Integer> listPrice3 = new ArrayList<>();
        listPrice3.add(6); //for blue
        listPrice3.add(2); // for white
        listPrice3.add(2); // for green
        listPrice3.add(0); // for black
        listPrice3.add(0); // for red
        listPrice3.add(0); // for pearl
        Card cardBlue1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 3, 1, 0, listPrice3);

        ArrayList<Integer> listPrice4 = new ArrayList<>();
        listPrice4.add(0); //for blue
        listPrice4.add(3); //for white
        listPrice4.add(3); //for green
        listPrice4.add(5); //for black
        listPrice4.add(0); //for red
        listPrice4.add(1); //for pearl
        Card cardBlue2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4blueToken)), 3, 1, 2, listPrice4);

        ArrayList<Integer> listPrice5 = new ArrayList<>();
        listPrice5.add(2); //for blue
        listPrice5.add(0); //for white
        listPrice5.add(6); //for green
        listPrice5.add(0); //for black
        listPrice5.add(2); //for red
        listPrice5.add(0); //for pearl
        Card cardGreen1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 3, 1, 0, listPrice5);

        ArrayList<Integer> listPrice6 = new ArrayList<>();
        listPrice6.add(2); //for blue
        listPrice6.add(0); //for white
        listPrice6.add(0); //for green
        listPrice6.add(0); //for black
        listPrice6.add(2); //for red
        listPrice6.add(0); //for pearl
        Card cardGreen2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4greenToken)), 3, 1, 2, listPrice6);

        ArrayList<Integer> listPrice7 = new ArrayList<>();
        listPrice7.add(0); //for blue
        listPrice7.add(8); //for white
        listPrice7.add(0); //for green
        listPrice7.add(0); //for black
        listPrice7.add(0); //for red
        listPrice7.add(0); //for pearl
        Card cardNormal = AddCard(6, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 3, 0, 0, listPrice7);

        ArrayList<Integer> listPrice8 = new ArrayList<>();
        listPrice8.add(0); //for blue
        listPrice8.add(0); //for white
        listPrice8.add(2); //for green
        listPrice8.add(2); //for black
        listPrice8.add(6); //for red
        listPrice8.add(0); //for pearl
        Card cardRed1 = AddCard(4, Color.valueOf(getResources().getColor(R.color.color4redToken)), 3, 1, 0, listPrice8);

        ArrayList<Integer> listPrice9 = new ArrayList<>();
        listPrice9.add(0); //for blue
        listPrice9.add(0); //for white
        listPrice9.add(2); //for green
        listPrice9.add(2); //for black
        listPrice9.add(0); //for red
        listPrice9.add(0); //for pearl
        Card cardRed2 = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4redToken)), 3, 1, 2, listPrice9);

        ArrayList<Integer> listPrice10 = new ArrayList<>();
        listPrice10.add(0); //for blue
        listPrice10.add(0); //for white
        listPrice10.add(0); //for green
        listPrice10.add(0); //for black
        listPrice10.add(0); //for red
        listPrice10.add(0); //for pearl
        Card cardUltra = AddCard(3, Color.valueOf(getResources().getColor(R.color.color4normalToken)), 3, 1, 0, listPrice10);

        listCardLevel3.add(cardBlack1);
        listCardLevel3.add(cardBlack2);
        listCardLevel3.add(cardBlue1);
        listCardLevel3.add(cardBlue2);
        listCardLevel3.add(cardGreen1);
        listCardLevel3.add(cardGreen2);
        listCardLevel3.add(cardNormal);
        listCardLevel3.add(cardRed1);
        listCardLevel3.add(cardRed2);
        listCardLevel3.add(cardUltra);
    }

    private Card AddCard(int cardValue, Color color, int level, int discount, int crowns, ArrayList<Integer> price){
        Card card = new Card(this);
        card.setCardValue(cardValue);
        card.setColor(color);
        card.setLevel(level);
        card.setDiscount(discount);
        card.setCrowns(crowns);
        card.setPrice(price);
        return card;
    }

    private Card PickRandomCard(List<Card> listCard){
        Random random = new Random();
        int randomIndex = random.nextInt(listCard.size());
        return listCard.get(randomIndex);
    }

    private void InitCardBoard(List<Card> cards, GridLayout cardBoard) {
        for (int i=0; i < 3; i++) {
            Card card = PickRandomCard(cards);
            cardBoard.addView(card);
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