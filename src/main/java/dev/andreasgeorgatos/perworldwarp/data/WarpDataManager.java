package dev.andreasgeorgatos.perworldwarp.data;

import dev.andreasgeorgatos.perworldwarp.PerWorldWarp;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WarpDataManager {

    private final PerWorldWarp plugin;
    private final FileConfiguration config;
    private Set<WarpData> warps;

    public WarpDataManager(PerWorldWarp plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        warps = new HashSet<>();
        loadData();
    }

    public boolean doesWarpExist(World world, String name) {
        return warps
                .stream()
                .anyMatch(warp ->
                        warp.getLocation().getWorld().equals(world) && warp.getName().equals(name));
    }

    public void addWarp(Location location, String name) {
        warps.add(new WarpData(location, name));

        World world = location.getWorld();

        if (config.getConfigurationSection(world.getName()) == null) {
            config.createSection(world.getName());
        }
        config.getConfigurationSection(world.getName()).set(name, location);
        plugin.saveConfig();
    }

    public void deleteWarp(World world, String name) {
        if (config.getConfigurationSection(world.getName()) != null) {
            config.getConfigurationSection(world.getName()).set(name, null);
            plugin.saveConfig();
        }
        warps.removeIf(warp -> warp.getLocation().getWorld().equals(world) && warp.getName().equals(name));
    }

    public WarpData getWarp(World world, String name) {
        return warps
                .stream()
                .filter(warp -> warp.getName().equals(name) && warp.getLocation().getWorld().equals(world))
                .findFirst()
                .orElse(null);
    }

    public List<String> getAllWorldWarps(World world) {
        return warps
                .stream()
                .filter(warp -> warp.getLocation() != null && warp.getLocation().getWorld() != null && warp.getLocation().getWorld().equals(world))
                .map(WarpData::getName)
                .toList();
    }

    private void loadData() {
        for(String world : config.getKeys(false)) {
            for (String warpName : config.getConfigurationSection(world).getKeys(false)) {
                warps.add(new WarpData(config.getConfigurationSection(world).getLocation(warpName), warpName));
            }
        }
    }
}
