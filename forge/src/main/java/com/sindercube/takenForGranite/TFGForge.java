package com.sindercube.takenForGranite;

import com.sindercube.takenForGranite.registry.BlockDataStorage;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;

import static com.mojang.text2speech.Narrator.LOGGER;
import static com.sindercube.takenForGranite.TFGMain.MOD_ID;

@Mod(MOD_ID)
public class TFGForge {
    public TFGForge() {
        TFGMain.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::register);

        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("Taken For Granite Initiated!");
    }

    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    @SubscribeEvent
    public void register(RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.BLOCKS)) {
            IForgeRegistry<Block> registry = event.getForgeRegistry();
            registerBlocks(registry);
        }
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)) {
            IForgeRegistry<Item> registry = event.getForgeRegistry();
            registerItems(registry);
        }
    }
    public static void registerBlocks(final IForgeRegistry<Block> registry) {
        STORAGE.forEach(blockData -> {
            blockData.genBlock();
            registry.register(blockData.getID(), blockData.block);
        });
    }
    public static void registerItems(final IForgeRegistry<Item> registry) {
        STORAGE.forEach(blockData -> registry.register(blockData.getID(), blockData.item));
    }

    public static BlockDataStorage BTS = BlockDataStorage.INSTANCE;
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == ItemGroups.BUILDING_BLOCKS) {
            BTS.itemList.forEach(event::add);
        }
    }
}