package com.example.mainboardsplendor.controller;

import android.graphics.Typeface;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.mainboardsplendor.MainActivity;
import com.example.mainboardsplendor.R;
import com.example.mainboardsplendor.databinding.LayoutPlayerBagBinding;
import com.example.mainboardsplendor.databinding.LayoutScorePlayerBoardBinding;
import com.example.mainboardsplendor.databinding.LayoutTokenBagPlayerBinding;
import com.example.mainboardsplendor.databinding.LayoutTokenBoardBinding;
import com.example.mainboardsplendor.model.Token;
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

        scoreBoardPlayer.poinPlayer.setText( String.valueOf(user.getCardsPoint()) );
        scoreBoardPlayer.crownPlayer.setText(String.valueOf(user.getCrowns()));
        scoreBoardPlayer.cardPoinPlayer.setText(String.valueOf(user.getMostSameCardColorValue()));

        scoreBoardPlayer.totalPrivilegePlayer.setText(String.valueOf(user.getScroll()));
        scoreBoardPlayer.totalReservedCardPlayer.setText(String.valueOf(user.getReserveCard()));
        scoreBoardPlayer.totalTokenPlayer.setText(String.valueOf(user.getTokensStack()));

        if(playerName.getTypeface().getStyle() == Typeface.BOLD) {
            playerName.setTextColor(ContextCompat.getColor(mainActivity, R.color.black));
            playerName.setTypeface(null, Typeface.NORMAL);
        }
        else if(playerName.getTypeface().getStyle() == Typeface.NORMAL){
            playerName.setTextColor(ContextCompat.getColor(mainActivity, R.color.first_player));
            playerName.setTypeface(null, Typeface.BOLD);
        }
    }

    public void setPlayerBold() {
        playerName.setTypeface(null, Typeface.BOLD);
    }

    public User getUser() {
        return user;
    }


    public void setOwnedToken(List<Token> selectedToken, User user) {
        for (int i = 0; i < selectedToken.size(); i++) {
            if (user.getOwnedTokens().containsKey(selectedToken.get(i))) {
                user.getOwnedTokens().put(selectedToken.get(i), user.getOwnedTokens().get(selectedToken.get(i)) + 1);
            } else {
                user.getOwnedTokens().put(selectedToken.get(i), 1);
            }
        }
    }
}
