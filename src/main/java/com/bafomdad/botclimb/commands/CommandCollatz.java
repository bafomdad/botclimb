package com.bafomdad.botclimb.commands;

import com.bafomdad.botclimb.BotClimb;
import com.bafomdad.botclimb.api.ICommand;
import com.bafomdad.botclimb.util.MessageFormatter;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bafomdad on 5/2/2017.
 */
public class CommandCollatz implements ICommand {

    @Override
    public String getName() {

        return "collatz";
    }

    @Override
    public Permissions getMinimumPermission() {

        return Permissions.SEND_MESSAGES;
    }

    @Override
    public void execute(String[] args, IUser sender, IChannel channel) {

        if (args.length < 2) {
            MessageFormatter.getInstance().send(BotClimb.INSTANCE, channel, "Needs a number");
            return;
        }
        try {
            collatz(Integer.parseInt(args[1]), channel);
        } catch (NumberFormatException e) {
            MessageFormatter.getInstance().send(BotClimb.INSTANCE, channel, "Not a number!");
        }
    }

    private void collatz(int num, IChannel channel) throws NumberFormatException {

        int tocalc = num;
        List<Integer> arrayint = new ArrayList<Integer>();
        arrayint.add(tocalc);
        while (tocalc != 1) {
            if (tocalc % 2 == 0) {
                tocalc = tocalc / 2;
                arrayint.add(tocalc);
            }
            else {
                tocalc = (tocalc * 3) + 1;
                arrayint.add(tocalc);
            }
        }
        MessageFormatter.getInstance().send(BotClimb.INSTANCE, channel, "Calculated " + num + " with " + arrayint.size() + " iterations: " + arrayint.toString());
    }
}
