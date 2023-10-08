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

public class StairsData extends BaseData {
    @Override
    public String getID() {
        return "stairs";
    }
    @Override
    public void blockModel(BlockStateModelGenerator generator, BlockData data) {
        TextureMap texture = TextureMap.all(data.parent.block);
        Identifier modelID = Models.STAIRS.upload(data.block, texture, generator.modelCollector);
        Identifier innerModelID = Models.INNER_STAIRS.upload(data.block, texture, generator.modelCollector);
        Identifier outerModelID = Models.OUTER_STAIRS.upload(data.block, texture, generator.modelCollector);
        BlockStateSupplier supplier = BlockStateModelGenerator.createStairsBlockState(data.block, innerModelID, modelID, outerModelID);
        generator.blockStateCollector.accept(supplier);
    }
    @Override
    public void recipeBuilder(Consumer<RecipeJsonProvider> exporter, BlockData data) {
        simpleShapedRecipe(
            data,
            List.of(
                "#  ",
                "## ",
                "###"
            ),
            4
        ).offerTo(exporter);
        simpleStonecuttingRecipe(exporter, data, data.item);
    }
}
