package com.sindercube.takenForGranite;

import com.sindercube.takenForGranite.registry.BlockDataStorage;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;

public class TFGFabricClient implements ClientModInitializer {
    public static BlockDataStorage BTS = BlockDataStorage.INSTANCE;
    @Override
    public void onInitializeClient() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            BTS.itemList.forEach(content::add);
        });
    }
}
