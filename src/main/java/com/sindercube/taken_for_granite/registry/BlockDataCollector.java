package com.sindercube.taken_for_granite.registry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

import static com.sindercube.taken_for_granite.TakenForGranite.MODID;

public class BlockDataCollector {
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public static void get(FabricLoader loader) throws IOException {
        Optional<ModContainer> container = loader.getModContainer(MODID);
        if (container.isEmpty()) return;
        Path modRoot = container.get().getRootPaths().get(0);
        Path blocksPath = modRoot.resolve("blocks/");

        try (Stream<Path> entries = Files.list(blocksPath)) { entries.forEach(path -> {
            File file = path.toFile();
            if (!file.toString().endsWith(".json")) return;

            JsonObject data = null;
            try {
                data = JsonParser.parseReader(new FileReader(file.getAbsoluteFile())).getAsJsonObject();
            } catch (FileNotFoundException ignored) {}
            if (data == null) return;

            String id = file.getName().replaceFirst("[.][^.]+$", "");

            BlockData blockData = new BlockData(id, data.getAsJsonObject());

            STORAGE.add(blockData);
            if (data.has("variants")) {
                blockData.addVariants(data);
            }
        }); }
    }
}
