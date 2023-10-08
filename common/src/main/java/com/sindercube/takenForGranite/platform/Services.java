package com.sindercube.takenForGranite.platform;

import com.sindercube.takenForGranite.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

import static com.sindercube.takenForGranite.TFGMain.LOGGER;

public class Services {
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}