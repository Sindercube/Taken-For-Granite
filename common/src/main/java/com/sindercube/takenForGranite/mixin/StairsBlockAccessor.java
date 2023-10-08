package com.sindercube.takenForGranite.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StairsBlock.class)
public interface StairsBlockAccessor {

    @Invoker("<init>")
    static StairsBlock createStairsBlock(BlockState baseBlockState, AbstractBlock.Settings settings) {
        throw new UnsupportedOperationException();
    }

}
