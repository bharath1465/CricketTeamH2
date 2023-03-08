/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.player.service;

import com.example.player.model.Player;
import com.example.player.repository.PlayerRepository;
import java.util.*;

 import org.springframework.web.server.ResponseStatusException;
 import org.springframework.http.HttpStatus;

 import org.springframework.beans.factory.annotation.Autowired;
 
 import org.springframework.jdbc.core.JdbcTemplate;
 import org.springframework.stereotype.Service;

 import java.util.ArrayList;
 
 import com.example.player.model.PlayerRowMapper;

@Service
 public class PlayerH2Service implements PlayerRepository {
  int id=12;
    @Autowired
    private JdbcTemplate db;
    
    @Override
    public ArrayList<Player> getPlayers(){
      List<Player> playersList = db.query("select * from TEAM", new PlayerRowMapper());
      ArrayList<Player> players = new ArrayList<>(playersList);
      return players;
    }
    
   @Override
   public Player getPlayerId(int playerId){
      try{
          Player teamPlayer = db.queryForObject("select * from TEAM where playerId = ?", new PlayerRowMapper(),playerId);
          return teamPlayer;
      }catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
    @Override
    public Player addPlayer(Player teamPlayer){
      db.update("insert into TEAM(playerId,playerName,jerseyNumber,role) values (?,?,?,?)",teamPlayer.getPlayerId() ,teamPlayer.getPlayerName(),teamPlayer.getJerseyNumber(),teamPlayer.getRole());
      id+=1;
      Player updatedPlayer= db.queryForObject("select * from TEAM where playerId = ? ", new PlayerRowMapper(),teamPlayer.getPlayerId());
      return updatedPlayer;
    }
    @Override
    public Player updatePlayer(int playerId,Player teamPlayer){
      if(teamPlayer.getPlayerName() != null){
        db.update("update TEAM set playerName = ? where playerId = ? ", teamPlayer.getPlayerName(),playerId);
      }
      if(teamPlayer.getJerseyNumber() != 0){
        db.update("update TEAM set jerseyNumber = ? where playerId = ? ", teamPlayer.getJerseyNumber(),playerId);
      }
      if(teamPlayer.getRole() != null){
        db.update("update TEAM set role = ? where playerId = ? ", teamPlayer.getRole(),playerId);
      }
      return getPlayerId(playerId);
    }
  @Override
    public void deletePlayer(int playerId){
      try{
        Player teamPlayer = db.queryForObject("select * from TEAM where playerId = ?", new PlayerRowMapper(),playerId);
        db.update("DELETE FROM TEAM WHERE playerId=?",playerId);
    }catch(Exception e){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Ok");
    }
 }
 }