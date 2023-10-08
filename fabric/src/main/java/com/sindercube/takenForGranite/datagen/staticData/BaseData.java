package com.sindercube.takenForGranite.datagen.staticData;

import com.sindercube.takenForGranite.registry.BlockData;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

public abstract class BaseData {
    public abstract String getID();
    public void itemModel(ItemModelGenerator generator, BlockData data) {}
    public void blockModel(BlockStateModelGenerator generator, BlockData data) {
        generator.registerSimpleCubeAll(data.block);
    }
    public void recipeBuilder(Consumer<RecipeJsonProvider> exporter, BlockData data) {}
}
