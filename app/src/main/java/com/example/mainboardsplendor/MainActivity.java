package com.example.mainboardsplendor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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

import com.example.mainboardsplendor.controller.CardController;
import com.example.mainboardsplendor.controller.TokenController;
import com.example.mainboardsplendor.controller.UserController;
import com.example.mainboardsplendor.databinding.ActivityMainBinding;
import com.example.mainboardsplendor.databinding.LayoutScorePlayerBoardBinding;
import com.example.mainboardsplendor.model.Card;
import com.example.mainboardsplendor.model.RoyalCard;
import com.example.mainboardsplendor.model.Token;
import com.example.mainboardsplendor.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

/*
    private int quantityCardLevel3 = 10;
    private int quantityCardLevel2 = 20;
    private int quantityCardLevel1 = 25;
*/
    private List<Token> tokenBag = new ArrayList<>();
    private List<Card> listCardLevel3 = new ArrayList<>();
    private List<Card> listCardLevel2 = new ArrayList<>();
    private List<Card> listCardLevel1 = new ArrayList<>();
    private List<RoyalCard> listRoyalCard = new ArrayList<>();
    private List<Token> selectedToken = new ArrayList<>();
    private HashMap ownedTokensPlayer1;
    private HashMap ownedTokensPlayer2;

    private User user1;
    private User user2;

    private TokenController tokenController;
    private UserController user1Controller;
    private UserController user2Controller;
    private CardController cardController;

    private User currentPlayer;

    private GridLayout tokenGridLayout;
    private CardView taskBarTakeToken;

    private int numToken = 0;

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
        // Todo: yang dapat player 1 yang set current true
        user1.setCurrent(true);
        user2 = new User(intent.getStringExtra(CreateUserActivity.PLAYER_2));

        // Init tokenBag
        tokenGridLayout = binding.tokenBoard.splendorDuelBoard;
        taskBarTakeToken = binding.taskBar.taskBarTakeGems;
        Button takeButton = taskBarTakeToken.findViewById(R.id.take_button);

        ownedTokensPlayer1 =  user1Controller.getOwnedToken();
        ownedTokensPlayer2 =  user2Controller.getOwnedToken();

//        takeButton.setOnClickListener(v -> {
//            List<Token> selectedTokens = tokenController.getSelectedToken();
//            // Todo: tambah attribut currentPlayer untuk tahu siapa player aktifnya,
//            //  beri indikator seperti warna nama playernya berubah/ teks pemberitahuan player aktif pada taskbar
//            // check current player baru ambil token
//        });
        tokenController = new TokenController(tokenBag, tokenGridLayout, taskBarTakeToken,this, this);

        tokenController.initTokenBag();

        // Init TokenBoard in Spiral
        int rowCount =tokenGridLayout.getRowCount();
        int colCount = tokenGridLayout.getColumnCount();
        tokenController.InitTokenBoard(rowCount, colCount);

        selectedToken = tokenController.getSelectedToken();
        takeButton.setOnClickListener(v -> {
            takeButtonAction();
        });
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
            view.setVisibility(View.VISIBLE);
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
            view.setVisibility(View.VISIBLE);
            binding.layoutPlayer1Bag.listWhiteToken.listToken.addView(view);
        }

        // Init Grid list_green_token pada bag player
        for (int i = 0; i< 4; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listGreenToken.listToken, false);
            ImageView tokenView = view.findViewById(R.id.token_view);
            view.setVisibility(View.INVISIBLE);
            CardView cardView = view.findViewById(R.id.cardView_token);

            // Set Image token
            tokenView.setImageResource(R.drawable.green_token);
            // Set bg CardView token
            int color4greenToken = ContextCompat.getColor(this, R.color.color4greenToken);
            cardView.setCardBackgroundColor(color4greenToken);
            view.setVisibility(View.VISIBLE);
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
            view.setVisibility(View.VISIBLE);
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
            view.setVisibility(View.VISIBLE);
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
            view.setVisibility(View.VISIBLE);
            binding.layoutPlayer1Bag.listPearlToken.listToken.addView(view);
        }

        for (int i = 0; i< 2; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.custom_token, binding.layoutPlayer1Bag.listGoldToken.listToken, false);
            ImageView tokenView = view.findViewById(R.id.token_view);
            CardView cardView = view.findViewById(R.id.cardView_token);
//            ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
//            layoutParams.width = (int) (35 * getResources().getDisplayMetrics().density);
//            layoutParams.height = (int) (35 * getResources().getDisplayMetrics().density);
//            float cornerRadiusInPx = 17.5f * getResources().getDisplayMetrics().density;

            // Set Image token
            tokenView.setImageResource(R.drawable.gold_token);
            // Set bg CardView token
            int color4goldToken = ContextCompat.getColor(this, R.color.color4goldToken);
            cardView.setCardBackgroundColor(color4goldToken);
//            cardView.setLayoutParams(layoutParams);
//            cardView.setRadius(cornerRadiusInPx);
            view.setVisibility(View.VISIBLE);
            binding.layoutPlayer1Bag.listGoldToken.listToken.addView(view);
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

    }

    // Method for add Card Stack on bag player
    private void takeButtonAction() {
        // Create a list to hold the views to remove
        List<View> viewsToRemove = new ArrayList<>();
        List<Token> tokensToRemove = new ArrayList<>();

        // Collect views to remove and tokens to remove in separate lists
        for (Token token : selectedToken) {
            View view = tokenController.getViewAt(token.getLocation().get(0), token.getLocation().get(1), tokenGridLayout);
            if (view != null) {
                viewsToRemove.add(view); // Add the view to the list
            }
            tokensToRemove.add(token); // Add the token to the list for removal
        }

        // Remove the collected views after the loop
        for (View view : viewsToRemove) {
            tokenGridLayout.removeView(view);
        }

        // Remove the selected tokens from the list
        selectedToken.removeAll(tokensToRemove);

        tokenController.testToast(viewsToRemove);
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