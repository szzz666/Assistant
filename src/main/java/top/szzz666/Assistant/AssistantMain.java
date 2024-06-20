package top.szzz666.Assistant;

import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import top.szzz666.Assistant.command.OpenFormCmd;
import top.szzz666.Assistant.web.MyHttpServer;

import static top.szzz666.Assistant.config.AssistantConfig.WebUiPort;
import static top.szzz666.Assistant.config.AssistantConfig.loadConfig;
import static top.szzz666.Assistant.config.LangConfig.loadLangConfig;


public class AssistantMain extends PluginBase {
    public static Server nkServer;
    public static CommandSender consoleObjects;
    public static String AssistantConfigPath;
    public static Plugin plugin;


    @Override
    public void onLoad(){
        //插件读取
        this.getLogger().info(TextFormat.colorize('&',"&b    _          _    _            _   "));
        this.getLogger().info(TextFormat.colorize('&',"&b   /_\\   _____(_)__| |_ __ _ _ _| |_ "));
        this.getLogger().info(TextFormat.colorize('&',"&b  / _ \\ (_-<_-< (_-<  _/ _` | ' \\  _|"));
        this.getLogger().info(TextFormat.colorize('&',"&b /_/ \\_\\/__/__/_/__/\\__\\__,_|_||_\\__|"));
        this.getLogger().info(TextFormat.colorize('&',"&b Assistant插件读取..."));
    }

    @Override
    public void onEnable(){
        nkServer = getServer();
        plugin = this;
        consoleObjects = getServer().getConsoleSender();
        AssistantConfigPath = getDataFolder().getPath();
        this.saveResource("config.yml");
        this.saveResource("webui.html");
        loadConfig();
        loadLangConfig();
        if (WebUiPort > 0){
            new Thread(MyHttpServer::httpServer).start();
        }
        this.getLogger().info(TextFormat.colorize('&',"&b Assistant插件开启"));
        this.getLogger().info(TextFormat.colorize('&',"&b Assistant插件 如果有bug加Q群反馈：894279534"));
        // 注册命令
        this.getServer().getCommandMap().register("Assistant", new OpenFormCmd());
    }

    @Override
    public void onDisable(){
        //插件关闭
        this.getLogger().info(TextFormat.colorize('&',"&c Assistant插件关闭"));
    }

}
