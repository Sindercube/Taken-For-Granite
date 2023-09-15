package com.sindercube.taken_for_granite.templates;

import com.sindercube.taken_for_granite.registry.BlockData;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.HashMap;
import java.util.function.Consumer;

public class RecipeTemplateStorage {
    public static RecipeTemplateStorage INSTANCE = new RecipeTemplateStorage();
    public HashMap<String, BaseRecipe> recipeTemplates;
    public RecipeTemplateStorage() {
        this.recipeTemplates = new HashMap<>();
    }
    public void add(BaseRecipe baseRecipe) {
        this.recipeTemplates.put(baseRecipe.getID(), baseRecipe);
    }
    public boolean contains(String id) {
        return this.recipeTemplates.containsKey(id);
    }
    public BaseRecipe get(String id) {
        return this.recipeTemplates.get(id);
    }
    public void offer(Consumer<RecipeJsonProvider> exporter, String id, BlockData data) {
        this.get(id).build(exporter, data);
    }
}
