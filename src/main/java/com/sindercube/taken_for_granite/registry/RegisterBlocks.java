package com.sindercube.taken_for_granite.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RegisterBlocks {
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public static void register() {
        STORAGE.forEach(blockData -> {
            blockData.genBlock();
            Registry.register(Registries.BLOCK, blockData.id, blockData.block);
            Registry.register(Registries.ITEM, blockData.id, blockData.item);
        });
    }
}
