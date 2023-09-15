package com.sindercube.taken_for_granite.templates.model;

import com.sindercube.taken_for_granite.registry.BlockData;
import com.sindercube.taken_for_granite.templates.BaseModel;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.util.Identifier;

public class SlabModel extends BaseModel {
    @Override
    public String getID() {
        return "slab";
    }
    @Override
    public Block block(FabricBlockSettings settings) {
        return new SlabBlock(settings);
    }
    @Override
    public void genModel(BlockStateModelGenerator generator, BlockData data) {
        TextureMap texture = TextureMap.all(data.parent.block);
        Identifier modelID = data.parent.id.withPrefixedPath("block/");
        Identifier bottomModelID = Models.SLAB.upload(data.block, texture, generator.modelCollector);
        Identifier topModelID = Models.SLAB_TOP.upload(data.block, texture, generator.modelCollector);
        BlockStateSupplier supplier = BlockStateModelGenerator.createSlabBlockState(data.block, bottomModelID, topModelID, modelID);
        generator.blockStateCollector.accept(supplier);
    }
}
