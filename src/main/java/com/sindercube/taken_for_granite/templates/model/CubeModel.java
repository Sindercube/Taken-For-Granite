package com.sindercube.taken_for_granite.templates.model;

import com.sindercube.taken_for_granite.registry.BlockData;
import com.sindercube.taken_for_granite.templates.BaseModel;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;

public class CubeModel extends BaseModel {
    @Override
    public String getID() {
        return "cube";
    }
    @Override
    public void genModel(BlockStateModelGenerator generator, BlockData data) {
        generator.registerSimpleCubeAll(data.block);
    }
    @Override
    public Block block(FabricBlockSettings settings) {
        return new Block(settings);
    }
}
