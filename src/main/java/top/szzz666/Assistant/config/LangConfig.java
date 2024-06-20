package top.szzz666.Assistant.config;

import cn.nukkit.utils.Config;

import java.util.ArrayList;

import static top.szzz666.Assistant.AssistantMain.AssistantConfigPath;
import static top.szzz666.Assistant.AssistantMain.plugin;
import static top.szzz666.Assistant.config.AssistantConfig.Language;


public class LangConfig {
    public static String OpFormCmd_description;
    public static String OpenFormCmd_addAssistant_sendMessage;
    public static String OpenFormCmd_addAssistant_sendMessage_error;
    public static String OpenFormCmd_delAssistant_sendMessage;
    public static String OpenFormCmd_delAssistant_sendMessage_error;
    public static String OpenFormCmd_not;
    public static String generalForm_title;
    public static String generalForm_content;
    public static String generalForm_ElementButton1;
    public static String generalForm_ElementButton2;
    public static String generalForm_ElementButton3;
    public static String generalForm_ElementButton4;
    public static String banForm_title;
    public static String banForm_ElementInput1;
    public static String banForm_ElementInput2;
    public static String SetWebUIpwdForm_title;
    public static String SetWebUIpwdForm_ElementInput1;
    public static String SetWebUIpwdForm_ElementInput2;
    public static String generalForm_sendMessage;
    public static String generalForm_sendMessage_error;
    public static String openAssistantForm_title;
    public static String openAssistantForm_ElementDropdown1;
    public static String openAssistantForm_ElementDropdown2;
    public static String openAssistantForm_ElementInput1;
    public static ArrayList<String> openAssistantForm_Arrays;
    public static String openAssistantForm_sendMessage;
    public static String UnbanForm_title;
    public static String UnbanForm_ElementInput;
    public static String warnPlayer_title;
    public static String warnPlayer_sendMessage;
    public static String tipsMessage;
    public static String tipsMessage2;


    public static boolean loadLangConfig() {
        plugin.saveResource("language/chs.yml");
        plugin.saveResource("language/eng.yml");
        Config LangConfig = new Config(AssistantConfigPath + "/language/" + Language, Config.YAML);
        OpFormCmd_description = LangConfig.getString("OpFormCmd_description");
        OpenFormCmd_addAssistant_sendMessage = LangConfig.getString("OpenFormCmd_addAssistant_sendMessage");
        OpenFormCmd_addAssistant_sendMessage_error = LangConfig.getString("OpenFormCmd_addAssistant_sendMessage_error");
        OpenFormCmd_delAssistant_sendMessage = LangConfig.getString("OpenFormCmd_delAssistant_sendMessage");
        OpenFormCmd_delAssistant_sendMessage_error = LangConfig.getString("OpenFormCmd_delAssistant_sendMessage_error");
        OpenFormCmd_not = LangConfig.getString("OpenFormCmd_not");
        generalForm_title = LangConfig.getString("generalForm_title");
        generalForm_content = LangConfig.getString("generalForm_content");
        generalForm_ElementButton1 = LangConfig.getString("generalForm_ElementButton1");
        generalForm_ElementButton2 = LangConfig.getString("generalForm_ElementButton2");
        generalForm_ElementButton3 = LangConfig.getString("generalForm_ElementButton3");
        generalForm_ElementButton4 = LangConfig.getString("generalForm_ElementButton4");
        banForm_title = LangConfig.getString("banForm_title");
        banForm_ElementInput1 = LangConfig.getString("banForm_ElementInput1");
        banForm_ElementInput2 = LangConfig.getString("banForm_ElementInput2");
        SetWebUIpwdForm_title = LangConfig.getString("SetWebUIpwdForm_title");
        SetWebUIpwdForm_ElementInput1 = LangConfig.getString("SetWebUIpwdForm_ElementInput1");
        SetWebUIpwdForm_ElementInput2 = LangConfig.getString("SetWebUIpwdForm_ElementInput2");
        generalForm_sendMessage = LangConfig.getString("generalForm_sendMessage");
        generalForm_sendMessage_error = LangConfig.getString("generalForm_sendMessage_error");
        openAssistantForm_title = LangConfig.getString("openAssistantForm_title");
        openAssistantForm_ElementDropdown1 = LangConfig.getString("openAssistantForm_ElementDropdown1");
        openAssistantForm_ElementDropdown2 = LangConfig.getString("openAssistantForm_ElementDropdown2");
        openAssistantForm_ElementInput1 = LangConfig.getString("openAssistantForm_ElementInput1");
        openAssistantForm_Arrays = (ArrayList<String>) LangConfig.get("openAssistantForm_Arrays");
        openAssistantForm_sendMessage = LangConfig.getString("openAssistantForm_sendMessage");
        UnbanForm_title = LangConfig.getString("UnbanForm_title");
        UnbanForm_ElementInput = LangConfig.getString("UnbanForm_ElementInput");
        warnPlayer_title = LangConfig.getString("warnPlayer_title");
        warnPlayer_sendMessage = LangConfig.getString("warnPlayer_sendMessage");
        tipsMessage = LangConfig.getString("tipsMessage");
        tipsMessage2 = LangConfig.getString("tipsMessage2");
        LangConfig.save();
        return true;
    }
}

