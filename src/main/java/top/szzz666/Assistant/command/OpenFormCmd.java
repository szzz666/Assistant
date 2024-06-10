package top.szzz666.Assistant.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import java.util.Objects;

import static top.szzz666.Assistant.config.AssistantConfig.*;
import static top.szzz666.Assistant.form.AssistantForm.*;

public class OpenFormCmd extends Command {
    public OpenFormCmd() {
        super("xg", "打开协管菜单", "/xg [参数]");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(sender.isOp() || AssistantrConfig.containsKey(sender.getName())) {
            // 在这里编写你的命令逻辑
            if (args.length == 0) {
                generalForm((Player) sender);
                return true;
            } else if (Objects.equals(args[0], "open")) {
                openAssistantForm((Player) sender);
                return true;
            } else if (Objects.equals(args[0], "unban")) {
                UnbanForm((Player) sender);
                return true;
            } else if (Objects.equals(args[0], "add")) {
                if (sender.isOp()) {
                    if (addAssistant(args[1])) {
                        sender.sendMessage("§b添加协管成功");
                    } else {
                        sender.sendMessage("§c添加协管失败，该玩家已经是协管");
                    }
                    return true;
                }

            } else if (Objects.equals(args[0], "del")) {
                if (sender.isOp()) {
                    if (delAssistant(args[1])) {
                        sender.sendMessage("§b删除协管成功");
                    } else {
                        sender.sendMessage("§c删除协管失败");
                    }
                    return true;
                }
            }
        }else {
            sender.sendMessage("§c你没有权限执行该命令");
        }
        return false;
    }
}
