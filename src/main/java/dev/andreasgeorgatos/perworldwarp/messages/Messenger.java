package dev.andreasgeorgatos.perworldwarp.messages;

import com.google.gson.JsonObject;
import dev.andreasgeorgatos.perworldwarp.files.FileManager;
import org.bukkit.ChatColor;

public class Messenger {
    private final FileManager fileManager;

    public Messenger(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public String getMessage(String path) {
        JsonObject jsonObject = fileManager.getJson();

        if (!jsonObject.has(path) || !jsonObject.get(path).getAsJsonObject().has("text")) {
            return ChatColor.translateAlternateColorCodes('&', "&3You have successfully been warped.");
        }

        return ChatColor.translateAlternateColorCodes('&', jsonObject.get(path).getAsJsonObject().get("text").getAsString());
    }
}