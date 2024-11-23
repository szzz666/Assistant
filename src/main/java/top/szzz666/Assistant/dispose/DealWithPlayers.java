package top.szzz666.Assistant.dispose;


import cn.nukkit.Player;

import static top.szzz666.Assistant.AssistantMain.consoleObjects;
import static top.szzz666.Assistant.AssistantMain.nkServer;
import static top.szzz666.Assistant.config.LangConfig.*;

public class DealWithPlayers {
    // 罚站

    public static void warnPlayer(Player player, String substance) {
        player.sendTitle(warnPlayer_title, substance, 20, 60, 20);
        player.sendMessage(warnPlayer_sendMessage + substance);
    }

    public static void banPlayer(Player player, String substance) {
        substance = "\""+substance+"\"";
        nkServer.dispatchCommand(consoleObjects, "ban " + "\"" + player.getName() + "\" " + substance);
    }

    public static void unBanPlayer(String playerName) {
        nkServer.dispatchCommand(consoleObjects, "unban " + "\"" + playerName + "\" ");
    }

    public static void banPlayerIP(Player player, String substance) {
        substance = "\""+substance+"\"";
        nkServer.dispatchCommand(consoleObjects, "banip " + "\"" + player.getName() + "\" " + substance);
    }

    public static void banlxPlayer(String playerName, String substance) {
        substance = "\""+substance+"\"";
        nkServer.dispatchCommand(consoleObjects, "ban " + "\"" + playerName + "\" " + substance);
    }

    public static void kickPlayer(Player player, String reason) {
        player.kick(reason);
    }

    public static void killPlayer(Player player, String substance) {
        substance = "\""+substance+"\"";
        nkServer.dispatchCommand(consoleObjects, "kill " + "\"" + player.getName() + "\"");
        player.sendMessage(substance);
    }

    public static void tipsMessage(Player AssistantPlayer, Player player, String processing) {
        nkServer.broadcastMessage(tipsMessage + AssistantPlayer.getName() + " " + processing + tipsMessage2 + player.getName());
    }

    public static void webTipsMessage(String AssistantPlayerName, Player player, String processing) {
        nkServer.broadcastMessage(tipsMessage + AssistantPlayerName + " " + processing + tipsMessage2 + player.getName());
    }
}
