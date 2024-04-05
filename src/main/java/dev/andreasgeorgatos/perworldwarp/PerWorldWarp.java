package dev.andreasgeorgatos.perworldwarp;

import dev.andreasgeorgatos.perworldwarp.commands.DeleteWarp;
import dev.andreasgeorgatos.perworldwarp.commands.ListWarps;
import dev.andreasgeorgatos.perworldwarp.commands.SetWarp;
import dev.andreasgeorgatos.perworldwarp.commands.Warp;
import dev.andreasgeorgatos.perworldwarp.data.WarpDataManager;
import dev.andreasgeorgatos.perworldwarp.files.FileManager;
import dev.andreasgeorgatos.perworldwarp.messages.Messenger;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerWorldWarp extends JavaPlugin {

    private final Logger logger = LoggerFactory.getLogger(PerWorldWarp.class);

    @Override
    public void onEnable() {
        saveDefaultConfig();
        WarpDataManager warpManager = new WarpDataManager(this);
        FileManager fileManager = new FileManager(this.getDataFolder());
        Messenger messenger = new Messenger(fileManager);
        getCommand("deletewarp").setExecutor(new DeleteWarp(warpManager, messenger));
        getCommand("listwarps").setExecutor(new ListWarps(warpManager, messenger));
        getCommand("setwarp").setExecutor(new SetWarp(warpManager, messenger));
        getCommand("warp").setExecutor(new Warp(warpManager, messenger));
        logger.info("The per world wrap plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        logger.info("The per world wrap plugin has been disabled.");
    }
}