/*
 * 
 * You can use the following import statemets
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 * 
 */
package com.example.player.controller;
import com.example.player.service.PlayerH2Service;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
public class PlayerController{
    @Autowired
    public PlayerH2Service playerh2Service;
    @GetMapping("/players")
    public ArrayList<Player> getPlayers(){
        return playerh2Service.getPlayers();
    }
    @GetMapping("/players/{playerId}")
    public Player getPlayerId(@PathVariable("playerId") int playerId){
        return playerh2Service.getPlayerId(playerId);
    }
    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player teamPlayer){
        return playerh2Service.addPlayer(teamPlayer);
    }

    @PutMapping("/players/{playerId}")
    public Player updatePlayer(@PathVariable("playerId")int playerId,@RequestBody Player teamPlayer) {
        return playerh2Service.updatePlayer(playerId, teamPlayer);
    }
    
    @DeleteMapping("/players/{playerId}")
    public void deletePlayer(@PathVariable("playerId") int playerId){
        playerh2Service.deletePlayer(playerId);
    }
}