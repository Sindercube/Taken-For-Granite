package com.sindercube.taken_for_granite.templates;

import com.sindercube.taken_for_granite.registry.BlockData;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

public abstract class BaseModel {
    public abstract String getID();
    public abstract Block block(FabricBlockSettings settings);
    public void genItemModel(ItemModelGenerator generator, BlockData data) {}
    public abstract void genModel(BlockStateModelGenerator generator, BlockData data);
    public Block blockFromParent(Block block, FabricBlockSettings settings) {
        return this.block(settings);
    }
}
