package com.example.mainboardsplendor.controller;

import com.example.mainboardsplendor.databinding.LayoutPlayerBagBinding;
import com.example.mainboardsplendor.databinding.LayoutScorePlayerBoardBinding;
import com.example.mainboardsplendor.model.User;

public class UserController {

    private User user;
    private LayoutScorePlayerBoardBinding scoreBoardPlayer;
    private LayoutPlayerBagBinding tokenBagPlayer;

    public UserController(User user, LayoutScorePlayerBoardBinding scoreBoardPlayer, LayoutPlayerBagBinding layoutPlayerBag) {
        this.user = user;
        this.scoreBoardPlayer = scoreBoardPlayer;
        this.tokenBagPlayer = layoutPlayerBag;
    }

    public void setPlayerBoard(int i) {
        tokenBagPlayer.playerNameBot.setText(user.getUsername());

        scoreBoardPlayer.playerName.setText(user.getUsername());

        scoreBoardPlayer.poinPlayer.setText( String.valueOf(user.getCardsPoint()) );
        scoreBoardPlayer.crownPlayer.setText(String.valueOf(user.getCrowns()));
        scoreBoardPlayer.cardPoinPlayer.setText(String.valueOf(user.getMostSameCardColorValue()));

        scoreBoardPlayer.totalPrivilegePlayer.setText(String.valueOf(user.getScroll()));
        scoreBoardPlayer.totalReservedCardPlayer.setText(String.valueOf(user.getReserveCard()));
        scoreBoardPlayer.totalTokenPlayer.setText(String.valueOf(user.getTokensStack()));
    }

    public User getUser() {
        return user;
    }
}
