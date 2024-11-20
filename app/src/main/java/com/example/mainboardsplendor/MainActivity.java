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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mainboardsplendor.databinding.ActivityMainBinding;
import com.example.mainboardsplendor.model.Token;

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
    private int[][] movementPattern = {
            {2, 2}, {3, 2}, {3, 1}, {2, 1}, {1, 1},
            {1, 2}, {1, 3}, {2, 3}, {3, 3}, {4, 3},
            {4, 2}, {4, 1}, {4, 0}, {3, 0}, {2, 0},
            {1, 0}, {0, 0}, {0, 1}, {0, 2}, {0, 3},
            {0, 4}, {1, 4}, {2, 4}, {3, 4}, {4, 4}
    };

    private List<Token> tokenBag;
    private final List<CardView> tokenPick = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Init tokenBag
        initTokenBag();

        // Init nama player
        binding.scoreBoardPlayer1.playerName.setText("MC");
        binding.scoreBoardPlayer2.playerName.setText("Theo");

        binding.scoreBoardPlayer1.totalPrivilegePlayer.setText("1");

        // Implementation Spiral Token
        int rowCount = binding.tokenBoard.splendorDuelBoard.getRowCount();
        int colCount = binding.tokenBoard.splendorDuelBoard.getColumnCount();
        InitTokenBoard(rowCount, colCount, binding.tokenBoard.splendorDuelBoard);
        // End Implementation Spiral Token

        // Init Grid list_blue_token pada bag player
        for (int i = 0; i < 4; i++) {
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
        for (int i = 0; i < 4; i++) {
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
        for (int i = 0; i < 4; i++) {
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
        for (int i = 0; i < 4; i++) {
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
        for (int i = 0; i < 4; i++) {
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

        for (int i = 0; i < 2; i++) {
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
        for (int i = 0; i < 3; i++) {
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(5, 0, 5, 0);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.blank_card);
            imageView.setLayoutParams(params);

            binding.cardBoard.cardStoreTop.addView(imageView);
        }

        // INIT CARD_MID
        for (int i = 0; i < 4; i++) {
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(5, 0, 5, 0);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.blank_card);
            imageView.setLayoutParams(params);

            binding.cardBoard.cardStoreMid.addView(imageView);
        }

        // INIT CARD_BOT
        for (int i = 0; i < 5; i++) {
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(5, 0, 5, 0);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.blank_card);
            imageView.setLayoutParams(params);

            binding.cardBoard.cardStoreBot.addView(imageView);
        }

        // INIT RESERVED_CARD
        for (int i = 0; i < 4; i++) {
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(5, 20, 5, 0);

            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.blank_card);
            imageView.setLayoutParams(params);

            binding.cardBoard.reservedCard.addView(imageView);
        }

        // INIT CARD_STACK
        for (int i = 0; i < 5; i++) {
            ImageView card = new ImageView(this);
            card.setImageResource(R.drawable.blank_card); // Ganti dengan Kartu Token
            card.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            ));
            card.setTranslationY(i * 70); // Mengatur jarak Y antar Kartu

            binding.layoutPlayer1Bag.blueCardStack.addView(card);
        }

        for (int i = 0; i < 5; i++) {
            ImageView card = new ImageView(this);
            card.setImageResource(R.drawable.blank_card); // Ganti dengan Kartu Token
            card.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            ));
            card.setTranslationY(i * 70); // Mengatur jarak Y antar Kartu

            binding.layoutPlayer1Bag.whiteCardStack.addView(card);
        }

        for (int i = 0; i < 5; i++) {
            ImageView card = new ImageView(this);
            card.setImageResource(R.drawable.blank_card); // Ganti dengan Kartu Token
            card.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            ));
            card.setTranslationY(i * 70); // Mengatur jarak Y antar Kartu

            binding.layoutPlayer1Bag.greenCardStack.addView(card);
        }

        for (int i = 0; i < 5; i++) {
            ImageView card = new ImageView(this);
            card.setImageResource(R.drawable.blank_card); // Ganti dengan Kartu Token
            card.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            ));
            card.setTranslationY(i * 70); // Mengatur jarak Y antar Kartu

            binding.layoutPlayer1Bag.blackCardStack.addView(card);
        }

        for (int i = 0; i < 3; i++) {
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

    private void InitTokenBoard(int rowCount, int colCount, GridLayout tokenBoard) {
        boolean[][] isFilled = new boolean[rowCount][colCount];
        CardView[][] map = new CardView[rowCount][colCount];
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
            map[row][col] = cardView;
            cardView.setOnClickListener(v -> pickToken(row,col,cardView, map));

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

    private void pick() {
        if(tokenPick.isEmpty()){
            Toast.makeText(this, "oi", Toast.LENGTH_SHORT).show();
        }
    }

    private void initTokenBag() {
        tokenBag = new ArrayList<>();

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
        for (int i = 0; i < quantity; i++) {
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

    private void pickToken(int row, int col, CardView cardView, CardView[][] map) {
        //kalo blom ada yang diambil
        if (tokenPick.isEmpty()) {
            int colorchoosen = ContextCompat.getColor(this, R.color.color4tokenIsChoose);
            map[row][col].setCardBackgroundColor(colorchoosen);
            tokenPick.add(cardView);
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (r != checkRangeRow(row+1) && c==col || r != checkRangeRow(row+2) && c==col || r != checkRangeRow(row-2) && c==col || r != checkRangeRow(row-1) && c==col){
                        map[r][c].setClickable(false);
                        int colorfalse = ContextCompat.getColor(this, R.color.transparent);
                        map[r][c].setCardBackgroundColor(colorfalse);
                    }
                    else if (c != checkRangeCol(col+1)&& r==row || c != checkRangeCol(col+2)&& r==row || c != checkRangeCol(col-1)&& r==row || c != checkRangeCol(col-2) && r==row){
                        map[r][c].setClickable(false);
                        int colorfalse = ContextCompat.getColor(this, R.color.transparent);
                        map[r][c].setCardBackgroundColor(colorfalse);
                    }
//                    else if (c!= checkRangeCol(col +1) && r!=checkRangeRow(row+1) || c!= checkRangeCol(col +2) && r!=checkRangeRow(row+2)) {
//                        map[r][c].setClickable(false);
//                        int colorfalse = ContextCompat.getColor(this, R.color.transparent);
//                        map[r][c].setCardBackgroundColor(colorfalse);
//                    } else if (c!= checkRangeCol(col -1) && r!=checkRangeRow(row-1) || c!= checkRangeCol(col -2) && r!=checkRangeRow(row-2)) {
//                        map[r][c].setClickable(false);
//                        int colorfalse = ContextCompat.getColor(this, R.color.transparent);
//                        map[r][c].setCardBackgroundColor(colorfalse);
//                    } else if (c!= checkRangeCol(col -1) && r!=checkRangeRow(row+1) || c!= checkRangeCol(col -2) && r!=checkRangeRow(row+2)) {
//                        map[r][c].setClickable(false);
//                        int colorfalse = ContextCompat.getColor(this, R.color.transparent);
//                        map[r][c].setCardBackgroundColor(colorfalse);
//                    } else if (c!= checkRangeCol(col +1) && r!=checkRangeRow(row-1) || c!= checkRangeCol(col +2) && r!=checkRangeRow(row-2)) {
//                        map[r][c].setClickable(false);
//                        int colorfalse = ContextCompat.getColor(this, R.color.transparent);
//                        map[r][c].setCardBackgroundColor(colorfalse);
//                    }
                }
            }
        }
        else if (tokenPick.size() == 1){
            tokenPick.add(cardView);
            for (int ro = 0; ro < 5; ro++){
                for (int co = 0; co<5; co++){
                    if (tokenPick.get(0) == map[row][col]){
                        if (checkRangeRow(ro-1) != row && checkRangeRow(ro+1) != row){
                            map[checkRangeRow(ro+1)][col].setClickable(false);
                            map[checkRangeRow(ro-1)][col].setClickable(false);
                            map[checkRangeRow(ro+2)][col].setClickable(false);
                            map[checkRangeRow(ro-2)][col].setClickable(false);
                        }
                        if (col != checkRangeCol(co-1) && col != checkRangeCol(co+1)){
                            map[checkRangeRow(row)][co+1].setClickable(false);
                            map[checkRangeRow(row)][co-1].setClickable(false);
                            map[checkRangeRow(row)][co+2].setClickable(false);
                            map[checkRangeRow(row)][co-2].setClickable(false);
                        }
                        if(col != checkRangeCol(co-1) && row!= checkRangeRow(ro-1)){
                            map[checkRangeRow(ro-1)][checkRangeCol(co-1)].setClickable(false);
                            map[checkRangeRow(ro-2)][checkRangeCol(co-2)].setClickable(false);
                        }
                        if(col != checkRangeCol(co+1) && row!= checkRangeRow(ro+1)){
                            map[checkRangeRow(ro+1)][checkRangeCol(co+1)].setClickable(false);
                            map[checkRangeRow(ro+2)][checkRangeCol(co+2)].setClickable(false);
                        }
                        if(col != checkRangeCol(co+1) && row!= checkRangeRow(ro-1)){
                            map[checkRangeRow(ro-1)][checkRangeCol(co+1)].setClickable(false);
                            map[checkRangeRow(ro-2)][checkRangeCol(co+2)].setClickable(false);
                        }
                        if(col != checkRangeCol(co-1) && row!= checkRangeRow(ro+1)){
                            map[checkRangeRow(ro+1)][checkRangeCol(co-1)].setClickable(false);
                            map[checkRangeRow(ro+2)][checkRangeCol(co-2)].setClickable(false);
                        }
                    }
                }
            }
        }
        else if (tokenPick.size() == 2){
            tokenPick.add(cardView);
        }
    }

    private int checkRangeRow(int row){
        if (row > movementPattern.length){
            return movementPattern.length-1;
        }
        else if(row<0){
            return 0;
        }
        return row;
    }

    private int checkRangeCol(int col){
        if (col > movementPattern.length){
            return movementPattern.length-1;
        }
        else if(col<0){
            return 0;
        }
        return col;
    }
}