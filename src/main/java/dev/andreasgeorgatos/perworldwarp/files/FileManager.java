package dev.andreasgeorgatos.perworldwarp.files;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

    private final Logger logger = LoggerFactory.getLogger(FileManager.class);

    private final File notificationJson;

    public FileManager(File dataFolder) {
        createFolder(dataFolder);
        notificationJson = new File(dataFolder + File.separator + "notifications.json");
        createJson(notificationJson);
    }

    public void saveJson(JsonObject jsonObject) {
        try (FileWriter file = new FileWriter(notificationJson.getAbsoluteFile())) {
            Gson gson = new Gson();
            file.write(gson.toJson(jsonObject));
            logger.info("Data has been successfully saved to " + notificationJson.getName());
        } catch (IOException e) {
            logger.error("An error occurred while saving data to " + notificationJson.getName() + ": ", e);
        }
    }

    public JsonObject getJson() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(notificationJson.getPath())));
            return JsonParser.parseString(content).getAsJsonObject();
        } catch (IOException e) {
            logger.error("An error occurred while reading the " + notificationJson.getName() + " file: ", e);
            return null;
        }
    }

    private void createJson(File notificationJson) {
        if (notificationJson.exists()) {
            return;
        }

        try (FileWriter file = new FileWriter(notificationJson.getAbsoluteFile())) {
            file.write("{\n}");
            logger.info("The " + notificationJson + " file has been created and initialized with an empty JSON object.");
        } catch (IOException e) {
            logger.error("An error occurred while creating the " + notificationJson.getName() + " file: ", e);

        }
    }

    private void createFolder(File dataFolder) {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
            logger.info("The folder: " + dataFolder.getName() + " has been created!");
        }
    }
}