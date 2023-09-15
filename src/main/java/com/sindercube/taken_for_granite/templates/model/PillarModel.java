package com.sindercube.taken_for_granite.templates.model;

import com.sindercube.taken_for_granite.registry.BlockData;
import com.sindercube.taken_for_granite.templates.BaseModel;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class PillarModel extends BaseModel {
    @Override
    public String getID() {
        return "pillar";
    }

    @Override
    public Block block(FabricBlockSettings settings) {
        return new PillarBlock(settings);
    }
    @Override
    public void genModel(BlockStateModelGenerator generator, BlockData data) {
        generator.registerAxisRotated(data.block, TexturedModel.CUBE_COLUMN, TexturedModel.CUBE_COLUMN_HORIZONTAL);
    }
}
