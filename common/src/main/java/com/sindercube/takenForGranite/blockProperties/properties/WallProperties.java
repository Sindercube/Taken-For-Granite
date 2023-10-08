package com.sindercube.takenForGranite.blockProperties.properties;

import com.sindercube.takenForGranite.blockProperties.BlockProperties;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;

public class WallProperties implements BlockProperties {
    @Override
    public Block block(AbstractBlock.Settings settings) {
        return new WallBlock(settings.solid());
    }
}
