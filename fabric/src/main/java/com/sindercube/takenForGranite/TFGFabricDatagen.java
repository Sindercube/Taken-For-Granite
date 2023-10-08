package com.sindercube.takenForGranite;

import com.sindercube.takenForGranite.datagen.*;
import com.sindercube.takenForGranite.datagen.staticData.DataStorage;
import com.sindercube.takenForGranite.datagen.staticData.model.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TFGFabricDatagen implements DataGeneratorEntrypoint {
	public static DataStorage DS = DataStorage.INSTANCE;
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		DS.add(new CubeData());
		DS.add(new BricksData());
		DS.add(new ChiseledData());
		DS.add(new CutData());
		DS.add(new PillarData());
		DS.add(new PolishedData());
		DS.add(new SlabData());
		DS.add(new StairsData());
		DS.add(new TilesData());
		DS.add(new WallData());

		pack.addProvider(ModelGenerator::new);
		pack.addProvider(LanguageGenerator::new);
		pack.addProvider(BlockLootTableGenerator::new);
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(TagGenerator::new);
	}
}
