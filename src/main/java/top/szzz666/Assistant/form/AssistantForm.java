package top.szzz666.Assistant.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.handler.FormResponseHandler;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;

import java.util.ArrayList;

import static top.szzz666.Assistant.AssistantMain.nkServer;
import static top.szzz666.Assistant.config.AssistantConfig.*;
import static top.szzz666.Assistant.config.LangConfig.*;
import static top.szzz666.Assistant.dispose.DealWithPlayers.tipsMessage;
import static top.szzz666.Assistant.dispose.DealWithPlayers.*;

public class AssistantForm {
    public static void generalForm(Player AssistantPlayer) {
        FormWindowSimple form = new FormWindowSimple(generalForm_title, generalForm_content);
        // 添加按钮
        form.addButton(new ElementButton(generalForm_ElementButton1));
        form.addButton(new ElementButton(generalForm_ElementButton2));
        form.addButton(new ElementButton(generalForm_ElementButton3));
        if (WebUiPort > 0) {
            form.addButton(new ElementButton(generalForm_ElementButton4));
        }
        if (AssistantPlayer.isOp()) {
            form.addButton(new ElementButton(generalForm_ElementButton5));
        }
        // 设置按钮点击处理
        form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
            if (form.wasClosed()) return;
            int buttonIndex = form.getResponse().getClickedButtonId(); // 获取点击的按钮索引
            switch (buttonIndex) {
                case 1:
                    UnbanForm(AssistantPlayer);
                    break;
                case 2:
                    banForm(AssistantPlayer);
                    break;
                case 3:
                    SetWebUIpwdForm(AssistantPlayer);
                    break;
                case 4:
                    loadConfig();
                    loadLangConfig();
                    AssistantPlayer.sendMessage(generalForm_sendMessage2);
                    break;
                default:
                    // 默认处理
                    // 处理按钮1的操作
                    openAssistantForm(AssistantPlayer);
                    break;
            }
        }));
        // 显示表单给玩家
        AssistantPlayer.showFormWindow(form);
    }

    public static void banForm(Player AssistantPlayer) {
        FormWindowCustom form = new FormWindowCustom(banForm_title);
        // 添加组件
        form.addElement(new ElementInput(banForm_ElementInput1));
        form.addElement(new ElementInput(banForm_ElementInput2));
        // 设置提交操作
        form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
            if (form.wasClosed()) return;
            String banPlayerName = form.getResponse().getInputResponse(0); // 获取文本输入框的值
            String substance = form.getResponse().getInputResponse(1);
            banlxPlayer(banPlayerName, substance);
        }));
        // 显示表单给玩家
        AssistantPlayer.showFormWindow(form);
    }

    public static void SetWebUIpwdForm(Player player) {
        FormWindowCustom form = new FormWindowCustom(SetWebUIpwdForm_title);
        // 添加组件
        form.addElement(new ElementInput("", SetWebUIpwdForm_ElementInput1));
        form.addElement(new ElementInput("", SetWebUIpwdForm_ElementInput2));
        // 设置提交操作
        form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
            if (form.wasClosed()) return;
            String inputpassword = form.getResponse().getInputResponse(0); // 获取文本输入框的值
            String againpassword = form.getResponse().getInputResponse(1); // 获取下拉框选择的文本
            if (inputpassword.equals(againpassword)) {
                player.sendMessage(generalForm_sendMessage + inputpassword);
                setAssistantPwd(player.getName(), inputpassword);
            } else {
                player.sendMessage(generalForm_sendMessage_error);
            }
            // 处理用户提交的数据
        }));
        // 显示表单给玩家
        player.showFormWindow(form);
    }

    public static void openAssistantForm(Player AssistantPlayer) {
        FormWindowCustom form = new FormWindowCustom(openAssistantForm_title);
        ArrayList<String> allPlayerNames = new ArrayList<>();
        ArrayList<Player> allPlayers = new ArrayList<>();
        for (Player serverPlayer : nkServer.getOnlinePlayers().values()) {
            allPlayerNames.add(serverPlayer.getName());
            allPlayers.add(serverPlayer);
        }
        // 添加组件
        form.addElement(new ElementDropdown(openAssistantForm_ElementDropdown1, allPlayerNames));
        form.addElement(new ElementDropdown(openAssistantForm_ElementDropdown2, openAssistantForm_Arrays));
        form.addElement(new ElementInput(openAssistantForm_ElementInput1));
        // 设置提交操作
        form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
            if (form.wasClosed()) return;// 如果玩家关闭了表单，终止方法防止报错
            int playersBeingDealtWithIndex = form.getResponse().getDropdownResponse(0).getElementID();
            String processingText = form.getResponse().getDropdownResponse(1).getElementContent();
            int methodsOfProcessing = form.getResponse().getDropdownResponse(1).getElementID(); // 获取下拉框选择的索引
            String substance = form.getResponse().getInputResponse(2);
            Player playersBeingDealtWith = allPlayers.get(playersBeingDealtWithIndex);
            switch (methodsOfProcessing) {
                case 1:
                    killPlayer(playersBeingDealtWith, substance);
                    tipsMessage(AssistantPlayer, playersBeingDealtWith, processingText);
                    break;
                case 2:
                    kickPlayer(playersBeingDealtWith, substance);
                    tipsMessage(AssistantPlayer, playersBeingDealtWith, processingText);
                    break;
                case 3:
                    if (AssistantPlayer != playersBeingDealtWith) {
                        banPlayer(playersBeingDealtWith, substance);
                        tipsMessage(AssistantPlayer, playersBeingDealtWith, processingText);
                    } else {
                        AssistantPlayer.sendMessage(openAssistantForm_sendMessage);
                    }
                    break;
                case 4:
                    if (DisableBanip) {
                        AssistantPlayer.sendMessage(disableBanip_tipsMessage);
                    } else {
                        if (AssistantPlayer != playersBeingDealtWith) {
                            banPlayerIP(playersBeingDealtWith, substance);
                            tipsMessage(AssistantPlayer, playersBeingDealtWith, processingText);
                        } else {
                            AssistantPlayer.sendMessage(openAssistantForm_sendMessage);
                        }
                    }
                    break;
                default:
                    warnPlayer(playersBeingDealtWith, substance);
                    tipsMessage(AssistantPlayer, playersBeingDealtWith, processingText);
            }
        }));
        // 显示表单给玩家
        AssistantPlayer.showFormWindow(form);
    }


    public static void UnbanForm(Player AssistantPlayer) {
        FormWindowCustom form = new FormWindowCustom(UnbanForm_title);
        // 添加组件
        form.addElement(new ElementInput(UnbanForm_ElementInput));

        // 设置提交操作
        form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
            if (form.wasClosed()) return;
            String UnbanPlayerName = form.getResponse().getInputResponse(0); // 获取文本输入框的值
            unBanPlayer(UnbanPlayerName);
        }));
        // 显示表单给玩家
        AssistantPlayer.showFormWindow(form);
    }

    public static ArrayList<String> getPlayerNameList() {
        ArrayList<String> allPlayerNames = new ArrayList<>();
        for (Player serverPlayer : nkServer.getOnlinePlayers().values()) {
            allPlayerNames.add(serverPlayer.getName());
        }
        return allPlayerNames;
    }
    //    public static void dealWithPlayersForm(Player playersBeingDealtWith, int methodsOfProcessing, String processingText, Player AssistantPlayer) {
//        FormWindowCustom form = new FormWindowCustom("§e" + processingText);
//        switch (methodsOfProcessing) {
//            case 1:
//                form.addElement(new ElementInput("§b杀死玩家发送的提示："));
//                form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
//                    if (form.wasClosed()) return;
//                    String substance = form.getResponse().getInputResponse(0);
//                    killPlayer(playersBeingDealtWith,substance);
//                    tipsMessage(AssistantPlayer, playersBeingDealtWith, processingText);
//                }));
//                break;
//            case 2:
//                form.addElement(new ElementInput("§b踢出玩家发送的提示："));
//                form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
//                    if (form.wasClosed()) return;
//                    String substance = form.getResponse().getInputResponse(0);
//                    kickPlayer(playersBeingDealtWith, substance);
//                    tipsMessage(AssistantPlayer, playersBeingDealtWith, processingText);
//                }));
//                break;
//            case 3:
//                form.addElement(new ElementInput("§b封禁玩家发送的提示："));
//                form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
//                    if (form.wasClosed()) return;
//                    String substance = form.getResponse().getInputResponse(0);
//                    if (AssistantPlayer != playersBeingDealtWith) {
//                        banPlayer(playersBeingDealtWith, substance);
//                        tipsMessage(AssistantPlayer, playersBeingDealtWith, processingText);
//                    } else {
//                        AssistantPlayer.sendMessage("§c你不能封禁你自己");
//                    }
//                }));
//                break;
//            case 4:
//                form.addElement(new ElementInput("§b封禁玩家发送的提示："));
//                form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
//                    if (form.wasClosed()) return;
//                    String substance = form.getResponse().getInputResponse(0);
//                    if (AssistantPlayer != playersBeingDealtWith) {
//                        banPlayerIP(playersBeingDealtWith, substance);
//                        tipsMessage(AssistantPlayer, playersBeingDealtWith, processingText);
//                    } else {
//                        AssistantPlayer.sendMessage("§c你不能封禁你自己");
//                    }
//                }));
//                break;
//            default:
//                form.addElement(new ElementInput("§b警告玩家发送的消息："));
//                form.addHandler(FormResponseHandler.withoutPlayer(ignored -> {
//                    if (form.wasClosed()) return;
//                    String substance = form.getResponse().getInputResponse(0);
//                    warnPlayer(playersBeingDealtWith, substance);
//                    tipsMessage(AssistantPlayer, playersBeingDealtWith, processingText);
//                }));
//        }
//        AssistantPlayer.showFormWindow(form);
//    }
}
