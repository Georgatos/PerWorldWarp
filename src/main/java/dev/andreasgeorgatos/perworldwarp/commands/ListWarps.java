package dev.andreasgeorgatos.perworldwarp.commands;

import dev.andreasgeorgatos.perworldwarp.data.WarpDataManager;
import dev.andreasgeorgatos.perworldwarp.messages.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ListWarps implements CommandExecutor {

    private final WarpDataManager warpManager;
    private final Messenger messenger;

    public ListWarps(WarpDataManager warpManager, Messenger messenger) {
        this.warpManager = warpManager;
        this.messenger = messenger;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(messenger.getMessage("onlyPlayers"));
            return false;
        }

        if (!sender.hasPermission("AlphaBoxWarps.listwarps")) {
            sender.sendMessage(messenger.getMessage("noPermission"));
            return false;
        }

        Player player = (Player) sender;
        Location location = player.getLocation();

        List<String> warps = warpManager.getAllWorldWarps(location.getWorld());
        StringBuilder sb = new StringBuilder();

        for (String warp : warps) {
            if (player.hasPermission("AlphaBoxWarps.warp.<" + warp + ">") || sender.hasPermission("AlphaBoxWarps.warp.*")) {
                sb.append(warp).append(",");
            }
        }

        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
            sender.sendMessage(messenger.getMessage("listWarps") + sb);
        } else {
            sender.sendMessage(messenger.getMessage("noWarpsAvailable"));
        }

        return true;
    }
}
