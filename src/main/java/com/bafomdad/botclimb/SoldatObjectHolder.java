package com.bafomdad.botclimb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bafomdad on 4/30/2017.
 */
public final class SoldatObjectHolder {

    Map<Integer, String> playerList = new HashMap<Integer, String>();
    static int slots;
    String currentMap = "ctf_Ash";

    public static SoldatObjectHolder INSTANCE;

    public static void init() {

        INSTANCE = new SoldatObjectHolder();
    }

    public void setSize(int size) {

        this.slots = size;
        for (int i = 1; i <= size; i++) {
            playerList.put(i, null);
        }
    }

    public List<String> getPlayers() {

        List<String> players = new ArrayList<String>();
        for (int i = 1; i <= playerList.size(); i++) {
            if (playerList.get(i) != null)
                players.add(i + "=" + playerList.get(i));
        }
        return players;
    }

    public void addPlayer(String playername) {

        if (playername.isEmpty() || playerList.isEmpty())
            return;

        if (playerList.containsValue(playername))
            return;

        for (int i = 1; i <= playerList.size(); i++) {
            if (playerList.get(i) == null) {
                playerList.put(i, playername);
                return;
            }
        }
    }

    public void removePlayer(String playername) {

        if (playername.isEmpty() || playerList.isEmpty())
            return;

        for (int i = 1; i <= playerList.size(); i++) {
            if (playerList.get(i) != null && playerList.get(i).equals(playername))
                playerList.put(i, null);
        }
    }

    public String getCurrentMap() {

        return currentMap;
    }

    public void setCurrentMap(String mapname) {

        currentMap = mapname;
    }
}
