package org.springframework.samples.petclinic.game;

import java.util.HashMap;
import java.util.Map;

public class GameStorage {
    
    private static Map<Integer,Game> games;
    private static GameStorage instance;
    
    private GameStorage(){
        games = new HashMap<>();
    }

    public static synchronized GameStorage getInstance(){
        if(instance==null){
            instance = new GameStorage();
        }
        return instance;
    }

    public Map<Integer,Game> getGames() {
        return games;
    }

    public void setGame(Game game) {
        games.put(game.getId(), game);
    }

}