package com.bafomdad.botclimb;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;
import java.net.URL;
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

    public static SoldatObjectHolder INSTANCE;

    public static void init() {

        INSTANCE = new SoldatObjectHolder();
        INSTANCE.setSize();
    }

    public void setSize() {

        this.slots = Integer.parseInt(getMaxPlayers());
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

        return getJsonObject("CurrentMap", "ctf_Ash");
    }

    public String getJsonObject(String object, String fallback) {

        try {
            URL url = new URL("http://api.soldat.pl/v0/server/" + BotClimb.config.address + "/" + BotClimb.config.port);

            InputStream is = url.openStream();
            JsonReader rdr = Json.createReader(is);

            JsonObject obj = rdr.readObject();
            return obj.getString(object);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fallback;
    }

    private String getMaxPlayers() {

        return getJsonObject("MaxPlayers", "0");
    }
}
