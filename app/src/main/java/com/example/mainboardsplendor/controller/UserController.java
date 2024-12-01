package com.example.mainboardsplendor.controller;

import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.mainboardsplendor.model.Card;
import com.example.mainboardsplendor.model.Token;
import com.example.mainboardsplendor.view.MainActivity;
import com.example.mainboardsplendor.R;
import com.example.mainboardsplendor.enumeration.TokenColor;
import com.example.mainboardsplendor.databinding.LayoutPlayerBagBinding;
import com.example.mainboardsplendor.databinding.LayoutScorePlayerBoardBinding;
import com.example.mainboardsplendor.model.User;

import java.util.HashMap;
import java.util.List;

public class UserController {

    private User user;
    private LayoutScorePlayerBoardBinding scoreBoardPlayer;
    private LayoutPlayerBagBinding tokenBagPlayer;
    private MainActivity mainActivity;
    private TextView playerName;

    public UserController(User user, LayoutScorePlayerBoardBinding scoreBoardPlayer, LayoutPlayerBagBinding layoutPlayerBag, MainActivity mainActivity) {
        this.user = user;
        this.scoreBoardPlayer = scoreBoardPlayer;
        this.tokenBagPlayer = layoutPlayerBag;
        this.mainActivity = mainActivity;
        this.playerName = scoreBoardPlayer.playerName.findViewById(R.id.player_name);
    }

    public void setPlayerBoard() {
        tokenBagPlayer.playerNameBot.setText(user.getUsername());

        scoreBoardPlayer.playerName.setText(user.getUsername());

        scoreBoardPlayer.poinPlayer.setText(String.valueOf(user.getCardsPoint()));
        scoreBoardPlayer.crownPlayer.setText(String.valueOf(user.getCrowns()));
        scoreBoardPlayer.cardPoinPlayer.setText(String.valueOf(user.getMostSameCardColorValue()));

        scoreBoardPlayer.totalPrivilegePlayer.setText(String.valueOf(user.getScroll()));
        scoreBoardPlayer.totalReservedCardPlayer.setText(String.valueOf(user.getReserveCard()));
        scoreBoardPlayer.totalTokenPlayer.setText(String.valueOf(user.getTokensStack()));

        try{
            if(user.getCurrent()) {
                playerName.setTextColor(TokenColor.FIRST_PLAYER.getTokenColorInt(mainActivity));
                playerName.setTypeface(null, Typeface.BOLD);
            }
            else{
                playerName.setTextColor(TokenColor.BLACK.getTokenColorInt(mainActivity));
                playerName.setTypeface(null, Typeface.NORMAL);
            }
        }
        catch (NullPointerException e){
            Log.d("ErrorNullPointerException", "setPlayerBoard: "+ e.getMessage());
        }
    }

    public void setPlayerBold() {
        playerName.setTypeface(null, Typeface.BOLD);
    }

    public User getUser() {
        return user;
    }

    public HashMap<TokenColor, Integer> getOwnedToken(){
        return user.getOwnedTokens();
    }

    public void setOwnedToken(TokenColor tokenColor){
        user.addToken2Bag(tokenColor);
    }

    public void setPointPerCardColor(TokenColor tokenColor){
        user.addPoint2CardColor(tokenColor);
    }

    public HashMap<TokenColor, Integer> getOwnedDiscount(){
        return user.getOwnedDiscount();
    }

    public void setTokenBagPlayer(TokenColor tokenColor, GridLayout tokenGridLayout, int tokenImage){
        Toast.makeText(mainActivity, "TokenColor "+ tokenColor + " : " + getOwnedToken().get(tokenColor), Toast.LENGTH_SHORT).show();

        View view = LayoutInflater.from(mainActivity).inflate(R.layout.custom_token, tokenGridLayout, false);
        ImageView tokenView = view.findViewById(R.id.token_view);
        CardView cardView = view.findViewById(R.id.cardView_token);

        // Set Image token
        tokenView.setImageResource(tokenImage);
        // Set bg CardView token
        int colorCurrentToken = tokenColor.getTokenColorInt(mainActivity);
        cardView.setCardBackgroundColor(colorCurrentToken);
        view.setVisibility(View.VISIBLE);
        tokenGridLayout.addView(view);

    }

    public void setOwnedCard(Card selectedCard) {
    }





}
