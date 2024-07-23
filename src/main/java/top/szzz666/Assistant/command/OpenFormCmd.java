package top.szzz666.Assistant.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import java.util.Objects;

import static top.szzz666.Assistant.config.AssistantConfig.*;
import static top.szzz666.Assistant.config.LangConfig.*;
import static top.szzz666.Assistant.form.AssistantForm.*;

public class OpenFormCmd extends Command {
    public OpenFormCmd() {
        super(CommandConfig.get("CommandName"), OpFormCmd_description);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(sender.isOp() || AssistantrConfig.containsKey(sender.getName())) {
            if (args.length == 0) {
                generalForm((Player) sender);
                return true;
            } else if (Objects.equals(args[0], CommandConfig.get("openAssistantForm"))) {
                openAssistantForm((Player) sender);
                return true;
            } else if (Objects.equals(args[0], CommandConfig.get("openUnbanForm"))) {
                UnbanForm((Player) sender);
                return true;
            } else if (Objects.equals(args[0], CommandConfig.get("addAssistant"))) {
                if (sender.isOp()) {
                    if (addAssistant(args[1])) {
                        sender.sendMessage(OpenFormCmd_addAssistant_sendMessage);
                    } else {
                        sender.sendMessage(OpenFormCmd_addAssistant_sendMessage_error);
                    }
                    return true;
                }

            } else if (Objects.equals(args[0], CommandConfig.get("delAssistant"))) {
                if (sender.isOp()) {
                    if (delAssistant(args[1])) {
                        sender.sendMessage(OpenFormCmd_delAssistant_sendMessage);
                    } else {
                        sender.sendMessage(OpenFormCmd_delAssistant_sendMessage_error);
                    }
                    return true;
                }
            }
        }else {
            sender.sendMessage(OpenFormCmd_not);
        }
        return false;
    }
}
