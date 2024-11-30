package com.example.mainboardsplendor.controller;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.mainboardsplendor.MainActivity;
import com.example.mainboardsplendor.R;
import com.example.mainboardsplendor.model.Token;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TokenController {
    private static final int quantityBlueToken = 4;
    private static final int quantityWhiteToken = 4;
    private static final int quantityGreenToken = 4;
    private static final int quantityBlackToken = 4;
    private static final int quantityRedToken = 4;
    private static final int quantityPearlToken = 2;
    private static final int quantityGoldToken = 3;
    private int[][] movementPattern = {
            {2, 2}, {3, 2}, {3, 1}, {2, 1}, {1, 1},
            {1, 2}, {1, 3}, {2, 3}, {3, 3}, {4, 3},
            {4, 2}, {4, 1}, {4, 0}, {3, 0}, {2, 0},
            {1, 0}, {0, 0}, {0, 1}, {0, 2}, {0, 3},
            {0, 4}, {1, 4}, {2, 4}, {3, 4}, {4, 4}
    };

    private List<Token> tokenBag;
    private final List<CardView> tokenPick = new ArrayList<>();
    private Context context;
    private MainActivity mainActivity;
    public List<Token> selectedToken = new ArrayList<>();
    private GridLayout splendorDuelBoard;
    private CardView taskBarTakeToken;

    private List<View> removedView = new ArrayList<>();

    public TokenController(List<Token> tokenBag, GridLayout splendorDuelBoard, CardView taskBarTakeToken, Context context, MainActivity mainActivity) {
        this.tokenBag = tokenBag;
        this.context = context;
        this.mainActivity = mainActivity;
        this.splendorDuelBoard = splendorDuelBoard;
        this.taskBarTakeToken = taskBarTakeToken;
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

    public void InitTokenBoard(int rowCount, int colCount) {
        boolean[][] isFilled = new boolean[rowCount][colCount];

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

            Token tokenImage = view.findViewById(R.id.token_view);

            CardView cardView = view.findViewById(R.id.cardView_token);

            cardView.setCardBackgroundColor(token.getColor().toArgb());

            Color color = token.getColor();
            tokenImage.setColor(color);
            if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.color4blueToken)))) {
                tokenImage.setImageResource(R.drawable.blue_token);
            } else if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.white)))) {
                tokenImage.setImageResource(R.drawable.white_token);
            } else if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.color4greenToken)))) {
                tokenImage.setImageResource(R.drawable.green_token);
            } else if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.black)))) {
                tokenImage.setImageResource(R.drawable.black_token);
            } else if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.color4redToken)))) {
                tokenImage.setImageResource(R.drawable.red_token);
            } else if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.color4pearlToken)))) {
                tokenImage.setImageResource(R.drawable.pearl_token);
            } else if (color.equals(Color.valueOf(mainActivity.getResources().getColor(R.color.color4goldToken)))) {
                tokenImage.setImageResource(R.drawable.gold_token);
            }

            // Define row & col gridLayout
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(row);
            params.columnSpec = GridLayout.spec(col);
            params.setGravity(Gravity.FILL);
            view.setLayoutParams(params);
            ArrayList<Integer> location = new ArrayList<>();
            location.add(row);
            location.add(col);
//            Log.d("MainActivity", "Setting location: " + location.toString());
            tokenImage.setLocation(location);
            setOnClickListenerToken(tokenImage);
//            tokenImage.setOnClickListener(view1 -> selectToken(tokenImage));
//            Log.d("MainActivity", "Location set: " + tokenImage.getLocation().toString());

            splendorDuelBoard.addView(view);

            // Mark Slot
            isFilled[row][col] = true;
        }
    }

    public void refreshTokenEvent(){
        for (int i=0; i < splendorDuelBoard.getChildCount(); i++){
            View chilView = splendorDuelBoard.getChildAt(i);
            Token token = chilView.findViewById(R.id.token_view);
            setOnClickListenerToken(token);
        }
    }

    public void setOnClickListenerToken(Token token){
        token.setOnClickListener(view1 -> selectToken(token));
    }

    private void selectToken(Token token) {
        Boolean selected = token.getSelected();
        Boolean valid = token.getValid();


        if (selectedToken.isEmpty() && checkIsGoldToken(token)){
            for (int i=0; i<movementPattern.length; i++){
                View chilView = splendorDuelBoard.getChildAt(i);
                CardView cardView = (CardView) chilView;
                Token currentToken = cardView.findViewById(R.id.token_view);
                currentToken.setValid(false);
                currentToken.setClickable(false);
                int color = ContextCompat.getColor(context, R.color.transparent);
                cardView.setCardBackgroundColor(color);
            }
            token.setIsSelected(true);
            token.setValid(false);
            token.setClickable(true);
            CardView cardView = getViewAt(token.getLocation().get(0), token.getLocation().get(1), splendorDuelBoard);
            if (cardView != null) {
                int color = ContextCompat.getColor(context, R.color.color4tokenIsChoose);
                cardView.setCardBackgroundColor(color);
            }
            selectedToken.add(token);
        }
        else  {
            if (!selected && valid) {
                token.setIsSelected(true);
                token.setValid(false);

                CardView cardView = getViewAt(token.getLocation().get(0), token.getLocation().get(1), splendorDuelBoard);
                if (cardView != null) {
                    int color = ContextCompat.getColor(context, R.color.color4tokenIsChoose);
                    cardView.setCardBackgroundColor(color);
                }

                selectedToken.add(token);

                disableAllGoldTokens(splendorDuelBoard);

                // Menampilkan Toast dalam thread utama jika diperlukan
                if (context != null) {
                    String message = "Add 1 Token\n" +
                            "Selected Tokens isSelected?: " + token.getSelected() + "\n" +
                            "Selected Tokens isValid?: " + token.getValid();
                    Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
                    TextView textView = new TextView(context);
                    textView.setText(message);
                    textView.setPadding(16, 16, 16, 16);
                    textView.setBackgroundColor(Color.BLACK);
                    textView.setTextColor(Color.WHITE);
                    toast.setView(textView);
                    toast.show();
                }
            }


            else if (selected && !valid) {
                token.setIsSelected(false);
                token.setValid(true);

                CardView cardView = getViewAt(token.getLocation().get(0), token.getLocation().get(1), splendorDuelBoard);
                if (cardView != null) {
                    Color color = token.getColor();
                    cardView.setCardBackgroundColor(color.toArgb());
                }

                selectedToken.remove(token);
                for (Token token1: selectedToken) {
                    token1.setClickable(true);
                }

                // Menampilkan Toast dalam thread utama jika diperlukan
                if (context != null) {
                    String message = "Add 1 Token\n" +
                            "Selected Tokens isSelected?: " + token.getSelected() + "\n" +
                            "Selected Tokens isValid?: " + token.getValid();
                    Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
                    TextView textView = new TextView(context);
                    textView.setText(message);
                    textView.setPadding(16, 16, 16, 16);
                    textView.setBackgroundColor(Color.BLACK);
                    textView.setTextColor(Color.WHITE);
                    toast.setView(textView);
                    toast.show();
                }
            }

            refreshValidToken();
        }

        if(!selectedToken.isEmpty()){
            taskBarTakeToken.setVisibility(View.VISIBLE);
        } else {
            taskBarTakeToken.setVisibility(View.INVISIBLE);
        }
    }

    public void testToast(List<View> view){
        if (selectedToken != null) {
            Toast.makeText(context, "Selected token size: " + selectedToken.size(), Toast.LENGTH_SHORT).show();
            removedView.addAll(view);
            refreshValidToken();
        } else {
            selectedToken = new ArrayList<>();
        }
    }

    public void refreshValidToken(){
        if (selectedToken.isEmpty()) {
            for (int i = 0; i < movementPattern.length; i++) {
                Log.d("refreshValidToken", "Checking child view at index: " + i);

                // Ensure that the movementPattern array has valid values for row and col
                if (i >= movementPattern.length) {
                    Log.e("Error", "Index " + i + " exceeds movementPattern length.");
                    continue;
                }

                int row = movementPattern[i][0];
                int col = movementPattern[i][1];

                View chilView = splendorDuelBoard.getChildAt(i);

                if (chilView == null) {
                    Log.e("Error", "Child view at index " + i + " is null.");
                    continue;
                }

                if (removedView.contains(chilView)) {
                    Log.d("refreshValidToken", "Skipping removed view at index " + i);
                    continue;
                }

                // Check if chilView is a CardView before casting
                if (chilView instanceof CardView) {
                    CardView cardView = (CardView) chilView;

                    // Ensure cardView is not null
                    if (cardView != null) {
                        Token currentToken = cardView.findViewById(R.id.token_view);

                        if (currentToken != null) {
                            currentToken.setClickable(true);
                            currentToken.setValid(true);
                            currentToken.setIsSelected(false);

                            cardView.setCardBackgroundColor(currentToken.getColor().toArgb());
                            Log.d("refreshValidToken", "Token at index " + i + " updated.");
                        } else {
                            Log.e("Error", "Token view is null for CardView at index " + i);
                        }
                    } else {
                        Log.e("Error", "CardView at index " + i + " is null.");
                    }
                } else {
                    Log.e("Error", "Child view at index " + i + " is not a CardView.");
                }
            }
        }
        else if (selectedToken.size() == 1) {
            for (int i=0; i<movementPattern.length; i++){
                if (movementPattern[i][0] == selectedToken.get(0).getLocation().get(0) && movementPattern[i][1] == selectedToken.get(0).getLocation().get(1)){
                    // current Location
                    continue;
                } else if (movementPattern[i][0] == (selectedToken.get(0).getLocation().get(0) + 1) && movementPattern[i][1] == selectedToken.get(0).getLocation().get(1)) {
                    // bot Location
                    View chilView = splendorDuelBoard.getChildAt(i);
                    updateTokenView(chilView);
                } else if (movementPattern[i][0] == (selectedToken.get(0).getLocation().get(0) - 1) && movementPattern[i][1] == selectedToken.get(0).getLocation().get(1)) {
                    // top Location
                    View chilView = splendorDuelBoard.getChildAt(i);
                    updateTokenView(chilView);
                } else if (movementPattern[i][0] == selectedToken.get(0).getLocation().get(0) && (movementPattern[i][1] == selectedToken.get(0).getLocation().get(1)+1)) {
                    // right Location
                    View chilView = splendorDuelBoard.getChildAt(i);
                    updateTokenView(chilView);
                } else if (movementPattern[i][0] == selectedToken.get(0).getLocation().get(0) && (movementPattern[i][1] == selectedToken.get(0).getLocation().get(1)-1)) {
                    // Left Location
                    View chilView = splendorDuelBoard.getChildAt(i);
                    updateTokenView(chilView);
                } else if ((movementPattern[i][0] == selectedToken.get(0).getLocation().get(0)-1) && (movementPattern[i][1] == selectedToken.get(0).getLocation().get(1)-1)) {
                    // Top-Left Location
                    View chilView = splendorDuelBoard.getChildAt(i);
                    updateTokenView(chilView);
                } else if ((movementPattern[i][0] == selectedToken.get(0).getLocation().get(0)-1) && (movementPattern[i][1] == selectedToken.get(0).getLocation().get(1)+1)) {
                    // Top-Right Location
                    View chilView = splendorDuelBoard.getChildAt(i);
                    updateTokenView(chilView);
                } else if ((movementPattern[i][0] == selectedToken.get(0).getLocation().get(0)+1) && (movementPattern[i][1] == selectedToken.get(0).getLocation().get(1)-1)) {
                    // Bot-Left Location
                    View chilView = splendorDuelBoard.getChildAt(i);
                    updateTokenView(chilView);
                }  else if ((movementPattern[i][0] == selectedToken.get(0).getLocation().get(0)+1) && (movementPattern[i][1] == selectedToken.get(0).getLocation().get(1)+1)) {
                    // Bot-Right Location
                    View chilView = splendorDuelBoard.getChildAt(i);
                    updateTokenView(chilView);
                } else {
                    View chilView = splendorDuelBoard.getChildAt(i);
                    if (chilView == null) {
                        continue;
                    }
                    CardView cardView = (CardView) chilView;
                    Token currentToken = cardView.findViewById(R.id.token_view);
                    currentToken.setClickable(false);
                    currentToken.setValid(false);
                    currentToken.setIsSelected(false);
                    int color = ContextCompat.getColor(context, R.color.transparent);
                    cardView.setCardBackgroundColor(color);
                }
            }
        }
        else if (selectedToken.size() == 2) {
            // Ambil koordinat token yang dipilih
            int row1 = selectedToken.get(0).getLocation().get(0);
            int col1 = selectedToken.get(0).getLocation().get(1);
            int row2 = selectedToken.get(1).getLocation().get(0);
            int col2 = selectedToken.get(1).getLocation().get(1);

            // Pastikan kedua token berada di kolom yang sama
            if (col1 == col2) {
                int smallestRow = Math.min(row1, row2);
                int largestRow = Math.max(row1, row2);

                for (int i = 0; i < movementPattern.length; i++) {
                    int row = movementPattern[i][0];
                    int col = movementPattern[i][1];

                    if ((row == smallestRow - 1 && col == col1) ||
                            (row == largestRow + 1 && col == col1)){
                        // Hint lokasi yang valid
                        View chilView = splendorDuelBoard.getChildAt(i);
                        updateTokenView(chilView);
                    } else if ((row == row1 && col == col1) ||
                            (row == row2 && col == col2)) {
                        continue;
                    } else {
                        // Non-hint lokasi
                        View chilView = splendorDuelBoard.getChildAt(i);
                        if (chilView == null) {
                            continue;
                        }
                        CardView cardView = (CardView) chilView;
                        Token currentToken = cardView.findViewById(R.id.token_view);
                        currentToken.setClickable(false);
                        currentToken.setValid(false);
                        currentToken.setIsSelected(false);
                        int color = ContextCompat.getColor(context, R.color.transparent);
                        cardView.setCardBackgroundColor(color);
                    }
                }
            }
            else if (row1 == row2) { //pastikan  keduanya dalam row yang sama
                int smallestCol = Math.min(col1, col2);
                int largestCol = Math.max(col1, col2);

                for (int i = 0; i < movementPattern.length; i++) {
                    int row = movementPattern[i][0];
                    int col = movementPattern[i][1];

                    if ((col == smallestCol - 1 && row == row1) || // Kiri
                            (col == largestCol + 1 && row == row1)) { // Kanan
                        // Hint lokasi yang valid
                        View chilView = splendorDuelBoard.getChildAt(i);
                        updateTokenView(chilView);
                    } else if ((row == row1 && col == col1) ||
                            (row == row2 && col == col2)) {
                        // Lokasi saat ini
                        continue;
                    } else {
                        // Non-hint lokasi
                        View chilView = splendorDuelBoard.getChildAt(i);
                        if (chilView == null) {
                            continue;
                        }
                        CardView cardView = (CardView) chilView;
                        Token currentToken = cardView.findViewById(R.id.token_view);
                        currentToken.setClickable(false);
                        currentToken.setValid(false);
                        currentToken.setIsSelected(false);
                        int color = ContextCompat.getColor(context, R.color.transparent);
                        cardView.setCardBackgroundColor(color);
                    }
                }
            }
            else if (Math.abs(row1 - row2) == Math.abs(col1 - col2)) {

                int [] head;
                int [] tail;

                if (Math.min(row1, row2) == row1){
                    if (Math.min(col1, col2) == col1){
                        head = new int[]{row1-1, col1-1};
                        tail = new int[]{row2+1, col2+1};
                    }
                    else{
                        head = new int[]{row1-1, col1+1};
                        tail = new int[]{row2+1, col2-1};
                    }
                }
                else{
                    if (Math.min(col1, col2) == col1){
                        head = new int[]{row2-1, col2+1};
                        tail = new int[]{row1+1, col1-1};
                    }
                    else{
                        head = new int[]{row2-1, col2-1};
                        tail = new int[]{row1+1, col1+1};
                    }
                }

                for (int i = 0; i < movementPattern.length; i++) {
                    int row = movementPattern[i][0];
                    int col = movementPattern[i][1];

                    // Valid diagonal hint locations
                    if ((row == head[0] && col == head[1]) || // Top-left
                            (row == tail[0] && col == tail[1])) {  // Bottom-right
                        View chilView = splendorDuelBoard.getChildAt(i);
                        updateTokenView(chilView);

                    }
                    else if ((row == row1 && col == col1) || (row == row2 && col == col2)) {
                        // Current token positions
                        continue;

                    }
                    else {
                        // Non-hint locations
                        View chilView = splendorDuelBoard.getChildAt(i);
                        if (chilView == null) {
                            continue;
                        }
                        CardView cardView = (CardView) chilView;
                        Token currentToken = cardView.findViewById(R.id.token_view);
                        currentToken.setClickable(false);
                        currentToken.setValid(false);
                        currentToken.setIsSelected(false);
                        int color = ContextCompat.getColor(context, R.color.transparent);
                        cardView.setCardBackgroundColor(color);
                    }
                }
            }

        }
        else {
            Token token1 = selectedToken.get(0);
            Token token2 = selectedToken.get(1);
            Token token3 = selectedToken.get(2);
            token2.setClickable(false);

            for (int i=0; i<movementPattern.length; i++){
                if ((movementPattern[i][0] == token1.getLocation().get(0) && movementPattern[i][1] == token1.getLocation().get(1)) ||
                        (movementPattern[i][0] == token2.getLocation().get(0) && movementPattern[i][1] == token2.getLocation().get(1)) ||
                        (movementPattern[i][0] == token3.getLocation().get(0) && movementPattern[i][1] == token3.getLocation().get(1))){
                    // current Location
                    continue;
                } else {
                    View chilView = splendorDuelBoard.getChildAt(i);
                    if (chilView == null) {
                        continue;
                    }
                    CardView cardView = (CardView) chilView;
                    Token currentToken = cardView.findViewById(R.id.token_view);
                    currentToken.setClickable(false);
                    currentToken.setValid(false);
                    currentToken.setIsSelected(false);
                    int color = ContextCompat.getColor(context, R.color.transparent);
                    cardView.setCardBackgroundColor(color);
                }
            }
        }
    }

    private void disableAllGoldTokens(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View childView = gridLayout.getChildAt(i);
            CardView cardView = (CardView) childView;
            Token currentToken = cardView.findViewById(R.id.token_view);

            if (checkIsGoldToken(currentToken)) {
                disableGoldToken(cardView);
            }
        }
    }

    private void disableGoldToken(CardView cardView) {
        // Pastikan token yang sesuai ditemukan
        Token currentToken = cardView.findViewById(R.id.token_view);

        currentToken.setValid(false);
        currentToken.setClickable(false);

        int transparentColor = ContextCompat.getColor(context, R.color.transparent);
        cardView.setCardBackgroundColor(transparentColor);

    }

    private Boolean checkIsGoldToken(Token token) {
        return token.getColor().equals(Color.valueOf(mainActivity.getResources().getColor(R.color.color4goldToken)));
    }

    private void updateTokenView(View view){
        if (view == null) {
            return;
        }
        CardView cardView = (CardView) view;
        Token currentToken = cardView.findViewById(R.id.token_view);
        if (!checkIsGoldToken(currentToken)){
            currentToken.setClickable(true);
            currentToken.setValid(true);
            currentToken.setIsSelected(false);
            cardView.setCardBackgroundColor(currentToken.getColor().toArgb());
        }
    }

    public CardView getViewAt(int row, int col, GridLayout gridLayout) {
        for (int i = 0; i < movementPattern.length; i++) {
            int[] position = movementPattern[i];
            int patternRow = position[0];
            int patternCol = position[1];

            if (patternRow == row && patternCol == col) {
                View childView = gridLayout.getChildAt(i);
                return (CardView) childView;
            }
        }
        return null;
    }

    private Token pickRandomToken() {
        if (tokenBag.isEmpty()) {
            return null;
        }
        int randomIndex = (int) (Math.random() * tokenBag.size());
        return tokenBag.remove(randomIndex);
    }

    public List<Token> getSelectedToken(){
        return selectedToken;
    }
}
