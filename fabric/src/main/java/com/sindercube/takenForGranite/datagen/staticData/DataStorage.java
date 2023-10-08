package com.sindercube.takenForGranite.datagen.staticData;

import com.sindercube.takenForGranite.registry.BlockData;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.HashMap;
import java.util.function.Consumer;

public class DataStorage {
    public static String DEFAULT = "cube";
    public static DataStorage INSTANCE = new DataStorage();
    public HashMap<String, BaseData> modelTemplates = new HashMap<>();
    private DataStorage() {}
    public void add(BaseData baseModel) {
        this.modelTemplates.put(baseModel.getID(), baseModel);
    }
    public boolean contains(String id) {
        return this.modelTemplates.containsKey(id);
    }
    public BaseData get(String id) {
        return this.modelTemplates.getOrDefault(id, this.modelTemplates.get(DEFAULT));
    }
    public void offer(Consumer<RecipeJsonProvider> exporter, String id, BlockData data) {
        this.get(id).recipeBuilder(exporter, data);
    }
}
