package com.sindercube.taken_for_granite;

import com.sindercube.taken_for_granite.registry.BlockDataCollector;
import com.sindercube.taken_for_granite.registry.BlockDataStorage;
import com.sindercube.taken_for_granite.registry.RegisterBlocks;
import com.sindercube.taken_for_granite.templates.ModelTemplateStorage;
import com.sindercube.taken_for_granite.templates.model.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.sindercube.taken_for_granite.registry.BlockDataStorage.ITEMS;

public class TakenForGranite implements ModInitializer {
	public static ModelTemplateStorage MTS = ModelTemplateStorage.INSTANCE;
	public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;

	public static final String MODID = "taken_for_granite";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		MTS.add(new CubeModel());
		MTS.add(new PillarModel());
		MTS.add(new SlabModel());
		MTS.add(new StairsModel());
		MTS.add(new WallModel());

		try { BlockDataCollector.get(FabricLoader.getInstance()); } catch (IOException ignored) {}

		RegisterBlocks.register();

		FabricLoader.getInstance().getModContainer(MODID).ifPresent(container ->
			ResourceManagerHelper.registerBuiltinResourcePack(
				new Identifier(MODID, "default_changes"),
				container,
				Text.translatable("resource_pack.taken_for_granite.default_changes"),
				ResourcePackActivationType.ALWAYS_ENABLED
			)
		);

		LOGGER.info("Taken For Granite Initiated!");
	}
}