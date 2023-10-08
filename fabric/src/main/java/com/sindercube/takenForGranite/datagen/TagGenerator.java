package com.sindercube.takenForGranite.datagen;

import com.sindercube.takenForGranite.registry.BlockDataStorage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class TagGenerator extends FabricTagProvider.BlockTagProvider {
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public TagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        FabricTagBuilder pickaxeBreakableBuilder = getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE);
        FabricTagBuilder wallsBuilder = getOrCreateTagBuilder(BlockTags.WALLS);
        STORAGE.forEach(blockData -> {
            pickaxeBreakableBuilder.add(blockData.block);
            if (blockData.getType().equals("wall")) wallsBuilder.add(blockData.block);
        });
    }
}