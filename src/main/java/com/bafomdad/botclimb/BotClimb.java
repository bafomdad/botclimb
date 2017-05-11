package com.bafomdad.botclimb;

import com.bafomdad.botclimb.api.IChatBot;
import com.bafomdad.botclimb.commands.CommandManager;
import com.bafomdad.botclimb.events.EventListener;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.DiscordException;

import java.io.File;
import java.net.Socket;

/**
 * Created by bafomdad on 4/27/2017.
 */
public class BotClimb implements IChatBot {

    public static BotClimb INSTANCE;
    private IDiscordClient client;
    public static Config config;
    private CommandManager commandmanager;
    public static Socket socket;
    public static boolean debug = false;

    public static void main(String[] args) {

        if (args.length > 0) {
            if (args[0].equals("debug"))
                debug = true;
        }
        try {
            config = new Config(new File("config.ini"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        INSTANCE = login();
    }

    public BotClimb(IDiscordClient client) {

        this.client = client;
        this.commandmanager = new CommandManager(this);
        SoldatObjectHolder.init();
    }

    public static BotClimb login() {

        BotClimb bot = null;
        ClientBuilder cb = new ClientBuilder();
        cb.withToken(config.token);
        try {
            IDiscordClient client = cb.login();
            EventDispatcher dispatcher = client.getDispatcher();
            dispatcher.registerListener(new EventListener());
            bot = new BotClimb(client);
        } catch (DiscordException e) {
            System.err.println("Error occurred while logging in!");
            e.printStackTrace();
        }
        return bot;
    }

    @Override
    public IDiscordClient getClient() {

        return client;
    }

    public CommandManager getCommandManager() {

        return commandmanager;
    }
}
