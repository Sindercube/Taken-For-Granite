package com.sindercube.taken_for_granite.templates.model;

import com.sindercube.taken_for_granite.registry.BlockData;
import com.sindercube.taken_for_granite.templates.BaseModel;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.util.Identifier;

public class StairsModel extends BaseModel {
    @Override
    public String getID() {
        return "stairs";
    }
    @Override
    public Block block(FabricBlockSettings settings) {
        return null;
    }
    @Override
    public void genModel(BlockStateModelGenerator generator, BlockData data) {
        System.out.println("block: " + data.block);
        TextureMap texture = TextureMap.all(data.parent.block);
        Identifier modelID = Models.STAIRS.upload(data.block, texture, generator.modelCollector);
        Identifier innerModelID = Models.INNER_STAIRS.upload(data.block, texture, generator.modelCollector);
        Identifier outerModelID = Models.OUTER_STAIRS.upload(data.block, texture, generator.modelCollector);
        BlockStateSupplier supplier = BlockStateModelGenerator.createStairsBlockState(data.block, innerModelID, modelID, outerModelID);
        generator.blockStateCollector.accept(supplier);
    }
    @Override
    public Block blockFromParent(Block block, FabricBlockSettings settings) {
        return new StairsBlock(block.getDefaultState(), settings);
    }
}
