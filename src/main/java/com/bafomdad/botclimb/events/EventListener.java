package com.bafomdad.botclimb.events;

import com.bafomdad.botclimb.BotClimb;
import com.bafomdad.botclimb.api.ICommand;
import com.bafomdad.botclimb.commands.CommandManager;
import com.bafomdad.botclimb.util.MessageFormatter;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageBuilder;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;

/**
 * Created by bafomdad on 4/27/2017.
 */
public class EventListener {

    public static OutputStream getStream() throws IOException {

        return BotClimb.socket.getOutputStream();
    }

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {

        for (IChannel channel : event.getClient().getChannels()) {
            if (channel.getName().equals(BotClimb.INSTANCE.config.channel))
                new MessageBuilder(event.getClient()).withChannel(channel).withContent("BotClimb online!").build();
        }
        doSocketConnect();
    }

    @EventSubscriber
    public void onMessage(MessageReceivedEvent event) {

        if (!event.getChannel().getName().equals(BotClimb.config.channel))
            return;

        IMessage message = event.getMessage();
        if (message.getContent().startsWith("!")) {
            CommandManager commands = BotClimb.INSTANCE.getCommandManager();
            String[] splitter = message.getContent().split(" ");
            if (commands.exists(splitter[0].substring(1))) {
                ICommand command = commands.get(splitter[0].substring(1));
                if (!commands.checkPermission(command, event.getAuthor(), event.getGuild()))
                    return;

                command.execute(splitter, event.getAuthor(), event.getChannel());
                return;
            }
        }
        if (BotClimb.socket == null) {
            System.out.println("An error has occurred, this program will now exit.");
            System.exit(0);
        }
        try {
            getStream().write(MessageFormatter.getInstance().formatDiscordToSoldat(message).getBytes("US-ASCII"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doSocketConnect() {

        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                try {
                    Socket s = new Socket(BotClimb.config.address, Integer.parseInt(BotClimb.config.port));
                    if (s.isConnected()) {
                        String str = BotClimb.config.password + "\n";
                        s.getOutputStream().write(str.getBytes("US-ASCII"));
                    }
                    BotClimb.socket = s;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
                    Iterator it = reader.lines().iterator();
                    while (it.hasNext()) {
                        MessageFormatter.getInstance().formatSoldatToDiscord(it.next().toString());
                    }
                    s.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
