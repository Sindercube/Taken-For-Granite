package com.sindercube.taken_for_granite;

import com.sindercube.taken_for_granite.registry.BlockDataStorage;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;

import static com.sindercube.taken_for_granite.registry.BlockDataStorage.ITEMS;

public class TakenForGraniteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            ITEMS.forEach(content::add);
        });
    }
}
