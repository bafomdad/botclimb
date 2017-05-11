package com.bafomdad.botclimb.commands;

import com.bafomdad.botclimb.BotClimb;
import com.bafomdad.botclimb.SoldatObjectHolder;
import com.bafomdad.botclimb.api.ICommand;
import com.bafomdad.botclimb.util.MessageFormatter;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.MessageBuilder;

import java.util.List;

/**
 * Created by bafomdad on 4/30/2017.
 */
public class CommandPlayers implements ICommand {

    @Override
    public String getName() {

        return "players";
    }

    @Override
    public Permissions getMinimumPermission() {

        return Permissions.SEND_MESSAGES;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        List<String> playerList = SoldatObjectHolder.INSTANCE.getPlayers();
        if (playerList.isEmpty()) {
            MessageFormatter.getInstance().send(BotClimb.INSTANCE, channel, "There are no players online.");
            return;
        }
        MessageFormatter.getInstance().send(BotClimb.INSTANCE, channel, "Players online: " + playerList);
    }
}
