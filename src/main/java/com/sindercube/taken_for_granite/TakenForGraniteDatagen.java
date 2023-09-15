package com.sindercube.taken_for_granite;

import com.sindercube.taken_for_granite.datagen.*;
import com.sindercube.taken_for_granite.templates.RecipeTemplateStorage;
import com.sindercube.taken_for_granite.templates.recipe.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TakenForGraniteDatagen implements DataGeneratorEntrypoint {
	public static RecipeTemplateStorage RTS = RecipeTemplateStorage.INSTANCE;
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		RTS.add(new PolishedRecipe());
		RTS.add(new BricksRecipe());
		RTS.add(new TilesRecipe());
		RTS.add(new CutRecipe());
		RTS.add(new PillarRecipe());
		RTS.add(new ChiseledRecipe());

		RTS.add(new SlabRecipe());
		RTS.add(new StairsRecipe());
		RTS.add(new WallRecipe());

		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(ModelGenerator::new);
		pack.addProvider(LanguageGenerator::new);
		pack.addProvider(BlockLootTableGenerator::new);
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(TagGenerator::new);
	}
}
