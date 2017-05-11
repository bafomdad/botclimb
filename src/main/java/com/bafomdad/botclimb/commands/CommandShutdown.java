package com.bafomdad.botclimb.commands;

import com.bafomdad.botclimb.BotClimb;
import com.bafomdad.botclimb.api.ICommand;
import com.bafomdad.botclimb.util.MessageFormatter;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 4/30/2017.
 */
public class CommandShutdown implements ICommand {

    @Override
    public String getName() {

        return "shutdown";
    }

    @Override
    public Permissions getMinimumPermission() {

        return Permissions.ADMINISTRATOR;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        MessageFormatter.getInstance().send(BotClimb.INSTANCE, channel, "Shutting down the bot..");
        System.exit(0);
    }
}
