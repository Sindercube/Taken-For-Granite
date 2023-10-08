package com.sindercube.takenForGranite;

import com.sindercube.takenForGranite.registry.BlockDataStorage;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.sindercube.takenForGranite.TFGMain.LOGGER;
import static com.sindercube.takenForGranite.TFGMain.MOD_ID;

public class TFGFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TFGMain.init();

        registerBlocks();

        FabricLoader.getInstance().getModContainer(MOD_ID).ifPresent(container ->
            ResourceManagerHelper.registerBuiltinResourcePack(
                new Identifier(MOD_ID, "default_changes"),
                container,
                Text.translatable("resource_pack.taken_for_granite.default_changes"),
                ResourcePackActivationType.ALWAYS_ENABLED
            )
        );

        LOGGER.info("Taken For Granite Initiated!");
    }
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public static void registerBlocks() {
        System.out.println(STORAGE.blockData.keySet());
        STORAGE.forEach(blockData -> {
            blockData.genBlock();
            System.out.println("id: " + blockData.getID());
            System.out.println("block: " + blockData.block);
            Registry.register(Registries.BLOCK, blockData.getID(), blockData.block);
            Registry.register(Registries.ITEM, blockData.getID(), blockData.item);
        });
    }
}
