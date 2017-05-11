package com.bafomdad.botclimb.commands;

import com.bafomdad.botclimb.BotClimb;
import com.bafomdad.botclimb.api.ICommand;
import com.bafomdad.botclimb.util.MessageFormatter;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bafomdad on 5/2/2017.
 */
public class CommandHelp implements ICommand {

    @Override
    public String getName() {

        return "help";
    }

    @Override
    public Permissions getMinimumPermission() {

        return Permissions.SEND_MESSAGES;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        Collection<ICommand> commands = BotClimb.INSTANCE.getCommandManager().getCommands();
        List<String> help = new ArrayList<String>();
        for (ICommand command : commands)
            help.add(command.getName());
        MessageFormatter.getInstance().send(BotClimb.INSTANCE, channel, help.toString());
    }
}
