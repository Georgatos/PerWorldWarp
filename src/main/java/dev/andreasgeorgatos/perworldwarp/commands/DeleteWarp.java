package dev.andreasgeorgatos.perworldwarp.commands;

import dev.andreasgeorgatos.perworldwarp.data.WarpDataManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteWarp implements CommandExecutor {

    private final WarpDataManager warpDataManager;

    public DeleteWarp(WarpDataManager wrapManager) {
        this.warpDataManager = wrapManager;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b Only players can run this command."));
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b Please only specify the warp name."));
            return false;
        }

        if (!sender.hasPermission("AlphaBoxWarps.deletewarp")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b You don't have permission to run this command."));
            return false;
        }


        World world = ((Player) sender).getLocation().getWorld();

        if (!warpDataManager.doesWarpExist(world, args[0])) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b The warp doesn't exists."));
            return false;
        }

        warpDataManager.deleteWarp(world, args[0]);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lAlpha&e&lBox&c&l") + ChatColor.translateAlternateColorCodes('&', "&b The has been deleted."));

        return true;
    }
}
