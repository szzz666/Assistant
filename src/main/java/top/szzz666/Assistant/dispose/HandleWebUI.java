package top.szzz666.Assistant.dispose;

import cn.nukkit.Player;
import top.szzz666.Assistant.entity.WebPost;

import static top.szzz666.Assistant.AssistantMain.nkServer;
import static top.szzz666.Assistant.config.AssistantConfig.AssistantrConfig;
import static top.szzz666.Assistant.config.AssistantConfig.DisableBanip;
import static top.szzz666.Assistant.dispose.DealWithPlayers.*;
import static top.szzz666.Assistant.dispose.DealWithPlayers.banPlayerIP;

public class HandleWebUI {
    public static boolean handleWebUI(WebPost wp) {
        if (AssistantrConfig.get(wp.getUsername()).equals(wp.getPassword())) {
            Player playersBeingDealtWith = getPlayerByName(wp.getPlayerName());
            String AssistantPlayerName = wp.getUsername();
            String substance = wp.getParameter();
            if (playersBeingDealtWith != null ) {
                switch (wp.getProcessing()) {
                    case "1":
                        killPlayer(playersBeingDealtWith, substance);
                        webTipsMessage(AssistantPlayerName, playersBeingDealtWith, "杀死");
                        return true;
                    case "2":
                        kickPlayer(playersBeingDealtWith, substance);
                        webTipsMessage(AssistantPlayerName, playersBeingDealtWith, "踢出");
                        return true;
                    case "3":
                        banPlayer(playersBeingDealtWith, substance);
                        webTipsMessage(AssistantPlayerName, playersBeingDealtWith, "封禁");
                        return true;
                    case "4":
                        if (DisableBanip) {
                            return false;
                        }else {
                            banPlayerIP(playersBeingDealtWith, substance);
                            webTipsMessage(AssistantPlayerName, playersBeingDealtWith, "封禁IP");
                            return true;
                        }
                    default:
                        warnPlayer(playersBeingDealtWith, substance);
                        webTipsMessage(AssistantPlayerName, playersBeingDealtWith, "警告");
                        return true;
                }
            }else if (wp.getProcessing().equals("unban")){
                unBanPlayer(wp.getPlayerName());
                return true;
            }else if (wp.getProcessing().equals("ban")){
                banlxPlayer(wp.getPlayerName(), substance);
                return true;
            }
        }
        return false;
    }

    public static Player getPlayerByName(String playerName) {
        // 使用getPlayer方法获取玩家对象
        Player player = nkServer.getPlayer(playerName);

        // 如果玩家在线，返回玩家对象；否则返回null
        if (player != null) {
            return player;
        } else {
            return null;
        }
    }
}
