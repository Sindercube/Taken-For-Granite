package com.sindercube.takenForGranite.blockProperties;

import java.util.HashMap;

public class BlockPropertyStorage {
    public static BlockPropertyStorage INSTANCE = new BlockPropertyStorage();
    public final HashMap<String, BlockProperties> blockProperties = new HashMap<>();
    private BlockPropertyStorage() {}

    public void add(String id, BlockProperties properties) {
        this.blockProperties.put(id, properties);
    }
    public boolean contains(String id) {
        return this.blockProperties.containsKey(id);
    }
    public BlockProperties get(String id) {
        return this.blockProperties.getOrDefault(id, this.blockProperties.get("cube"));
    }
}
