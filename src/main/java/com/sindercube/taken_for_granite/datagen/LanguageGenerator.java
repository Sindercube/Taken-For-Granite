package com.sindercube.taken_for_granite.datagen;

import com.sindercube.taken_for_granite.registry.BlockData;
import com.sindercube.taken_for_granite.registry.BlockDataStorage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class LanguageGenerator extends FabricLanguageProvider {
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public LanguageGenerator(FabricDataOutput dataGenerator) {
        super(dataGenerator, "en_us");
    }
    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        STORAGE.forEach(blockData -> {
            translationBuilder.add(blockData.id.toTranslationKey("item"), blockData.name);
            translationBuilder.add(blockData.id.toTranslationKey("block"), blockData.name);
        });
    }
}