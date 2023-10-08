package com.sindercube.takenForGranite.datagen;

import com.sindercube.takenForGranite.blockProperties.BlockPropertyStorage;
import com.sindercube.takenForGranite.datagen.staticData.DataStorage;
import com.sindercube.takenForGranite.registry.BlockDataStorage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

public class RecipeGenerator extends FabricRecipeProvider {
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public static DataStorage DS = DataStorage.INSTANCE;
    public RecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        STORAGE.forEach(blockData -> {
            if (!DS.contains(blockData.getType())) return;
            DS.offer(exporter, blockData.getType(), blockData);
        });
    }
}