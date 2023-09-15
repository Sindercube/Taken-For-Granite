package com.sindercube.taken_for_granite.datagen;

import com.sindercube.taken_for_granite.templates.RecipeTemplateStorage;
import com.sindercube.taken_for_granite.registry.BlockDataStorage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

public class RecipeGenerator extends FabricRecipeProvider {
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public static RecipeTemplateStorage RTS = RecipeTemplateStorage.INSTANCE;
    public RecipeGenerator(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        STORAGE.forEach(blockData -> {
            if (!RTS.contains(blockData.blockType)) return;
            RTS.offer(exporter, blockData.blockType, blockData);
        });
    }
}