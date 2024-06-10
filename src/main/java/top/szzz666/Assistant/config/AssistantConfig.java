package top.szzz666.Assistant.config;

import cn.nukkit.utils.Config;

import java.util.HashMap;

import static top.szzz666.Assistant.AssistantMain.AssistantConfigPath;

public class AssistantConfig {
    public static HashMap<String, String> AssistantrConfig = new HashMap<>();
    public static HashMap<String, String> CommandConfig = new HashMap<>();

    public static int WebUiPort;
    public static boolean xLink;

    public static void loadConfig() {
        Config config = new Config(AssistantConfigPath + "/config.yml", Config.YAML);
        AssistantrConfig = (HashMap<String, String>) config.get("Assistant");
        CommandConfig = (HashMap<String, String>) config.get("Command");
        WebUiPort = config.getInt("WebUiPort");
        xLink = config.getBoolean("xLink");
        config.save();
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
