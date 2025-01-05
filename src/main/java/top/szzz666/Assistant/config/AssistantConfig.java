package top.szzz666.Assistant.config;

import cn.nukkit.utils.Config;

import java.util.HashMap;

import static top.szzz666.Assistant.AssistantMain.AssistantConfigPath;
import static top.szzz666.Assistant.AssistantMain.plugin;

public class AssistantConfig {
    public static HashMap<String, String> AssistantrConfig = new HashMap<>();
    public static HashMap<String, String> CommandConfig = new HashMap<>();

//    public static int WebUiPort = 8080;
    public static String Language = "zh_CN";
    public static boolean DisableBanip = true;
    public static boolean useStarrySkyAuth = false;

    public static void loadConfig() {
        plugin.saveResource("config.yml");
        Config config = new Config(AssistantConfigPath + "/config.yml", Config.YAML);
        AssistantrConfig = (HashMap<String, String>) config.get("Assistant");
        CommandConfig = (HashMap<String, String>) config.get("Command");
//        WebUiPort = config.getInt("WebUiPort", 8080);
        Language = config.getString("Language", "zh_CN");
        DisableBanip = config.getBoolean("DisableBanip", true);
        useStarrySkyAuth = config.getBoolean("useStarrySkyAuth", false);
        saveConfig();
    }
    public static void saveConfig() {
        Config config = new Config(AssistantConfigPath + "/config.yml", Config.YAML);
        config.set("Assistant", AssistantrConfig);
        config.set("Command", CommandConfig);
//        config.set("WebUiPort", WebUiPort);
        config.set("Language", Language);
        config.set("DisableBanip", DisableBanip);
        config.set("useStarrySkyAuth", useStarrySkyAuth);
        config.save();
    }


    public static boolean isAssistant(String playerName) {
        return AssistantrConfig.containsKey(playerName);
    }
    public static boolean addAssistant(String playerName) {
        Config config = new Config(AssistantConfigPath + "/config.yml", Config.YAML);
        boolean x = AssistantrConfig.containsKey(playerName);
        if (!x) {
            AssistantrConfig.put(playerName, null);
            config.set("Assistant", AssistantrConfig);
            AssistantrConfig = (HashMap<String, String>) config.get("Assistant");
            config.save();
        }
        return !x;
    }

    public static boolean delAssistant(String playerName) {
        Config config = new Config(AssistantConfigPath + "/config.yml", Config.YAML);
        String x = AssistantrConfig.remove(playerName);
        config.set("Assistant", AssistantrConfig);
        AssistantrConfig = (HashMap<String, String>) config.get("Assistant");
        config.save();
        return x == null;
    }

    public static boolean setAssistantPwd(String playerName, String pwd) {
        Config config = new Config(AssistantConfigPath + "/config.yml", Config.YAML);
        boolean x = AssistantrConfig.containsKey(playerName);
        if (x) {
            AssistantrConfig.put(playerName, pwd);
            config.set("Assistant", AssistantrConfig);
            AssistantrConfig = (HashMap<String, String>) config.get("Assistant");
            config.save();
        }
        return x;
    }
}
