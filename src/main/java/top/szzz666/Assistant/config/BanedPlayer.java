package top.szzz666.Assistant.config;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import top.szzz666.Assistant.dispose.DealWithPlayers;
import top.szzz666.Assistant.tools.TimeUtils;

import java.util.HashMap;

import static top.szzz666.Assistant.AssistantMain.*;
import static top.szzz666.Assistant.tools.TimeUtils.intTimeToFormattedTime;

public class BanedPlayer {
    public static int interval = 60;
    public static HashMap<String, String> banedPlayerList = new HashMap<>();

    public static void loadBanedPlayerConfig() {
        plugin.saveResource("banedPlayer.yml");
        Config banedPlayer = new Config(AssistantConfigPath + "/banedPlayer.yml", Config.YAML);
        banedPlayerList = (HashMap<String, String>) banedPlayer.get("banedPlayerList");
        if (banedPlayerList == null) {
            banedPlayerList = new HashMap<>();
        }
        interval = banedPlayer.getInt("interval");
        saveBanedPlayerConfig();
        nkServer.getScheduler().scheduleRepeatingTask(plugin, () -> {
            if (banedPlayerList == null || banedPlayerList.isEmpty()) {
                return;
            }
            for (String playerName : banedPlayerList.keySet()) {
                long banTime = Long.parseLong(banedPlayerList.get(playerName));
                if (banTime < TimeUtils.nowTime()) {
                    DealWithPlayers.unBanPlayer(playerName);
                }
            }
        }, interval * 20, true);
    }

    public static void saveBanedPlayerConfig() {
        Config banedPlayer = new Config(AssistantConfigPath + "/banedPlayer.yml", Config.YAML);
        banedPlayer.set("banedPlayerList", banedPlayerList);
        banedPlayer.set("interval", interval);
        banedPlayer.save();
    }


    public static String ban(String playerName, long time) {
        banedPlayerList.put(playerName, String.valueOf(time));
        saveBanedPlayerConfig();
        return "解封时间： " + intTimeToFormattedTime(time);
    }

    public static void unban(String playerName) {
        banedPlayerList.remove(playerName);
        saveBanedPlayerConfig();
    }

    public static boolean isBaned(Player player) {
        return banedPlayerList.containsKey(player.getName());
    }

    public static long getBanTime(Player player) {
        return Long.parseLong(banedPlayerList.get(player.getName()));
    }
}
