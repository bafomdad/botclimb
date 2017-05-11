package com.bafomdad.botclimb.commands;

import com.bafomdad.botclimb.BotClimb;
import com.bafomdad.botclimb.api.ICommand;
import com.bafomdad.botclimb.events.EventListener;
import com.bafomdad.botclimb.util.MessageFormatter;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 5/3/2017.
 */
public class CommandBan implements ICommand {

    @Override
    public String getName() {

        return "ban";
    }

    @Override
    public Permissions getMinimumPermission() {

        return Permissions.ADMINISTRATOR;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        if (args.length < 2) {
            MessageFormatter.getInstance().send(BotClimb.INSTANCE, channel, "Not enough arguments.");
            return;
        }
        try {
            if (args.length > 2) {
                String ban = "/ban " + args[1] + " " + args[2] + "\n";
                EventListener.getStream().write(ban.getBytes("US-ASCII"));
                return;
            }
            String ban = "/ban " + args[1] + "\n";
            EventListener.getStream().write(ban.getBytes("US-ASCII"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
