package com.sindercube.takenForGranite.staticData;

import com.google.gson.*;
import com.mojang.serialization.JsonOps;
import com.sindercube.takenForGranite.TFGMain;
import com.sindercube.takenForGranite.registry.BlockData;
import net.minecraft.util.Identifier;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StaticContent {
    private static final List<String> USED_DIRECTORIES = new ArrayList<>();

    public static void load(Identifier directory, ContentData dataClass) {
        if(USED_DIRECTORIES.contains(directory.toString())) {
            throw new UnsupportedOperationException(String.format("%s was already registered as a data directory key!", directory));
        }
        USED_DIRECTORIES.add(directory.toString());

        String dirName = String.format("%s/%s", directory.getNamespace(), directory.getPath());
        List<StaticDataItem> dataSet = StaticData.getAllInDirectory(dirName, "*.json");

        for(StaticDataItem data : dataSet) {
            try {
                InputStreamReader reader = new InputStreamReader(data.createInputStream(), StandardCharsets.UTF_8);
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                if (!json.has("id")) json.addProperty("id", data.getIdentifier().toString());

                Object rawData = dataClass.getCodec().parse(JsonOps.INSTANCE, json)
                        .resultOrPartial(TFGMain.LOGGER::error).orElseThrow();
                ContentData finalData = dataClass.getClass().cast(rawData);
                finalData.register(data.getIdentifier(), json);
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
