package com.sindercube.takenForGranite.staticData;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.sindercube.takenForGranite.registry.BlockData;
import net.minecraft.util.Identifier;

public abstract class ContentData {
    public abstract ContentData register(Identifier file, JsonObject json);

    public Codec<?> getCodec() {
        return null;
    }
}
