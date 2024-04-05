package dev.andreasgeorgatos.perworldwarp.data;

import org.bukkit.Location;

public class WarpData {
    private Location location;
    private String name;

    public WarpData(Location location, String name) {
        this.location = location;
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
