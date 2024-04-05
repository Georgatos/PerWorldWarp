package dev.andreasgeorgatos.perworldwarp.commands;

import dev.andreasgeorgatos.perworldwarp.data.WarpDataManager;
import dev.andreasgeorgatos.perworldwarp.messages.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor {

    private final WarpDataManager warpManager;
    private final Messenger messenger;

    public SetWarp(WarpDataManager wrapManager, Messenger messenger) {
        this.warpManager = wrapManager;
        this.messenger = messenger;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(messenger.getMessage("onlyPlayers"));
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(messenger.getMessage("wrongAmountOfArguments"));
            return false;
        }

        if (!sender.hasPermission("PerWorldWarp.setwarp")) {
            sender.sendMessage(messenger.getMessage("noPermission"));
            return false;
        }

        Location location = ((Player) sender).getLocation();

        if (warpManager.doesWarpExist(location.getWorld(), args[0])) {
            sender.sendMessage(messenger.getMessage("warpDoesNotExist"));
            return false;
        }

        warpManager.addWarp(((Player) sender).getLocation(), args[0]);
        sender.sendMessage(messenger.getMessage("warpHasBeenSet"));
        return true;
    }
}
