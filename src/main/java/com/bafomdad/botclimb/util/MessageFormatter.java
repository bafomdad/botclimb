package com.bafomdad.botclimb.util;

import com.bafomdad.botclimb.BotClimb;
import com.bafomdad.botclimb.SoldatObjectHolder;
import com.bafomdad.botclimb.api.IChatBot;
import com.bafomdad.botclimb.events.EventListener;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.RequestBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bafomdad on 4/29/2017.
 */
public class MessageFormatter {

    static MessageFormatter INSTANCE;

    String[] playerleave = new String[] { " has left ", " has been kicked", " has disconnected", " has been disconnected", " has been ping kicked", " has been flood kicked", " has been voted to leave", " was kicked from", " disconnected" };
    Pattern playerchat = Pattern.compile("(\\[.*?\\]\\s+)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Pattern ipAddress = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");

    public static MessageFormatter getInstance() {

        if (INSTANCE == null)
            INSTANCE = new MessageFormatter();

        return INSTANCE;
    }

    /*
        Heavy filtering done in this method, with certain responses to certain messages receieved.
     */
    public boolean canSend(String message) {

        if (BotClimb.debug) {
            System.out.println(message);
        }
        if (message.contains("(TEAM)")) {
            String[] splitter = message.split(Pattern.quote("(") + "TEAM" + Pattern.quote(")"));
            if (splitter[1].startsWith("["))
                return false;
        }
        for (int i = 0; i < playerleave.length; i++) {
            if (message.contains(playerleave[i])) {
                String[] str = message.split(playerleave[i]);
                SoldatObjectHolder.INSTANCE.removePlayer(str[0]);
                return true;
            }
        }
        if (message.contains(" has joined ")) {
            String[] str = message.split(" has joined ");
            SoldatObjectHolder.INSTANCE.addPlayer(str[0]);
            return true;
        }
        if (message.contains(" scores for ")) {
            return true;
        }
        Matcher m3 = playerchat.matcher(message);
        if (!m3.find())
            return false;

        return !message.startsWith("/");
    }

    public void send(IChatBot chat, IChannel channel, String message) {

        RequestBuffer.request(() ->
        new MessageBuilder(chat.getClient()).withChannel(channel).withContent(message).build());
    }

    public void formatSoldatToDiscord(String message) {

        if (matchesChannel() <= 0L)
            return;

        if (!canSend(message))
            return;

        long channelID = matchesChannel();
        Matcher match = ipAddress.matcher(message);
        if (match.find()) {
            String[] splitter = message.split(ipAddress.pattern());
            send(BotClimb.INSTANCE, BotClimb.INSTANCE.getClient().getChannelByID(channelID), splitter[0].substring(0, splitter[0].length() - 1));
            return;
        }
        send(BotClimb.INSTANCE, BotClimb.INSTANCE.getClient().getChannelByID(channelID), message);
    }

    public String formatDiscordToSoldat(IMessage message) {

        String name = message.getAuthor().getName();
        String content = message.getContent();

        return "/say " + "[" + name + "] " + content + "\n";
    }

    public long matchesChannel() {

        if (BotClimb.INSTANCE.getClient().getChannels().isEmpty())
            return 0L;

        for (IChannel channel : BotClimb.INSTANCE.getClient().getChannels()) {
            if (channel.getName().equals(BotClimb.config.channel)) {
                return channel.getLongID();
            }
        }
        return 0L;
    }
}
