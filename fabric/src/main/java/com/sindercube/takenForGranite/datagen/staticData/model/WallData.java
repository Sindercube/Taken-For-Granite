package com.sindercube.takenForGranite.datagen.staticData.model;

import com.google.gson.JsonObject;
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

public class WallData extends BaseData {
    @Override
    public String getID() {
        return "wall";
    }
    @Override
    public void blockModel(BlockStateModelGenerator generator, BlockData data) {
        TextureMap texture = TextureMap.all(data.parent.block);
        Identifier postModelID = Models.TEMPLATE_WALL_POST.upload(data.block, texture, generator.modelCollector);
        Identifier lowSideModelID = Models.TEMPLATE_WALL_SIDE.upload(data.block, texture, generator.modelCollector);
        Identifier tallSideModelID = Models.TEMPLATE_WALL_SIDE_TALL.upload(data.block, texture, generator.modelCollector);
        BlockStateSupplier supplier = BlockStateModelGenerator.createWallBlockState(data.block, postModelID, lowSideModelID, tallSideModelID);
        generator.blockStateCollector.accept(supplier);
        Models.WALL_INVENTORY.upload(data.block, texture, generator.modelCollector);
        generator.modelCollector.accept(data.getID().withPrefixedPath("item/"), () -> {
            JsonObject object = new JsonObject();
            object.addProperty("parent", data.getID().withPrefixedPath("block/").withSuffixedPath("_inventory").toString());
            return object;
        });
    }
    @Override
    public void recipeBuilder(Consumer<RecipeJsonProvider> exporter, BlockData data) {
        simpleShapedRecipe(
            data,
            List.of(
                "###",
                "###"
            ),
            6
        ).offerTo(exporter);
        simpleStonecuttingRecipe(exporter, data, data.item);
    }
}
