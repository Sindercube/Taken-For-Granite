package com.sindercube.taken_for_granite.templates.recipe;

import com.sindercube.taken_for_granite.registry.BlockData;
import com.sindercube.taken_for_granite.templates.BaseRecipe;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;

import java.util.List;
import java.util.function.Consumer;

public class SlabRecipe extends BaseRecipe {
    @Override
    public String getID() {
        return "slab";
    }
    @Override
    public void build(Consumer<RecipeJsonProvider> exporter, BlockData data) {
        simpleShapedRecipe(
            data,
            List.of("###"),
            6
        ).offerTo(exporter);
        simpleStonecuttingRecipe(exporter, data, data.item);
    }
}
