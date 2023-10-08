package com.sindercube.takenForGranite.blockProperties.properties;

import com.sindercube.takenForGranite.blockProperties.BlockProperties;
import com.sindercube.takenForGranite.mixin.StairsBlockAccessor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class StairsProperties implements BlockProperties {
    @Override
    public Block block(AbstractBlock.Settings settings) {return null;}
    @Override
    public Block blockFromParent(Block block, AbstractBlock.Settings settings) {
        return StairsBlockAccessor.createStairsBlock(block.getDefaultState(), settings);
    }
}
