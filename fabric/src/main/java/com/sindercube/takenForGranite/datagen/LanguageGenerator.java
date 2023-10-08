package com.sindercube.takenForGranite.datagen;

import com.sindercube.takenForGranite.registry.BlockDataStorage;
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
            String itemID = "item." + blockData.getID().getNamespace() + "." + blockData.getID().getPath();
            String blockID = "block." + blockData.getID().getNamespace() + "." + blockData.getID().getPath();
            translationBuilder.add(itemID, blockData.getName());
            translationBuilder.add(blockID, blockData.getName());
        });
    }
}