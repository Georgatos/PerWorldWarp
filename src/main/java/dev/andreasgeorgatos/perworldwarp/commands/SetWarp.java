package dev.andreasgeorgatos.perworldwarp.commands;

import dev.andreasgeorgatos.perworldwarp.data.WarpDataManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor {

    private final WarpDataManager warpManager;

    public SetWarp(WarpDataManager wrapManager) {
        this.warpManager = wrapManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b Only players can run this command."));
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b Please only specify the warp name."));
            return false;
        }

        if (!sender.hasPermission("AlphaBoxWarps.setwarp")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b You don't have permission to run this command."));
            return false;
        }

        Location location = ((Player) sender).getLocation();

        if (warpManager.doesWarpExist(location.getWorld(), args[0])) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b The warp already exists."));
            return false;
        }

        warpManager.addWarp(((Player) sender).getLocation(), args[0]);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b The warp has been created."));

        return true;
    }
}
