package com.sindercube.takenForGranite.datagen;

import com.sindercube.takenForGranite.registry.BlockDataStorage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class BlockLootTableGenerator extends FabricBlockLootTableProvider {
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public BlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    @Override
    public void generate() {
        STORAGE.forEach(blockData -> drops(blockData.block));
    }
}