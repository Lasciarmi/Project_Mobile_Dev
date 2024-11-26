package com.example.mainboardsplendor.controller;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.example.mainboardsplendor.MainActivity;
import com.example.mainboardsplendor.R;
import com.example.mainboardsplendor.model.Token;

import java.util.ArrayList;
import java.util.List;

public class TokenController {
    private static final int quantityBlueToken = 4;
    private static final int quantityWhiteToken = 4;
    private static final int quantityGreenToken = 4;
    private static final int quantityBlackToken = 4;
    private static final int quantityRedToken = 4;
    private static final int quantityPearlToken = 2;
    private static final int quantityGoldToken = 3;

    private List<Token> tokenBag;
    private Context context;
    private MainActivity mainActivity;

    public TokenController(List<Token> tokenBag, Context context, MainActivity mainActivity) {
        this.tokenBag = tokenBag;
        this.context = context;
        this.mainActivity = mainActivity;
    }

    public void initTokenBag() {
        Token blueToken = new Token(context);
        blueToken.setColor(Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)));
        Token whiteToken = new Token(context);
        whiteToken.setColor(Color.valueOf(mainActivity.getResources().getColor(R.color.white)));
        Token greenToken = new Token(context);
        greenToken.setColor(Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)));
        Token blackToken = new Token(context);
        blackToken.setColor(Color.valueOf(mainActivity.getResources().getColor(R.color.black)));
        Token redToken = new Token(context);
        redToken.setColor(Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)));
        Token pearlToken = new Token(context);
        pearlToken.setColor(Color.valueOf(mainActivity.getResources().getColor(R.color.color4pearlToken)));
        Token goldToken = new Token(context);
        goldToken.setColor(Color.valueOf(mainActivity.getResources().getColor(R.color.color4goldToken)));

        addTokens(blueToken, quantityBlueToken);
        addTokens(whiteToken, quantityWhiteToken);
        addTokens(greenToken, quantityGreenToken);
        addTokens(blackToken, quantityBlackToken);
        addTokens(redToken, quantityRedToken);
        addTokens(pearlToken, quantityPearlToken);
        addTokens(goldToken, quantityGoldToken);
    }

    private void addTokens(Token token, int quantity) {
        for (int i=0; i<quantity; i++) {
            tokenBag.add(token);
        }
    }

    public void InitTokenBoard(int rowCount, int colCount, GridLayout splendorDuelBoard) {
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
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(R.drawable.white_token);
                imageView.setVisibility(View.INVISIBLE);
                splendorDuelBoard.addView(imageView);
                continue;
            }
            int row = movementPattern[i][0];
            int col = movementPattern[i][1];

            // Create View for token
            View view = LayoutInflater.from(context).inflate(
                    R.layout.custom_token, splendorDuelBoard, false);

            CardView cardView = view.findViewById(R.id.cardView_token);
            ImageView tokenView = view.findViewById(R.id.token_view);

            cardView.setCardBackgroundColor(token.getColor().toArgb());

            Color color = token.getColor();
            if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)))) {
                tokenView.setImageResource(R.drawable.blue_token);
            } else if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.white)))) {
                tokenView.setImageResource(R.drawable.white_token);
            } else if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)))) {
                tokenView.setImageResource(R.drawable.green_token);
            } else if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.black)))) {
                tokenView.setImageResource(R.drawable.black_token);
            } else if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)))) {
                tokenView.setImageResource(R.drawable.red_token);
            } else if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.color4pearlToken)))) {
                tokenView.setImageResource(R.drawable.pearl_token);
            } else if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.color4goldToken)))) {
                tokenView.setImageResource(R.drawable.gold_token);
            }

            // Define row & col gridLayout
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(row);
            params.columnSpec = GridLayout.spec(col);
            params.setGravity(Gravity.FILL);
            view.setLayoutParams(params);

            // Adding View to GridLayout
            ArrayList<Integer> location = new ArrayList<>();
            location.add(row);
            location.add(col);
            Log.d("MainActivity", "Setting location: " + location.toString());
            token.setLocation(location);
            Log.d("MainActivity", "Location set: " + token.getLocation().toString());
            splendorDuelBoard.addView(view);

            // Mark Slot
            isFilled[row][col] = true;
        }
    }

    private Token pickRandomToken() {
        if (tokenBag.isEmpty()) {
            return null;
        }
        int randomIndex = (int) (Math.random() * tokenBag.size());
        return tokenBag.remove(randomIndex);
    }
}