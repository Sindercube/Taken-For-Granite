package com.sindercube.takenForGranite.datagen.staticData.model;

import com.sindercube.takenForGranite.datagen.staticData.BaseData;
import com.sindercube.takenForGranite.registry.BlockData;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.List;
import java.util.function.Consumer;

import static com.sindercube.takenForGranite.datagen.staticData.RecipeUtils.simpleShapedRecipe;
import static com.sindercube.takenForGranite.datagen.staticData.RecipeUtils.simpleStonecuttingRecipe;

public class PolishedData extends BaseData {
    @Override
    public String getID() {
        return "polished";
    }
    @Override
    public void recipeBuilder(Consumer<RecipeJsonProvider> exporter, BlockData data) {
        simpleShapedRecipe(
                data,
                List.of(
                        "##",
                        "##"
                ),
                4
        ).offerTo(exporter);
        simpleStonecuttingRecipe(exporter, data, data.item);
    }
}
