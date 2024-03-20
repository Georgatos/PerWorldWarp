package dev.andreasgeorgatos.perworldwarp;

import dev.andreasgeorgatos.perworldwarp.commands.DeleteWarp;
import dev.andreasgeorgatos.perworldwarp.commands.ListWarps;
import dev.andreasgeorgatos.perworldwarp.commands.SetWarp;
import dev.andreasgeorgatos.perworldwarp.commands.Warp;
import dev.andreasgeorgatos.perworldwarp.data.WarpDataManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerWorldWarp extends JavaPlugin {

    private final Logger logger = LoggerFactory.getLogger(PerWorldWarp.class);

    @Override
    public void onEnable() {
        saveDefaultConfig();
        WarpDataManager warpManager = new WarpDataManager(this);
        getCommand("deletewarp").setExecutor(new DeleteWarp(warpManager));
        getCommand("listwarps").setExecutor(new ListWarps(warpManager));
        getCommand("setwarp").setExecutor(new SetWarp(warpManager));
        getCommand("warp").setExecutor(new Warp(warpManager));
        logger.info("The per world wrap plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        logger.info("The per world wrap plugin has been disabled.");
    }
}