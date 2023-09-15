package com.sindercube.taken_for_granite.templates.recipe;

import com.sindercube.taken_for_granite.registry.BlockData;
import com.sindercube.taken_for_granite.templates.BaseRecipe;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.List;
import java.util.function.Consumer;

public class PolishedRecipe extends BaseRecipe {
    @Override
    public String getID() {
        return "polished";
    }
    @Override
    public void build(Consumer<RecipeJsonProvider> exporter, BlockData data) {
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
