package com.example.mainboardsplendor.controller;

import com.example.mainboardsplendor.databinding.LayoutPlayerBagBinding;
import com.example.mainboardsplendor.databinding.LayoutScorePlayerBoardBinding;
import com.example.mainboardsplendor.databinding.LayoutTokenBagPlayerBinding;
import com.example.mainboardsplendor.databinding.LayoutTokenBoardBinding;
import com.example.mainboardsplendor.model.Token;
import com.example.mainboardsplendor.model.User;

import java.util.HashMap;

public class UserController {

    private User user;
    private LayoutScorePlayerBoardBinding scoreBoardPlayer;
    private LayoutPlayerBagBinding tokenBagPlayer;

    public UserController(User user, LayoutScorePlayerBoardBinding scoreBoardPlayer, LayoutPlayerBagBinding layoutPlayerBag) {
        this.user = user;
        this.scoreBoardPlayer = scoreBoardPlayer;
        this.tokenBagPlayer = layoutPlayerBag;
    }

    public void setPlayerBoard() {
        scoreBoardPlayer.playerName.setText(user.getUsername());
        tokenBagPlayer.playerNameBot.setText(user.getUsername());
    }

    public HashMap<Token, Integer> getOwnedToken(){
        return user.getOwnedTokens();
    }
}
