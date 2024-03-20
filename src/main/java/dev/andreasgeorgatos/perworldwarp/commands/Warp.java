package dev.andreasgeorgatos.perworldwarp.commands;

import dev.andreasgeorgatos.perworldwarp.data.WarpData;
import dev.andreasgeorgatos.perworldwarp.data.WarpDataManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {

    private final WarpDataManager warpManager;

    public Warp(WarpDataManager wrapManager) {
        this.warpManager = wrapManager;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b Only players can run this command."));
            return false;
        }
        if (args.length != 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b Please only specify the wrap name."));
            return false;
        }

        if (!sender.hasPermission("AlphaBoxWarps.warp.<" + args[0] +">") || !sender.hasPermission("AlphaBoxWarps.warp.*")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b You don't have permission to run this command."));
            return false;
        }

        Player player = (Player) sender;
        Location location = player.getLocation();

        if (!warpManager.doesWarpExist(location.getWorld(), args[0])) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b The wrap doesn't exist."));
            return false;
        }

        WarpData warp = warpManager.getWarp(location.getWorld(), args[0]);

        player.teleport(warp.getLocation());

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b You have been teleported."));

        return true;
    }
}
