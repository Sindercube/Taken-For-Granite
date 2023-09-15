package com.sindercube.taken_for_granite.datagen;

import com.sindercube.taken_for_granite.registry.BlockData;
import com.sindercube.taken_for_granite.registry.BlockDataStorage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class BlockLootTableGenerator extends FabricBlockLootTableProvider {
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public BlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    @Override
    public void generate() {
        STORAGE.forEach(blockData -> {
            addDrop(blockData.block);
        });
    }
}