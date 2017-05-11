package com.bafomdad.botclimb.commands;

import com.bafomdad.botclimb.api.IChatBot;
import com.bafomdad.botclimb.api.ICommand;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bafomdad on 4/30/2017.
 */
public class CommandManager {

    private IChatBot client;
    private Map<String, ICommand> commands = new HashMap<>();

    public CommandManager(IChatBot client) {

        this.client = client;
        register(new CommandMap());
        register(new CommandPlayers());
        register(new CommandShutdown());
        register(new CommandClimb());
        register(new CommandHelp());
        register(new CommandCollatz());
        register(new CommandKick());
        register(new CommandBan());
    }

    public void register(ICommand command) {

        commands.put(command.getName().toLowerCase(), command);
    }

    public boolean exists(String name) {

        return commands.containsKey(name.toLowerCase());
    }

    public ICommand get(String name) {

        return commands.get(name.toLowerCase());
    }

    public Collection<ICommand> getCommands() {

        return commands.values();
    }

    public boolean checkPermission(ICommand command, IUser user, IGuild guild) {

        EnumSet<Permissions> perms = user.getPermissionsForGuild(guild);
        if (perms == null || perms.isEmpty())
            return false;

        return perms.contains(command.getMinimumPermission());
    }
}
