package com.bafomdad.botclimb.commands;

import com.bafomdad.botclimb.BotClimb;
import com.bafomdad.botclimb.api.ICommand;
import com.bafomdad.botclimb.events.EventListener;
import com.bafomdad.botclimb.util.MessageFormatter;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 4/30/2017.
 */
public class CommandKick implements ICommand {

    @Override
    public String getName() {

        return "kick";
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
            String kick = "/kick " + args[1] + "\n";
            EventListener.getStream().write(kick.getBytes("US-ASCII"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
