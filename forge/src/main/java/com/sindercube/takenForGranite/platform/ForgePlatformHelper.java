package com.sindercube.takenForGranite.platform;

import com.sindercube.takenForGranite.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

import static com.sindercube.takenForGranite.TFGMain.MOD_ID;

public class ForgePlatformHelper implements IPlatformHelper {
    @Override
    public String getPlatformName() {
        return "Forge";
    }
    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }
    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }
    @Override
    public Path getGameDir() {
//        return FMLPaths.GAMEDIR.get();
//        return FMLLoader.getLoadingModList().getModFileById(MOD_ID).getFile().getFilePath();
        return FMLLoader.getLoadingModList().getModFileById(MOD_ID).getFile().getSecureJar().getPath("/");
    }
}