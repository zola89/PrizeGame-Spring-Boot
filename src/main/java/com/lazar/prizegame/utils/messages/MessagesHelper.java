package com.lazar.prizegame.utils.messages;

public class MessagesHelper {
    public static String generate(String message, Object... args) {

        int index = 0;

        for (Object arg : args) {
            message = message.replace("{" + index++ + "}", arg.toString());
        }

        return message;
    }
}
