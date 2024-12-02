package com.example.mainboardsplendor.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mainboardsplendor.R;
import com.example.mainboardsplendor.enumeration.ActiveTaskBar;
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

    private ActivityMainBinding binding;

    private List<Token> tokenBag = new ArrayList<>();
    private List<Card> listCardLevel3 = new ArrayList<>();
    private List<Card> listCardLevel2 = new ArrayList<>();
    private List<Card> listCardLevel1 = new ArrayList<>();
    private List<RoyalCard> listRoyalCard = new ArrayList<>();
    private List<Token> selectedToken = new ArrayList<>();
    private Card selectedCard;
    private boolean dontChangePlayer = false;

    private User user1;
    private User user2;

    public TokenController tokenController;
    private UserController user1Controller;
    private UserController user2Controller;
    private CardController cardController;

    private GridLayout tokenGridLayout;

    private CardView taskBarTakeToken;
    private CardView taskBarPurchaseCard;
    private CardView taskBarUsePrivilege;
    private CardView taskBarReplenishBoard;

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

    private GridLayout cardReservedPlayer1;
    private GridLayout cardReservedPlayer2;

    private GridLayout tokenJokerPlayer1;
    private GridLayout tokenJokerPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
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
        // SET CURRENT PLAYER 1
        user1.setCurrent(true);

        // Init SCOREBOARD PLAYER
        user1Controller = new UserController(user1, binding.scoreBoardPlayer1, binding.layoutPlayer1Bag, this);
        user2Controller = new UserController(user2, binding.scoreBoardPlayer2, binding.layoutPlayer2Bag, this);
        user1Controller.setPlayerBoard();
        user2Controller.setPlayerBoard();

        // Init TOKEN BAG BOARD
        tokenGridLayout = binding.tokenBoard.splendorDuelBoard;

        // Init TASK BAR
        taskBarTakeToken = binding.taskBar.takeGemsTaskBar.cardViewTaskBar;
        taskBarPurchaseCard = binding.taskBar.purchaseCardTaskBar.cardViewTaskBar;
        taskBarUsePrivilege = binding.taskBar.taskBarUsePrivilege;
        taskBarReplenishBoard = binding.taskBar.replenishBoardTaskBar.cardViewTaskBar;
        setTaskBar(ActiveTaskBar.NONE);

        //get Button from task bar
        Button takeTokenButton = taskBarTakeToken.findViewById(R.id.task_button);
        Button purchaseCardButton = taskBarPurchaseCard.findViewById(R.id.task_button);
        Button usePrivilegeButton = taskBarUsePrivilege.findViewById(R.id.task_button);

        // get gridLayout for reserved card and joker
        cardReservedPlayer1 = binding.layoutPlayer1Bag.cardReservedPlayer;
        cardReservedPlayer2 = binding.layoutPlayer2Bag.cardReservedPlayer;
        tokenJokerPlayer1 = binding.layoutPlayer1Bag.jokerGridLayout;
        tokenJokerPlayer2 = binding.layoutPlayer2Bag.jokerGridLayout;

        // BUTTON ON CLICK LISTENER
        takeTokenButton.setOnClickListener(v -> {
            takeTokenButtonAction();
        });
        purchaseCardButton.setOnClickListener(v -> {
            purchaseButtonAction();
        });
//        usePrivilegeButton.setOnClickListener(v -> {
//            // TODO: 12/1/2024
//        });


        // Init TokenBag on Board
        tokenController = new TokenController(tokenBag, tokenGridLayout,this, this);
        tokenController.initTokenBag();

        // Init TOKEN ON BOARD SPIRAL
        int rowCount =tokenGridLayout.getRowCount();
        int colCount = tokenGridLayout.getColumnCount();
        tokenController.InitTokenBoard(rowCount, colCount);

        // GET LIST SELECTEDTOKEN
        selectedToken = tokenController.getSelectedToken();

        // SET TOKEN BAG SIZE ON TEXT
        binding.tokenBoard.numTokenBag.setText(String.valueOf(tokenBag.size()));

        // Init CardBoard
        GridLayout royalCard = binding.cardBoard.royalCard; //tes
        cardController = new CardController(binding.cardBoard.cardStoreTop, binding.cardBoard.cardStoreMid, binding.cardBoard.cardStoreBot, royalCard, listCardLevel1, listCardLevel2, listCardLevel3, listRoyalCard,this, this, selectedCard);
        cardController.InitCardTopDeck();
        cardController.InitCardMidDeck();
        cardController.InitCardBotDeck();
        cardController.setCardGridLayout();
        cardController.InitCardBoard();

        // Init ReversedCard
        cardController.InitRoyalCard();
        cardController.InitReservedCardBoard();
        cardController.refreshValidCrownCard(getCurrentPlayerController());

        // check valid card (TODO: DELETE)
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

    }

    public void setTokenBagSize(List<Token> tokenBag) {
        // Hitung jumlah token di dalam tokenBag
        int tokenCount = tokenBag.size();

        // Perbarui TextView dengan jumlah token
        binding.tokenBoard.numTokenBag.setText(String.valueOf(tokenCount));
    }

    public void setCardDeckSize(List<Card> listCard) {
        int cardCount = listCard.size();
        TextView size = sizeCardDeck(listCard);
        size.setText(String.valueOf(cardCount));
    }

    public TextView sizeCardDeck(List<Card> listCard){
        if(listCard.equals(listCardLevel1)){
            return binding.cardBoard.totalDeck3;
        } else if (listCard.equals(listCardLevel2)){
            return binding.cardBoard.totalDeck2;
        } else if (listCard.equals(listCardLevel3)){
            return binding.cardBoard.totalDeck1;
        } else return null;
    }

    public GridLayout getCardReservedPlayer(){
        if(user1.getCurrent()) return cardReservedPlayer1; else return cardReservedPlayer2;
    }

    private GridLayout getTokenJokerPlayer(){
        if(user1.getCurrent()) return tokenJokerPlayer1; else return tokenJokerPlayer2;
    }

    private void purchaseButtonAction() {
        this.selectedCard = cardController.getSelectedCard();
        if (selectedCard != null){

            UserController currentPlayerController = getCurrentPlayerController();

            cardController.removeAndAddNewCardInBoard(selectedCard);

            if (!selectedToken.isEmpty()){

                // joker
                GridLayout tokenJokerPlayerGrid = getTokenJokerPlayer();
                currentPlayerController.setOwnedToken(TokenColor.GOLD);
                currentPlayerController.setTokenBagPlayer(TokenColor.GOLD, tokenJokerPlayerGrid, R.drawable.gold_token);

                // buang token Done
                View tokenView = tokenController.getViewAt(selectedToken.get(0).getLocation().get(0), selectedToken.get(0).getLocation().get(1), tokenGridLayout);
                tokenView.setVisibility(View.INVISIBLE);
                selectedToken.remove(0);
                List<View> viewsToRemove = new ArrayList<>();
                viewsToRemove.add(tokenView);
                tokenController.resetSelectedToken(viewsToRemove);
                tokenController.refreshTokenEvent();

                // reserved card
                GridLayout cardReservedGridLayout = getCardReservedPlayer();
                selectedCard.setCurrentGridLayout(cardReservedGridLayout);
                selectedCard.setReserved(true);
                selectedCard.setCardIndexOnGridLayout(cardReservedGridLayout.getChildCount());
                cardReservedGridLayout.addView(selectedCard);

            }
            else{
                FrameLayout currentFrameLayout = getCurrentFrameLayout(selectedCard.getColor());
                cardController.addNewCard(currentFrameLayout);
                tokenController.payCard(selectedCard, currentPlayerController);
                currentPlayerController.setOwnedCard(selectedCard);
            }
            cardController.cardClicked(getCurrentPlayerController(), selectedCard);
            this.selectedCard = cardController.getSelectedCard();
            currentPlayerController.setPlayerBoard();
            victoryCondition();
            changeCurrentPlayer();
        }
    }

    public FrameLayout getCurrentFrameLayout(Color color){
        if(color.equals(TokenColor.BLUE.getTokenColor(this))){
            if(user1.getCurrent()) return binding.layoutPlayer1Bag.blueCardStack; else return binding.layoutPlayer2Bag.blueCardStack;
        } else if (color.equals(TokenColor.WHITE.getTokenColor(this))){
            if(user1.getCurrent()) return binding.layoutPlayer1Bag.whiteCardStack; else return binding.layoutPlayer2Bag.whiteCardStack;
        } else if (color.equals(TokenColor.GREEN.getTokenColor(this))){
            if(user1.getCurrent()) return binding.layoutPlayer1Bag.greenCardStack; else return binding.layoutPlayer2Bag.greenCardStack;
        } else if (color.equals(TokenColor.BLACK.getTokenColor(this))){
            if(user1.getCurrent()) return binding.layoutPlayer1Bag.blackCardStack; else return binding.layoutPlayer2Bag.blackCardStack;
        } else if (color.equals(TokenColor.RED.getTokenColor(this))){
            if(user1.getCurrent()) return binding.layoutPlayer1Bag.redCardStack; else return binding.layoutPlayer2Bag.redCardStack;
        }
        return null;
    }

    public void setTaskBar(ActiveTaskBar activeTaskBar){
        switch (activeTaskBar){
            case GEMS:
                taskBarTakeToken.setVisibility(View.VISIBLE);
                taskBarPurchaseCard.setVisibility(View.GONE);
                TextView textToken= taskBarTakeToken.findViewById(R.id.text_task1);
                Button textTokenButton = taskBarTakeToken.findViewById(R.id.task_button);
                textToken.setText("You must select a Token");
                textTokenButton.setText("Take Selected Token");
                break;
            case CARD:
                taskBarPurchaseCard.setVisibility(View.VISIBLE);
                taskBarTakeToken.setVisibility(View.GONE);
                TextView textCard = taskBarPurchaseCard.findViewById(R.id.text_task1);
                Button textCardButon = taskBarPurchaseCard.findViewById(R.id.task_button);
                textCard.setText("You must select a card");
                textCardButon.setText("Take Selected Card");
                //setelah dipilih jangan lupa set None biar hilang textnya
                break;

            case SCROLL:
                taskBarTakeToken.setVisibility(View.INVISIBLE);
                taskBarPurchaseCard.setVisibility(View.GONE);
                taskBarUsePrivilege.setVisibility(View.VISIBLE);
                break;

            case NONE:
                taskBarTakeToken.setVisibility(View.INVISIBLE);
                taskBarPurchaseCard.setVisibility(View.GONE);
                taskBarUsePrivilege.setVisibility(View.GONE);
                taskBarReplenishBoard.setVisibility(View.GONE);
                TextView textView = taskBarReplenishBoard.findViewById(R.id.text_task1);
                Button button = taskBarReplenishBoard.findViewById(R.id.task_button);
                textView.setText("Before taking your mandatory action, you can ");
                button.setText("Replenish the Board");
                break;
        }
    }

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
        }
        return null;
    }

    // Method for add Token Stack on bag player
    private void takeTokenButtonAction() {
    // Method for add Card Stack on bag player

        int totalTokens = getCurrentPlayerController().getUser().getTotalTokens();

        // Check if the player exceeds maximum bag capacity
        if (totalTokens + selectedToken.size() > 10) {
            Toast.makeText(this, "You cannot take any more tokens. Your bag will exceed the maximum capacity!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a list to hold the views to remove
        List<View> viewsToRemove = new ArrayList<>();
        List<Token> tokensToRemove = new ArrayList<>();

        UserController currentPlayerController = getCurrentPlayerController();

        // Collect views to remove and tokens to remove in separate lists
        for (Token token : selectedToken) {
            TokenColor tokenColor = tokenController.mapColorToTokenColor(token.getColor());
            if (tokenColor.equals(TokenColor.GOLD) && selectedCard == null){
                if (currentPlayerController.getUser().getReserveCard() == 3) {
                    Toast.makeText(this, "You can't hold more than 3 Reserved Card!", Toast.LENGTH_SHORT).show();
                    return;
                }
                setTaskBar(ActiveTaskBar.CARD);
                cardController.refreshForReverseCard(currentPlayerController, tokenColor);

                this.dontChangePlayer = true;
            }
            else{
                this.dontChangePlayer = false;
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
                setTaskBar(ActiveTaskBar.NONE);
            }
        }

        if (!dontChangePlayer){
            for (View view : viewsToRemove) {
    //            tokenGridLayout.removeView(view);
                view.setVisibility(View.INVISIBLE);
            }

            // Remove the selected tokens from the list
            selectedToken.removeAll(tokensToRemove);

            tokenController.resetSelectedToken(viewsToRemove);
            tokenController.refreshTokenEvent();

            getCurrentPlayerController().setPlayerBoard();

            victoryCondition();
            changeCurrentPlayer();
        }
        // Remove the collected views after the loop

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
        cardController.refreshValidCard(getCurrentPlayerController());
    }

    public void victoryCondition(){
        if (getCurrentPlayerController().getUser().getCardsPoint() == 20){
            showDialog("Congratulations " + getCurrentPlayerController().getUser().getUsername() + "! \n You win with 20 point");
        } else if (getCurrentPlayerController().getUser().getCrowns() == 10) {
            showDialog("Congratulations " + getCurrentPlayerController().getUser().getUsername() + "! \n You win with 3 crown");
        } else if (getCurrentPlayerController().getUser().getMostSameCardColorValue() == 10) {
            showDialog("Congratulations " + getCurrentPlayerController().getUser().getUsername() + "! \n You win with 10 same card color");
        }
    }
    private void showDialog(String message) {
        // Create a custom dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate the custom layout (optional, only if you want custom layout)
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout, null);
        builder.setView(dialogView);

        // Find the TextView in the dialog layout and set the message
        TextView dialogMessageTextView = dialogView.findViewById(R.id.dialog_message);
        dialogMessageTextView.setText(message);

        // Set the OK button
        Button okButton = dialogView.findViewById(R.id.dialog_ok_button);
        okButton.setOnClickListener(v -> OpenStartUpActivity());

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void OpenStartUpActivity() {
        Intent intent = new Intent(this, StartUpActivity.class);
        startActivity(intent);
    }

}