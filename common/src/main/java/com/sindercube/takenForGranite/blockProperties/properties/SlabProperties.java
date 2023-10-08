package com.sindercube.takenForGranite.blockProperties.properties;

import com.sindercube.takenForGranite.blockProperties.BlockProperties;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;

public class SlabProperties implements BlockProperties {
    @Override
    public Block block(AbstractBlock.Settings settings) {
        return new SlabBlock(settings);
    }
}
