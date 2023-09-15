package com.sindercube.taken_for_granite.datagen;

import com.sindercube.taken_for_granite.registry.BlockDataStorage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.ItemTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class TagGenerator extends FabricTagProvider.BlockTagProvider {
    private static final TagKey<Block> PICKAXE_BREAKABLE = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft:mineable/pickaxe"));
    private static final TagKey<Block> WALLS = TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft:walls"));
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public TagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        FabricTagProvider<Block>.FabricTagBuilder pickaxeBreakableBuilder = getOrCreateTagBuilder(PICKAXE_BREAKABLE);

        FabricTagProvider<Block>.FabricTagBuilder wallsBuilder = getOrCreateTagBuilder(WALLS);
        STORAGE.forEach(blockData -> {
            pickaxeBreakableBuilder.add(blockData.block);
            if (blockData.blockType.equals("wall")) wallsBuilder.add(blockData.block);
        });
    }
}