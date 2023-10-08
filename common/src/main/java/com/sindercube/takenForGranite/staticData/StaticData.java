package com.sindercube.takenForGranite.staticData;

import com.sindercube.takenForGranite.platform.Services;
import net.minecraft.util.Identifier;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.sindercube.takenForGranite.TFGMain.MOD_ID;

public class StaticData {
    public static String SOURCE = "content";

    public static List<StaticDataItem> getAllInDirectory(String dir) {
        return getAllInDirectory(dir, "*");
    }

    public static List<StaticDataItem> getAllInDirectory(String dir, String matchFile) {
        List<StaticDataItem> result = new ArrayList<>();
        Path gameDir = Services.PLATFORM.getGameDir();
        Path sourceDir = gameDir.resolve(SOURCE);

        if (!Files.isDirectory(sourceDir)) return result;

        Path dataDir = sourceDir.resolve(dir);
        if (!Files.isDirectory(dataDir)) return result;

        try {
            Files.newDirectoryStream(dataDir, matchFile).forEach(file -> {
                if (Files.isDirectory(file)) return;
                String id = FilenameUtils.removeExtension(file.getFileName().toString());
                result.add(new StaticDataItem(new Identifier(MOD_ID, id), file));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
