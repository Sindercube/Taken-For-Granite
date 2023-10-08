package com.sindercube.takenForGranite.datagen.staticData.model;

import com.sindercube.takenForGranite.datagen.staticData.BaseData;
import com.sindercube.takenForGranite.registry.BlockData;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.List;
import java.util.function.Consumer;

import static com.sindercube.takenForGranite.datagen.staticData.RecipeUtils.simpleShapedRecipe;
import static com.sindercube.takenForGranite.datagen.staticData.RecipeUtils.simpleStonecuttingRecipe;

public class PillarData extends BaseData {
    @Override
    public String getID() {
        return "pillar";
    }
    @Override
    public void blockModel(BlockStateModelGenerator generator, BlockData data) {
        generator.registerAxisRotated(data.block, TexturedModel.CUBE_COLUMN, TexturedModel.CUBE_COLUMN_HORIZONTAL);
    }
    @Override
    public void recipeBuilder(Consumer<RecipeJsonProvider> exporter, BlockData data) {
        simpleShapedRecipe(
                data,
                List.of(
                        "#",
                        "#"
                ),
                2
        ).offerTo(exporter);
        simpleStonecuttingRecipe(exporter, data, data.item);
    }
}
