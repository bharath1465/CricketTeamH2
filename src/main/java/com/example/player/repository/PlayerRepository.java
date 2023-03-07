// Write your code here
package com.example.player.repository;

import java.util.*;
import com.example.player.model.Player;
public interface PlayerRepository {
    ArrayList<Player> getPlayers();
    

    Player getPlayerId(int playerId);

    Player addPlayer(Player teamPlayer);
    Player updatePlayer(int playerId,Player teamPlayer);

    void deletePlayer(int playerId);

}