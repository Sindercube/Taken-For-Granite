package com.sindercube.takenForGranite.datagen.staticData.model;

import com.sindercube.takenForGranite.datagen.staticData.BaseData;
import com.sindercube.takenForGranite.registry.BlockData;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

import static com.sindercube.takenForGranite.datagen.staticData.RecipeUtils.simpleShapedRecipe;
import static com.sindercube.takenForGranite.datagen.staticData.RecipeUtils.simpleStonecuttingRecipe;

public class SlabData extends BaseData {
    @Override
    public String getID() {
        return "slab";
    }
    @Override
    public void blockModel(BlockStateModelGenerator generator, BlockData data) {
        TextureMap texture = TextureMap.all(data.parent.block);
        Identifier modelID = data.parent.getID().withPrefixedPath("block/");
        Identifier bottomModelID = Models.SLAB.upload(data.block, texture, generator.modelCollector);
        Identifier topModelID = Models.SLAB_TOP.upload(data.block, texture, generator.modelCollector);
        BlockStateSupplier supplier = BlockStateModelGenerator.createSlabBlockState(data.block, bottomModelID, topModelID, modelID);
        generator.blockStateCollector.accept(supplier);
    }
    @Override
    public void recipeBuilder(Consumer<RecipeJsonProvider> exporter, BlockData data) {
        simpleShapedRecipe(
                data,
                List.of("###"),
                6
        ).offerTo(exporter);
        simpleStonecuttingRecipe(exporter, data, data.item);
    }
}
