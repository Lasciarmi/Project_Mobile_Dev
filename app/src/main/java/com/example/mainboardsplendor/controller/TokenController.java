package com.example.mainboardsplendor.controller;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.mainboardsplendor.enumeration.ActiveTaskBar;
import com.example.mainboardsplendor.model.Card;
import com.example.mainboardsplendor.view.MainActivity;
import com.example.mainboardsplendor.R;
import com.example.mainboardsplendor.enumeration.TokenColor;
import com.example.mainboardsplendor.model.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private boolean[][] isFilled;

    private List<Token> tokenBag;
    private List<Token> tempTokenBag = new ArrayList<>();
    private final List<CardView> tokenPick = new ArrayList<>();
    private Context context;
    private MainActivity mainActivity;
    public List<Token> selectedToken = new ArrayList<>();
    private GridLayout splendorDuelBoard;

    private List<View> removedView = new ArrayList<>();

    public TokenController(List<Token> tokenBag, GridLayout splendorDuelBoard, Context context, MainActivity mainActivity) {
        this.tokenBag = tokenBag;
        this.context = context;
        this.mainActivity = mainActivity;
        this.splendorDuelBoard = splendorDuelBoard;
    }

    public void payCard(Card selectedCard, UserController currentPlayerController) {
        HashMap<TokenColor, Integer> price = selectedCard.getPrice();

        for (TokenColor tokenColor : price.keySet()) {
            FrameLayout currentFrame = mainActivity.getCurrentFrameLayout(tokenColor.getTokenColor(mainActivity));



            int tokenPrice = price.getOrDefault(tokenColor, 0); // Gunakan getOrDefault untuk aman
            if (currentFrame != null) {
                if (currentFrame.getChildCount() == 0) {
                    // Tidak ada kartu diskon, bayar penuh
                    currentPlayerController.payToken(mainActivity.getTokenBagGridLayout(tokenColor), tokenPrice, tokenColor);
                } else {
                    for (int i = 0; i < currentFrame.getChildCount(); i++) {
                        View child = currentFrame.getChildAt(i);
                        if (child instanceof Card) {
                            Card card = (Card) child;

                            Map<TokenColor, Integer> cardPrice = card.getPrice();
                            int discountCard = (cardPrice != null) ? cardPrice.getOrDefault(tokenColor, 0) : 0; // Pastikan tidak null

                            int priceDiscounted = Math.max(tokenPrice - discountCard, 0);

                            if (priceDiscounted > 0) {
                                currentPlayerController.payToken(mainActivity.getTokenBagGridLayout(tokenColor), priceDiscounted, tokenColor);
                            }
                        }
                    }
                }
            }
            else{
                currentPlayerController.payToken(mainActivity.getTokenBagGridLayout(tokenColor), tokenPrice, tokenColor);
            }
        }
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
        isFilled = new boolean[rowCount][colCount];

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
            if (color.equals(TokenColor.BLUE.getTokenColor(context))) {
                tokenImage.setImageResource(R.drawable.blue_token);
            } else if (color.equals(TokenColor.WHITE.getTokenColor(context))) {
                tokenImage.setImageResource(R.drawable.white_token);
            } else if (color.equals(TokenColor.GREEN.getTokenColor(context))) {
                tokenImage.setImageResource(R.drawable.green_token);
            } else if (color.equals(TokenColor.BLACK.getTokenColor(context))) {
                tokenImage.setImageResource(R.drawable.black_token);
            } else if (color.equals(TokenColor.RED.getTokenColor(context))) {
                tokenImage.setImageResource(R.drawable.red_token);
            } else if (color.equals(TokenColor.PEARL.getTokenColor(context))) {
                tokenImage.setImageResource(R.drawable.pearl_token);
            } else if (color.equals(TokenColor.GOLD.getTokenColor(context))) {
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

    public Integer getImageToken(TokenColor tokenColor){
        switch (tokenColor){
            case BLUE:
                return R.drawable.blue_token;
            case WHITE:
                return R.drawable.white_token;
            case GREEN:
                return R.drawable.green_token;
            case BLACK:
                return R.drawable.black_token;
            case RED:
                return R.drawable.red_token;
            case PEARL:
                return R.drawable.pearl_token;
            case GOLD:
                return R.drawable.gold_token;
            default:
                return null;
        }
    }

    public TokenColor mapColorToTokenColor(Color color) {
        int colorValue = color.toArgb();
        if (colorValue == ContextCompat.getColor(context, R.color.color4blueToken)) {
            return TokenColor.BLUE;
        } else if (colorValue == ContextCompat.getColor(context, R.color.white)) {
            return TokenColor.WHITE;
        } else if (colorValue == ContextCompat.getColor(context, R.color.color4greenToken)) {
            return TokenColor.GREEN;
        } else if (colorValue == ContextCompat.getColor(context, R.color.black)) {
            return TokenColor.BLACK;
        } else if (colorValue == ContextCompat.getColor(context, R.color.color4redToken)) {
            return TokenColor.RED;
        } else if (colorValue == ContextCompat.getColor(context, R.color.color4pearlToken)) {
            return TokenColor.PEARL;
        } else if (colorValue == ContextCompat.getColor(context, R.color.color4goldToken)) {
            return TokenColor.GOLD;
        }
        return null;
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

        mainActivity.setTaskBar(ActiveTaskBar.GEMS);
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
                refreshValidToken();
                disableAllGoldTokens(splendorDuelBoard);


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
            }

            refreshValidToken();
        }

        if(selectedToken.isEmpty()) {
            mainActivity.setTaskBar(ActiveTaskBar.NONE);
            mainActivity.cardRefreshValidCard();
        }

    }

    public void resetSelectedToken(List<View> view){
        if (selectedToken != null) {
            removedView.addAll(view);
            refreshValidToken();
        } else {
            selectedToken = new ArrayList<>();
        }
    }

    public void refreshValidToken(){
        if (selectedToken.isEmpty()) {
            for (int i = 0; i < movementPattern.length; i++) {

                if (i >= movementPattern.length) {
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
                    continue;
                }

                if (chilView instanceof CardView) {
                    CardView cardView = (CardView) chilView;

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
                }
            }
        }
        else if (selectedToken.size() == 1) {
            for (int i=0; i<movementPattern.length; i++){
                if (!mainActivity.getUsingScrollNow()){
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
                    } else if ((movementPattern[i][0] == selectedToken.get(0).getLocation().get(0)+1) && (movementPattern[i][1] == selectedToken.get(0).getLocation().get(1)+1)) {
                        // Bot-Right Location
                        View chilView = splendorDuelBoard.getChildAt(i);
                        updateTokenView(chilView);
                    } else{
                        disableAllTokenInBoardWithThisIndex(i);
                    }
                }
                else {
                    Token currentSelectedToken = selectedToken.get(0);
                    if ((movementPattern[i][0] == currentSelectedToken.getLocation().get(0) && movementPattern[i][1] == currentSelectedToken.getLocation().get(1))){
                        continue;
                    }
                    else{
                        disableAllTokenInBoardWithThisIndex(i);
                    }
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
                        disableAllTokenInBoardWithThisIndex(i);
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
                        disableAllTokenInBoardWithThisIndex(i);
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
                        disableAllTokenInBoardWithThisIndex(i);
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
                    disableAllTokenInBoardWithThisIndex(i);
                }
            }
        }

        if (mainActivity.getUsingScrollNow()){
            disableAllGoldTokens(splendorDuelBoard);
        }
    }

    private void disableAllTokenInBoardWithThisIndex(int i) {
        View chilView = splendorDuelBoard.getChildAt(i);
        if (chilView == null) {
            return;
        }
        CardView cardView = (CardView) chilView;
        Token currentToken = cardView.findViewById(R.id.token_view);
        currentToken.setClickable(false);
        currentToken.setValid(false);
        currentToken.setIsSelected(false);
        int color = ContextCompat.getColor(context, R.color.transparent);
        cardView.setCardBackgroundColor(color);
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

    public void addToken2Board() {
        for (int i = 0; i < movementPattern.length; i++) {
            if (tokenBag.isEmpty()){
                mainActivity.setTokenBagSize();
                return;
            }

            int row = movementPattern[i][0];
            int col = movementPattern[i][1];

            if (isFilled[row][col]) {
                continue;
            } else {
                Token newToken = pickRandomToken();
                if(newToken == null){
                    return;
                }

                View view = LayoutInflater.from(context).inflate(
                        R.layout.custom_token, splendorDuelBoard, false);

                Token tokenImage = view.findViewById(R.id.token_view);

                CardView cardView = view.findViewById(R.id.cardView_token);

                cardView.setCardBackgroundColor(newToken.getColor().toArgb());

                Color color = newToken.getColor();
                tokenImage.setColor(color);
                if (color.equals(TokenColor.BLUE.getTokenColor(context))) {
                    tokenImage.setImageResource(R.drawable.blue_token);
                } else if (color.equals(TokenColor.WHITE.getTokenColor(context))) {
                    tokenImage.setImageResource(R.drawable.white_token);
                } else if (color.equals(TokenColor.GREEN.getTokenColor(context))) {
                    tokenImage.setImageResource(R.drawable.green_token);
                } else if (color.equals(TokenColor.BLACK.getTokenColor(context))) {
                    tokenImage.setImageResource(R.drawable.black_token);
                } else if (color.equals(TokenColor.RED.getTokenColor(context))) {
                    tokenImage.setImageResource(R.drawable.red_token);
                } else if (color.equals(TokenColor.PEARL.getTokenColor(context))) {
                    tokenImage.setImageResource(R.drawable.pearl_token);
                } else if (color.equals(TokenColor.GOLD.getTokenColor(context))) {
                    tokenImage.setImageResource(R.drawable.gold_token);
                }

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(row);
                params.columnSpec = GridLayout.spec(col);
                params.setGravity(Gravity.FILL);
                view.setLayoutParams(params);

                ArrayList<Integer> location = new ArrayList<>();
                location.add(row);
                location.add(col);
                tokenImage.setLocation(location);

                setOnClickListenerToken(tokenImage);

                splendorDuelBoard.removeView(view);
                splendorDuelBoard.addView(view);

                // Mark Slot
                isFilled[row][col] = true;
            }
        }
    }

    public void setIsFilledFalse(List<Token> tokensToRemove){
        for (Token token: tokensToRemove){
            int row = token.getLocation().get(0);
            int col = token.getLocation().get(1);
            isFilled[row][col] = false;
        }
    }

}
