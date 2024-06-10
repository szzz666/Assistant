package top.szzz666.Assistant.dispose;


import cn.nukkit.Player;

import static top.szzz666.Assistant.AssistantMain.consoleObjects;
import static top.szzz666.Assistant.AssistantMain.nkServer;

public class DealWithPlayers {

    public static void warnPlayer(Player player, String substance) {
        player.sendTitle("§c警告", substance, 20, 60, 20);
        player.sendMessage("§c收到警告: " + substance);
    }

    public static void banPlayer(Player player, String substance) {
        nkServer.dispatchCommand(consoleObjects, "ban " + "\"" + player.getName() + "\" " + substance);
    }

    public static void unBanPlayer(String playerName) {
        nkServer.dispatchCommand(consoleObjects, "unban " + "\"" + playerName + "\" ");
    }

    public static void banPlayerIP(Player player, String substance) {
        nkServer.dispatchCommand(consoleObjects, "banip " + "\"" + player.getName() + "\" " + substance);
    }
    public static void banlxPlayer(String playerName, String substance) {
        nkServer.dispatchCommand(consoleObjects, "ban " + "\"" + playerName + "\" " + substance);
    }

    public static void kickPlayer(Player player, String reason) {
        player.kick(reason);
    }

    public static void killPlayer(Player player, String substance) {
        nkServer.dispatchCommand(consoleObjects, "kill " + "\"" + player.getName() + "\"");
        player.sendMessage(substance);
    }

    public static void tipsMessage(Player AssistantPlayer, Player player, String processing) {
        nkServer.broadcastMessage("§c协管 " + AssistantPlayer.getName() + " " + processing + "了玩家 " + player.getName());
    }

    public static void webTipsMessage(String AssistantPlayerName, Player player, String processing) {
        nkServer.broadcastMessage("§c协管 " + AssistantPlayerName + " " + processing + "了玩家 " + player.getName());
    }
}
