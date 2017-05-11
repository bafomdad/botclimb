package com.bafomdad.botclimb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by bafomdad on 4/28/2017.
 */
public class Config {

    private File file;
    public String token;
    public String address;
    public String port;
    public String password;
    public String channel;

    public Config(File file) throws IOException {

        this.file = file;
        load();
    }

    public void load() throws IOException {

        if (!file.exists()) {
            file.createNewFile();
            writeNewConfig();
        }
        readIt();
    }

    private void readIt() {

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("config.ini"));
            token = prop.getProperty("token");
            address = prop.getProperty("ip");
            port = prop.getProperty("port");
            password = prop.getProperty("password");
            channel = prop.getProperty("channel");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void writeNewConfig() {

        try {
            Properties prop = new Properties();
            prop.setProperty("token", "bottokenhere");
            prop.setProperty("ip", "localhost");
            prop.setProperty("port", "9001");
            prop.setProperty("password", "serverpassword");
            prop.setProperty("channel", "channelname");
            prop.store(new FileOutputStream(file), "config ini for discord bot. change the details below as you see fit");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
