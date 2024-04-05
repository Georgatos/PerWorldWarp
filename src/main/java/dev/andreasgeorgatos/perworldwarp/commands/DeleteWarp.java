package dev.andreasgeorgatos.perworldwarp.commands;

import dev.andreasgeorgatos.perworldwarp.data.WarpDataManager;
import dev.andreasgeorgatos.perworldwarp.messages.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteWarp implements CommandExecutor {

    private final WarpDataManager warpDataManager;
    private final Messenger messenger;

    public DeleteWarp(WarpDataManager wrapManager, Messenger messenger) {
        this.warpDataManager = wrapManager;
        this.messenger = messenger;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(messenger.getMessage("onlyPlayers"));
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(messenger.getMessage("wrongAmountOfArguments"));
            return false;
        }

        if (!sender.hasPermission("PerWorldWarp.deletewarp")) {
            sender.sendMessage(messenger.getMessage("noPermission"));
            return false;
        }


        World world = ((Player) sender).getLocation().getWorld();

        if (!warpDataManager.doesWarpExist(world, args[0])) {
            sender.sendMessage(messenger.getMessage("warpDoesNotExist"));
            return false;
        }

        warpDataManager.deleteWarp(world, args[0]);
        sender.sendMessage(messenger.getMessage("warpDeleted"));

        return true;
    }
}
