package com.example.mainboardsplendor.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
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
    private boolean isUsingScrollNow = false;
    private int privilegeOnBoard = 3;

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

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mediaPlayer = MediaPlayer.create(this, R.raw.shinesfx);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get Player 1 and 2 Username
        Intent intent = getIntent();
        user1 = new User(intent.getStringExtra(CreateUserActivity.PLAYER_1));
        user1.setScroll(2);
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


        //get Button from task bar
        Button takeTokenButton = taskBarTakeToken.findViewById(R.id.task_button);
        Button purchaseCardButton = taskBarPurchaseCard.findViewById(R.id.task_button);
        Button usePrivilegeButton = taskBarUsePrivilege.findViewById(R.id.privilege_button);
        Button replenishBoardButton = taskBarReplenishBoard.findViewById(R.id.task_button);

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
        usePrivilegeButton.setOnClickListener(v -> {
            usePrivilegeButtonAction();
        });
        replenishBoardButton.setOnClickListener(v -> {
            replenishTokenButtonAction();
        });


        // Init TokenBag on Board
        tokenController = new TokenController(tokenBag, tokenGridLayout,this, this);
        tokenController.initTokenBag();

        // Init TOKEN ON BOARD SPIRAL
        int rowCount =tokenGridLayout.getRowCount();
        int colCount = tokenGridLayout.getColumnCount();
        tokenController.InitTokenBoard(rowCount, colCount);

        // GET LIST SELECTED TOKEN
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

        TextView textViewReplenish = taskBarReplenishBoard.findViewById(R.id.text_task1);
        Button buttonReplenish = taskBarReplenishBoard.findViewById(R.id.task_button);
        textViewReplenish.setText("Before taking your mandatory action, you can ");
        buttonReplenish.setText("Replenish the Board");
        setTaskBar(ActiveTaskBar.NONE);
    }

    public void setTokenBagSize() {
        if (tokenBag.isEmpty()){
            int tokenCount = 0;
            binding.tokenBoard.numTokenBag.setText(String.valueOf(tokenCount));
            return;
        }
        int tokenCount = tokenBag.size();

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

    public List<Token> getTokenBag(){
        return tokenBag;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    public void playSFX() {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        }
    }

    public boolean getUsingScrollNow(){
        return isUsingScrollNow;
    }

    public GridLayout getCardReservedPlayer(){
        if(user1.getCurrent()) return cardReservedPlayer1; else return cardReservedPlayer2;
    }

    private GridLayout getTokenJokerPlayer(){
        if(user1.getCurrent()) return tokenJokerPlayer1; else return tokenJokerPlayer2;
    }

    private void refreshAndChangeThePlayer() {
        getCurrentPlayerController().setPlayerBoard();
        victoryCondition();
        changeCurrentPlayer();
        setTaskBar(ActiveTaskBar.NONE);
        playSFX();
    }

    private void usePrivilegeButtonAction() {
        if (!selectedToken.isEmpty()){
            Toast.makeText(this, "You must unselect selected token", Toast.LENGTH_SHORT).show();
            return;
        }
        isUsingScrollNow = true;
        dontChangePlayer = true;
        getCurrentPlayerController().useScroll();
        setTaskBar(ActiveTaskBar.GEMS);
        tokenController.refreshValidToken();
    }

    private void replenishTokenButtonAction() {
        if(tokenBag.isEmpty()){
            Toast.makeText(this, "Token bag is Empty", Toast.LENGTH_SHORT).show();
            taskBarReplenishBoard.setVisibility(View.GONE);
            return;
        }
        if(privilegeOnBoard > 0){
            takeAvailablePrivilege(getCurrentPlayerController());
        }
        tokenController.addToken2Board();
        taskBarReplenishBoard.setVisibility(View.GONE);
    }

    private void purchaseButtonAction() {
        this.selectedCard = getSelectedCard();
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
            cardController.cardClicked(getCurrentPlayerController(), getSelectedCard());
            this.selectedCard = getSelectedCard();
            refreshAndChangeThePlayer();
        }
    }

    public void cardRefreshValidCard() {
        cardController.refreshValidCard(getCurrentPlayerController());
    }

    public Card getSelectedCard(){
        return cardController.getSelectedCard();
    }

    // Method for add Token Stack on bag player
    private void takeTokenButtonAction() {
        // Method for add Card Stack on bag player

        int totalTokens = getCurrentPlayerController().getUser().getTotalTokens();

        // Check if the player exceeds maximum bag capacity
        if (totalTokens + selectedToken.size() > 10) {
            showCustomDialog("You cannot take these tokens because it will exceed your bag's capacity!", "OK", null);
            return;
        }

        // Create a list to hold the views to remove
        List<View> viewsToRemove = new ArrayList<>();
        List<Token> tokensToRemove = new ArrayList<>();

        UserController currentPlayerController = getCurrentPlayerController();

        boolean isPass = false;
        if(selectedToken.size() >= 2){
            if (selectedToken.size() == 2){
                if (selectedToken.get(0).getColor().equals(selectedToken.get(1).getColor())
                        && selectedToken.get(0).getColor().equals(TokenColor.PEARL.getTokenColor(this))){
                    isPass = true;
                }
            }
            else{
                if (selectedToken.size() == 3){
                    if (selectedToken.get(0).getColor().equals(selectedToken.get(1).getColor())
                        && selectedToken.get(0).getColor().equals(selectedToken.get(2).getColor())){
                        isPass = true;
                    }
                }
            }
        }
        if (isPass && privilegeOnBoard > 0){
            takeAvailablePrivilege(currentPlayerController);
        }

        // Collect views to remove and tokens to remove in separate lists
        for (Token token : selectedToken) {
            TokenColor tokenColor = tokenController.mapColorToTokenColor(token.getColor());
            if (tokenColor.equals(TokenColor.GOLD) && selectedCard == null) {
                if (currentPlayerController.getUser().getReserveCard() == 3) {
                    showCustomDialog("You can't hold more than 3 Reserved Cards!", "OK", null);
                    return;
                }
                setTaskBar(ActiveTaskBar.CARD);
                cardController.refreshForReverseCard(currentPlayerController, tokenColor);

                this.dontChangePlayer = true;
            }
            else{
                if(!isUsingScrollNow){
                    this.dontChangePlayer = false;
                }
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

        if (!tokensToRemove.isEmpty()){
            tokenController.setIsFilledFalse(tokensToRemove);
        }

        if (!dontChangePlayer || isUsingScrollNow){
            for (View view : viewsToRemove) {
                //            tokenGridLayout.removeView(view);
                view.setVisibility(View.INVISIBLE);
            }

            // Remove the selected tokens from the list
            selectedToken.removeAll(tokensToRemove);

            tokenController.resetSelectedToken(viewsToRemove);
            tokenController.refreshTokenEvent();

            if (!isUsingScrollNow) {
                refreshAndChangeThePlayer();
            }
            else {
                isUsingScrollNow = false;
                dontChangePlayer = false;
                tokenController.refreshValidToken();
            }
        }
        // Remove the collected views after the loop

    }

    private void takeAvailablePrivilege(UserController currentPlayerController) {
        UserController opponentController;
        if (currentPlayerController.equals(user1Controller)){
            opponentController = user2Controller;
        }
        else{
            opponentController = user1Controller;
        }
        privilegeOnBoard -= 1;
        opponentController.increaseScroll();
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
                setPrivilegeTaskBar();
                break;

            case CARD:
                taskBarPurchaseCard.setVisibility(View.VISIBLE);
                taskBarTakeToken.setVisibility(View.GONE);
                TextView textCard = taskBarPurchaseCard.findViewById(R.id.text_task1);
                Button textCardButon = taskBarPurchaseCard.findViewById(R.id.task_button);
                textCard.setText("You must select a card");
                textCardButon.setText("Take Selected Card");
                setPrivilegeTaskBar();
                //setelah dipilih jangan lupa set None biar hilang textnya
                break;

            case NONE:
                taskBarTakeToken.setVisibility(View.VISIBLE);
                TextView textView = taskBarTakeToken.findViewById(R.id.text_task1);
                textView.setText("You must select a Token");
                Button button = taskBarTakeToken.findViewById(R.id.task_button);
                button.setText("Take Selected Token");
                taskBarUsePrivilege.setVisibility(View.GONE);
                taskBarPurchaseCard.setVisibility(View.GONE);
                if (tokenBag.isEmpty()){
                    taskBarReplenishBoard.setVisibility(View.GONE);
                } else {
                    taskBarReplenishBoard.setVisibility(View.VISIBLE);
                }
                setPrivilegeTaskBar();
                break;
        }
    }

    public void setPrivilegeTaskBar(){
        if (getCurrentPlayerController().getUser().getScroll() > 0 && !isUsingScrollNow){
            taskBarUsePrivilege.setVisibility(View.VISIBLE);
        }
        else {
            taskBarUsePrivilege.setVisibility(View.GONE);
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
        if (tokenBag.isEmpty()){
            taskBarReplenishBoard.setVisibility(View.INVISIBLE);
            return;
        }
        taskBarReplenishBoard.setVisibility(View.VISIBLE);
    }

    public void victoryCondition() {
        UserController currentPlayer = getCurrentPlayerController();
        if (currentPlayer.getUser().getCardsPoint() == 20) {
            showCustomDialog("You win with 20 points!", "OK", this::OpenStartUpActivity);
        } else if (currentPlayer.getUser().getCrowns() == 10) {
            showCustomDialog("You win with 10 crowns!", "OK", this::OpenStartUpActivity);
        } else if (currentPlayer.getUser().getMostSameCardColorValue() == 10) {
            showCustomDialog("You win with 10 same card colors!", "OK", this::OpenStartUpActivity);
        }
    }

    private void showCustomDialog(String message, String buttonText, Runnable onButtonClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout, null);
        builder.setView(dialogView);

        TextView messageTextView = dialogView.findViewById(R.id.dialog_message);
        Button button = dialogView.findViewById(R.id.dialog_ok_button);

        messageTextView.setText(message);
        button.setText(buttonText);

        AlertDialog dialog = builder.create();
        button.setOnClickListener(v -> {
            if (onButtonClick != null) {
                onButtonClick.run();
            }
            dialog.dismiss();
        });

        dialog.show();
    }

    private void OpenStartUpActivity() {
        Intent intent = new Intent(this, StartUpActivity.class);
        startActivity(intent);
    }

}