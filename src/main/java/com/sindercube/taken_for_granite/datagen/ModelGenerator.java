package com.sindercube.taken_for_granite.datagen;

import com.sindercube.taken_for_granite.registry.BlockDataStorage;
import com.sindercube.taken_for_granite.templates.BaseModel;
import com.sindercube.taken_for_granite.templates.ModelTemplateStorage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModelGenerator extends FabricModelProvider {
    public static ModelTemplateStorage MTS = ModelTemplateStorage.INSTANCE;
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        STORAGE.forEach(blockData -> {
            BaseModel model = MTS.get(blockData.modelType);
            model.genModel(blockStateModelGenerator, blockData);
        });
    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        STORAGE.forEach(blockData -> {
            BaseModel model = MTS.get(blockData.modelType);
            model.genItemModel(itemModelGenerator, blockData);
        });
    }
}