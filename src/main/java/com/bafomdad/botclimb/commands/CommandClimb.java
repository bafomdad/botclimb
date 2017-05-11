package com.bafomdad.botclimb.commands;

import com.bafomdad.botclimb.BotClimb;
import com.bafomdad.botclimb.api.ICommand;
import com.bafomdad.botclimb.util.MessageFormatter;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by bafomdad on 5/2/2017.
 */
public class CommandClimb implements ICommand {
    @Override
    public String getName() {

        return "climb";
    }

    @Override
    public Permissions getMinimumPermission() {

        return Permissions.SEND_MESSAGES;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        MessageFormatter.getInstance().send(BotClimb.INSTANCE, channel, "Soldat Server IP: " + BotClimb.config.address + " / Port: " + BotClimb.config.port);
    }
}
