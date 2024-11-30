package com.example.mainboardsplendor.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mainboardsplendor.R;
import com.example.mainboardsplendor.enumeration.TokenColor;
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

public class MainActivity extends AppCompatActivity {

    private List<Token> tokenBag = new ArrayList<>();
    private List<Card> listCardLevel3 = new ArrayList<>();
    private List<Card> listCardLevel2 = new ArrayList<>();
    private List<Card> listCardLevel1 = new ArrayList<>();
    private List<RoyalCard> listRoyalCard = new ArrayList<>();
    private List<Token> selectedToken = new ArrayList<>();

    private User user1;
    private User user2;

    private TokenController tokenController;
    private UserController user1Controller;
    private UserController user2Controller;
    private CardController cardController;

    private GridLayout tokenGridLayout;
    private CardView taskBarTakeToken;

    private GridLayout blueTokenBagPlayer1;
    private GridLayout blueTokenBagPlayer2;
    private GridLayout whiteTokenBagPlayer1;
    private GridLayout whiteTokenBagPlayer2;
    private GridLayout greenTokenBagPlayer1;
    private GridLayout greenTokenBagPlayer2;
    private GridLayout blackTokenBagPlayer1;
    private GridLayout blackTokenBagPlayer2;
    private GridLayout redTokenBagPlayer1;
    private GridLayout redTokenBagPlayer2;
    private GridLayout pearlTokenBagPlayer1;
    private GridLayout pearlTokenBagPlayer2;
    private GridLayout goldTokenBagPlayer1;
    private GridLayout goldTokenBagPlayer2;

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
        // SET CURRENT PLAYER 1
        user1.setCurrent(true);
        user2 = new User(intent.getStringExtra(CreateUserActivity.PLAYER_2));

        // Init SCOREBOARD PLAYER
        user1Controller = new UserController(user1, binding.scoreBoardPlayer1, binding.layoutPlayer1Bag, this);
        user2Controller = new UserController(user2, binding.scoreBoardPlayer2, binding.layoutPlayer2Bag, this);

        user1Controller.setPlayerBoard();
        user2Controller.setPlayerBoard();

        // Init TOKEN BAG BOARD
        tokenGridLayout = binding.tokenBoard.splendorDuelBoard;
        taskBarTakeToken = binding.taskBar.taskBarTakeGems;
        Button takeButton = taskBarTakeToken.findViewById(R.id.take_button);

        tokenController = new TokenController(tokenBag, tokenGridLayout, taskBarTakeToken,this, this);

        tokenController.initTokenBag();

        // Init TOKEN ON BOARD SPIRAL
        int rowCount =tokenGridLayout.getRowCount();
        int colCount = tokenGridLayout.getColumnCount();
        tokenController.InitTokenBoard(rowCount, colCount);

        // GET LIST SELECTEDTOKEN
        selectedToken = tokenController.getSelectedToken();
        // TAKE BUTTON ON CLICK LISTENER
        takeButton.setOnClickListener(v -> {
            takeButtonAction();
        });

        // SET TOKEN BAG SIZE ON TEXT
        binding.tokenBoard.numTokenBag.setText(String.valueOf(tokenBag.size()));

        binding.taskBar.taskBarTakeGems.setVisibility(View.INVISIBLE);
        binding.taskBar.taskBarUsePrivilege.setVisibility(View.GONE);

        // Init CardBoard
        cardController = new CardController(binding.cardBoard.cardStoreTop, binding.cardBoard.cardStoreMid, binding.cardBoard.cardStoreBot, binding.cardBoard.reservedCard, listCardLevel1, listCardLevel2, listCardLevel3, listRoyalCard,this, this);
        cardController.InitCardTopDeck();
        cardController.InitCardMidDeck();
        cardController.InitCardBotDeck();
        cardController.InitCardBoard();

        cardController.InitReservedCard();
        cardController.InitReservedCardBoard();

//        InitGridBag(binding);

        cardController.refreshValidCard(user1Controller);

        // Binding all token bag player
        blueTokenBagPlayer1 = binding.layoutPlayer1Bag.listBlueToken.listToken;
        blueTokenBagPlayer2 = binding.layoutPlayer2Bag.listBlueToken.listToken;
        whiteTokenBagPlayer1 = binding.layoutPlayer1Bag.listWhiteToken.listToken;
        whiteTokenBagPlayer2 = binding.layoutPlayer2Bag.listWhiteToken.listToken;
        greenTokenBagPlayer1 = binding.layoutPlayer1Bag.listGreenToken.listToken;
        greenTokenBagPlayer2 = binding.layoutPlayer2Bag.listGreenToken.listToken;
        blackTokenBagPlayer1 = binding.layoutPlayer1Bag.listBlackToken.listToken;
        blackTokenBagPlayer2 = binding.layoutPlayer2Bag.listBlackToken.listToken;
        redTokenBagPlayer1 = binding.layoutPlayer1Bag.listRedToken.listToken;
        redTokenBagPlayer2 = binding.layoutPlayer2Bag.listRedToken.listToken;
        pearlTokenBagPlayer1 = binding.layoutPlayer1Bag.listPearlToken.listToken;
        pearlTokenBagPlayer2 = binding.layoutPlayer2Bag.listPearlToken.listToken;
        goldTokenBagPlayer1 = binding.layoutPlayer1Bag.listGoldToken.listToken;
        goldTokenBagPlayer2 = binding.layoutPlayer2Bag.listGoldToken.listToken;
    }


//    private void InitGridBag(ActivityMainBinding binding) {
//        // Init Grid list_blue_token pada bag player
//        for (int i = 0; i< 4; i++) {
//            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listBlueToken.listToken, false);
//            ImageView tokenView = view.findViewById(R.id.token_view);
//            CardView cardView = view.findViewById(R.id.cardView_token);
//
//            // Set Image token
//            tokenView.setImageResource(R.drawable.blue_token);
//            // Set bg CardView token
//            int color4blueToken = TokenColor.BLUE.getTokenColorInt(this);
//            cardView.setCardBackgroundColor(color4blueToken);
//            view.setVisibility(View.VISIBLE);
//            binding.layoutPlayer1Bag.listBlueToken.listToken.addView(view);
//        }
//
//        // Init Grid list_white_token pada bag player
//        for (int i = 0; i< 4; i++) {
//            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listWhiteToken.listToken, false);
//            ImageView tokenView = view.findViewById(R.id.token_view);
//            CardView cardView = view.findViewById(R.id.cardView_token);
//
//            // Set Image token
//            tokenView.setImageResource(R.drawable.white_token);
//            // Set bg CardView token
//            int color4whiteToken = TokenColor.WHITE.getTokenColorInt(this);
//            cardView.setCardBackgroundColor(color4whiteToken);
//            view.setVisibility(View.VISIBLE);
//            binding.layoutPlayer1Bag.listWhiteToken.listToken.addView(view);
//        }
//
//        // Init Grid list_green_token pada bag player
//        for (int i = 0; i< 4; i++) {
//            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listGreenToken.listToken, false);
//            ImageView tokenView = view.findViewById(R.id.token_view);
//            view.setVisibility(View.INVISIBLE);
//            CardView cardView = view.findViewById(R.id.cardView_token);
//
//            // Set Image token
//            tokenView.setImageResource(R.drawable.green_token);
//            // Set bg CardView token
//            int color4greenToken = TokenColor.GREEN.getTokenColorInt(this);
//            cardView.setCardBackgroundColor(color4greenToken);
//            view.setVisibility(View.VISIBLE);
//            binding.layoutPlayer1Bag.listGreenToken.listToken.addView(view);
//        }
//
//        // Init Grid list_black_token pada bag player
//        for (int i = 0; i< 4; i++) {
//            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listBlackToken.listToken, false);
//            ImageView tokenView = view.findViewById(R.id.token_view);
//            CardView cardView = view.findViewById(R.id.cardView_token);
//
//            // Set Image token
//            tokenView.setImageResource(R.drawable.black_token);
//            // Set bg CardView token
//            int color4blackToken = TokenColor.BLACK.getTokenColorInt(this);
//            cardView.setCardBackgroundColor(color4blackToken);
//            view.setVisibility(View.VISIBLE);
//            binding.layoutPlayer1Bag.listBlackToken.listToken.addView(view);
//        }
//
//        // Init Grid list_red_token pada bag player
//        for (int i = 0; i< 4; i++) {
//            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listRedToken.listToken, false);
//            ImageView tokenView = view.findViewById(R.id.token_view);
//            CardView cardView = view.findViewById(R.id.cardView_token);
//
//            // Set Image token
//            tokenView.setImageResource(R.drawable.red_token);
//            // Set bg CardView token
//            int color4redToken = TokenColor.RED.getTokenColorInt(this);
//            cardView.setCardBackgroundColor(color4redToken);
//            view.setVisibility(View.VISIBLE);
//            binding.layoutPlayer1Bag.listRedToken.listToken.addView(view);
//        }
//
//        for (int i = 0; i< 2; i++) {
//            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listPearlToken.listToken, false);
//            ImageView tokenView = view.findViewById(R.id.token_view);
//            CardView cardView = view.findViewById(R.id.cardView_token);
//
//            // Set Image token
//            tokenView.setImageResource(R.drawable.pearl_token);
//            // Set bg CardView token
//            int color4pearlToken = TokenColor.PEARL.getTokenColorInt(this);
//            cardView.setCardBackgroundColor(color4pearlToken);
//            view.setVisibility(View.VISIBLE);
//            binding.layoutPlayer1Bag.listPearlToken.listToken.addView(view);
//        }
//
//        for (int i = 0; i< 2; i++) {
//            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listGoldToken.listToken, false);
//            ImageView tokenView = view.findViewById(R.id.token_view);
//            CardView cardView = view.findViewById(R.id.cardView_token);
////            ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
////            layoutParams.width = (int) (35 * getResources().getDisplayMetrics().density);
////            layoutParams.height = (int) (35 * getResources().getDisplayMetrics().density);
////            float cornerRadiusInPx = 17.5f * getResources().getDisplayMetrics().density;
//
//            // Set Image token
//            tokenView.setImageResource(R.drawable.gold_token);
//            // Set bg CardView token
//            int color4goldToken = TokenColor.GOLD.getTokenColorInt(this);
//            cardView.setCardBackgroundColor(color4goldToken);
////            cardView.setLayoutParams(layoutParams);
////            cardView.setRadius(cornerRadiusInPx);
//            view.setVisibility(View.VISIBLE);
//            binding.layoutPlayer1Bag.listGoldToken.listToken.addView(view);
//        }
//
//        // INIT CARD_STACK
//        addNewCard(binding.layoutPlayer1Bag.blueCardStack);
//        addNewCard(binding.layoutPlayer1Bag.blueCardStack);
//        addNewCard(binding.layoutPlayer1Bag.blueCardStack);
//        addNewCard(binding.layoutPlayer1Bag.blueCardStack);
//
//        addNewCard(binding.layoutPlayer1Bag.redCardStack);
//        addNewCard(binding.layoutPlayer1Bag.redCardStack);
//        addNewCard(binding.layoutPlayer1Bag.redCardStack);
//        addNewCard(binding.layoutPlayer1Bag.redCardStack);
//    }

    public GridLayout getTokenBagGridLayout(TokenColor color){
        if(color == TokenColor.BLUE){
            if(user1.getCurrent()) return blueTokenBagPlayer1; else return blueTokenBagPlayer2;
        } else if (color == TokenColor.WHITE){
            if(user1.getCurrent()) return whiteTokenBagPlayer1; else return whiteTokenBagPlayer2;
        } else if (color == TokenColor.GREEN){
            if(user1.getCurrent()) return greenTokenBagPlayer1; else return greenTokenBagPlayer2;
        } else if (color == TokenColor.BLACK){
            if(user1.getCurrent()) return blackTokenBagPlayer1; else return blackTokenBagPlayer2;
        } else if (color == TokenColor.RED){
            if(user1.getCurrent()) return redTokenBagPlayer1; else return redTokenBagPlayer2;
        } else if (color == TokenColor.PEARL){
            if(user1.getCurrent()) return pearlTokenBagPlayer1; else return pearlTokenBagPlayer2;
        } else if (color == TokenColor.GOLD) {
            if(user1.getCurrent()) return goldTokenBagPlayer1; else return goldTokenBagPlayer2;
        }
        return null;
    }

    // Method for add Card Stack on bag player
    private void takeButtonAction() {
        // Create a list to hold the views to remove
        List<View> viewsToRemove = new ArrayList<>();
        List<Token> tokensToRemove = new ArrayList<>();

        UserController currentPlayerController = getCurrentPlayerController();


        // Collect views to remove and tokens to remove in separate lists
        for (Token token : selectedToken) {
            TokenColor tokenColor = tokenController.mapColorToTokenColor(token.getColor());
            currentPlayerController.setOwnedToken(tokenColor);

            GridLayout tokenBagGridLayout = getTokenBagGridLayout(tokenColor);
            int tokenImage = tokenController.getImageToken(tokenColor);

            currentPlayerController.setTokenBagPlayer(tokenColor, tokenBagGridLayout, tokenImage);
            View view = tokenController.getViewAt(token.getLocation().get(0), token.getLocation().get(1), tokenGridLayout);
            view.getId();
            if (view != null) {
                viewsToRemove.add(view); // Add the view to the list
            }
            tokensToRemove.add(token); // Add the token to the list for removal
        }

        // Remove the collected views after the loop
        for (View view : viewsToRemove) {
//            tokenGridLayout.removeView(view);
            view.setVisibility(View.INVISIBLE);
        }

        // Remove the selected tokens from the list
        selectedToken.removeAll(tokensToRemove);

        tokenController.resetSelectedToken(viewsToRemove);
        tokenController.refreshTokenEvent();

//        String message = "Token Bag: " + currentPlayerController.getOwnedToken().toString();
//        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
//        TextView textView = new TextView(this);
//        textView.setText(message);
//        textView.setPadding(16, 16, 16, 16);
//        textView.setBackgroundColor(Color.BLACK);
//        textView.setTextColor(Color.WHITE);
//        toast.setView(textView);
//        toast.show();

        changeCurrentPlayer();
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

    private UserController getCurrentPlayerController() {
        if (user1.getCurrent()){
            return user1Controller;
        }
        return user2Controller;
    }

    private void changeCurrentPlayer() {
        if (user1.getCurrent()) {
            user1.setCurrent(false);
            user2.setCurrent(true);
        } else {
            user2.setCurrent(false);
            user1.setCurrent(true);
        }
        user1Controller.setPlayerBoard();
        user2Controller.setPlayerBoard();
    }

}