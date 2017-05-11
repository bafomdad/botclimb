package com.bafomdad.botclimb.test;

import com.bafomdad.botclimb.BotClimb;
import com.bafomdad.botclimb.api.ICommand;
import com.bafomdad.botclimb.commands.CommandManager;
import com.bafomdad.botclimb.util.MessageFormatter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bafomdad on 5/1/2017.
 */
public class TestMain {

    static String IPADDRESS = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    static Pattern pattern = Pattern.compile(IPADDRESS);

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Not enough args!");
            return;
        }
        System.out.println("You got this, Travis");
    }
}
