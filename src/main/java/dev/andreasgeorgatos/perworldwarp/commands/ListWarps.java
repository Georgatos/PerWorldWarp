package dev.andreasgeorgatos.perworldwarp.commands;

import dev.andreasgeorgatos.perworldwarp.data.WarpDataManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ListWarps implements CommandExecutor {

    private final WarpDataManager warpManager;

    public ListWarps(WarpDataManager warpManager) {
        this.warpManager = warpManager;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b Only players can run this command."));
            return false;
        }

        if (!sender.hasPermission("AlphaBoxWarps.listwarps")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b You don't have permission to run this command."));
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
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b The warps are: " + sb));
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b There are no warps available for this world."));
        }


        return true;
    }
}
