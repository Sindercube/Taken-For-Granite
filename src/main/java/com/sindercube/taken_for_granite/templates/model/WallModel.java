package com.sindercube.taken_for_granite.templates.model;

import com.google.gson.JsonObject;
import com.sindercube.taken_for_granite.registry.BlockData;
import com.sindercube.taken_for_granite.templates.BaseModel;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class WallModel extends BaseModel {
    @Override
    public String getID() {
        return "wall";
    }
    @Override
    public Block block(FabricBlockSettings settings) {
        return new WallBlock(settings.solid());
    }
    public void genModel(BlockStateModelGenerator generator, BlockData data) {
        TextureMap texture = TextureMap.all(data.parent.block);
        Identifier postModelID = Models.TEMPLATE_WALL_POST.upload(data.block, texture, generator.modelCollector);
        Identifier lowSideModelID = Models.TEMPLATE_WALL_SIDE.upload(data.block, texture, generator.modelCollector);
        Identifier tallSideModelID = Models.TEMPLATE_WALL_SIDE_TALL.upload(data.block, texture, generator.modelCollector);
        BlockStateSupplier supplier = BlockStateModelGenerator.createWallBlockState(data.block, postModelID, lowSideModelID, tallSideModelID);
        generator.blockStateCollector.accept(supplier);
        Models.WALL_INVENTORY.upload(data.block, texture, generator.modelCollector);
        generator.modelCollector.accept(data.id.withPrefixedPath("item/"), () -> {
            JsonObject object = new JsonObject();
            object.addProperty("parent", data.id.withPrefixedPath("block/").withSuffixedPath("_inventory").toString());
            return object;
        });
    }
}
