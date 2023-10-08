package com.sindercube.takenForGranite.blockProperties;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public interface BlockProperties {

    default Block block(AbstractBlock.Settings settings) {
        return new Block(settings);
    }
    default Block blockFromParent(Block block, AbstractBlock.Settings settings) {
        return block(settings);
    }

}
