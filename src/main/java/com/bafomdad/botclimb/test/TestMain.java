package com.bafomdad.botclimb.test;

import com.bafomdad.botclimb.BotClimb;

import javax.json.*;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by bafomdad on 5/1/2017.
 */
public class TestMain {

    public static void main(String[] args) {

        String address = BotClimb.config.address;
        String port = BotClimb.config.port;

        try {
            URL url = new URL("http://api.soldat.pl/v0/server/" + address + "/" + port + "/players");

            InputStream is = url.openStream();
            JsonReader rdr = Json.createReader(is);

            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("Players");
            if (results.isEmpty()) {
                System.out.println("Empty!");
                return;
            }
            System.out.println(results.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
