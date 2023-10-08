package com.sindercube.takenForGranite.datagen;

import com.sindercube.takenForGranite.datagen.staticData.BaseData;
import com.sindercube.takenForGranite.datagen.staticData.DataStorage;
import com.sindercube.takenForGranite.registry.BlockData;
import com.sindercube.takenForGranite.registry.BlockDataStorage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;


public class ModelGenerator extends FabricModelProvider {
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public static DataStorage DS = DataStorage.INSTANCE;
    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        STORAGE.forEach(blockData -> {
            BaseData modelData = DS.get(blockData.getModel());
            System.out.println(blockData.getID());
            System.out.println(modelData);
            modelData.blockModel(blockStateModelGenerator, blockData);
        });
    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        STORAGE.forEach(blockData -> {
            BaseData modelData = DS.get(blockData.getModel());
            modelData.itemModel(itemModelGenerator, blockData);
        });
    }
}