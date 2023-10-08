package com.sindercube.takenForGranite.mixin;

import com.sindercube.takenForGranite.registry.BlockDataStorage;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ClearCacheMixin {
//    @Unique
//    private static BlockDataStorage TFG$STORAGE = BlockDataStorage.INSTANCE;
//    @Inject(at = @At("HEAD"), method = "run")
//    private void run(CallbackInfo info) {
//        TFG$STORAGE.clear();
//    }
}